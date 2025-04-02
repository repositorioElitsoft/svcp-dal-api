package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TrabajoTareaDTO;
import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TrabajoTareaMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoTareaMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TrabajoTareaError;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad TrabajoTarea.
 */
@Service
public class TrabajoTareaService {

    @Autowired
    private TrabajoTareaMapper trabajoTareaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoTareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoTareaService.class); //Logback

    /**
     * Agrega un nuevo TrabajoTarea.
     *
     * @param trabajoTareaDTO el TrabajoTarea DTO.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TrabajoTarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso trabajotarea ya existe.
     */
    public void agregar(TrabajoTareaDTO trabajoTareaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() trabajotarea");

        //  Valida Entrada
        if (trabajoTareaDTO == null || trabajoTareaDTO.getTrabajoId() == null || trabajoTareaDTO.getTareaId() == null) {
            logeador.error(Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE + ": {}", ((trabajoTareaDTO != null) ? trabajoTareaDTO.toString() : null));
            throw new EntradaInvalidadException(TrabajoTareaError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TrabajoTarea trabajotarea = mapper.toEntity(trabajoTareaDTO);
            Long nuevoId = trabajoTareaMapper.agregar(trabajotarea);
            logeador.info("TrabajoTarea agregado exitosamente id: {}", nuevoId);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TRABAJOTAREA_DUPLICADO_MENSAGE + ": {}, {}", trabajoTareaDTO.getTrabajoId(), trabajoTareaDTO.getTrabajoId());
            throw new RecursoDuplicadoException(TrabajoTareaError.DUPLICADO.getCodigoError(),
                    Constantes.TRABAJOTAREA_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_AGREGAR_MENSAJE + ": {}", trabajoTareaDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Agrega Lote nuevos TrabajoTarea.
     *
     * @param trabajoTareaLoteDTO lista de TrabajoTarea DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TrabajoTarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso trabajotarea ya existe.
     */
    public void agregarLote(List<TrabajoTareaDTO> trabajoTareaLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() trabajotarea");

        //  Valida Entrada
        if (trabajoTareaLoteDTO.isEmpty()) {
            logeador.error(Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TrabajoTareaError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TrabajoTarea> trabajoTareaLote = mapper.toEntityList(trabajoTareaLoteDTO);

            int registrosAgregados = trabajoTareaMapper.agregarLote(trabajoTareaLote);
            logeador.info("Lote TrabajoTarea agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TRABAJOTAREA_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TrabajoTareaError.DUPLICADO.getCodigoError(),
                    Constantes.TRABAJOTAREA_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJOTAREA_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TrabajoTarea existente.
     *
     * @param trabajoId       la clave de Trabajo a actualizar.
     * @param tareaId         la clave de Tarea a actualizar.
     * @param trabajoTareaDTO el TrabajoTarea DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TrabajoTarea no es encontrado.
     * @throws EntradaInvalidadException    si la entrada TrabajoTarea tiene errores.
     */
    public void actualizar(Long trabajoId, Long tareaId, TrabajoTareaDTO trabajoTareaDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() trabajotarea");

        //  Valida Entrada
        if (trabajoId == null || tareaId == null || trabajoTareaDTO == null || trabajoTareaDTO.getTrabajoId() == null || trabajoTareaDTO.getTareaId() == null) {
            logeador.error(Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE + ": {}", ((trabajoTareaDTO != null) ? trabajoTareaDTO.toString() : null));
            throw new EntradaInvalidadException(TrabajoTareaError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TrabajoTareaDTO trabajoTareaDTOEncontrado = this.encontrarPorClave(trabajoId, tareaId); // Verifica si existe el recurso
            TrabajoTarea trabajoTarea = mapper.toEntity(trabajoTareaDTO);
            trabajoTarea.setTrabajoId(trabajoId);
            trabajoTarea.setTareaId(tareaId);
            int registrosActualizados = trabajoTareaMapper.actualizar(trabajoTarea);
            logeador.info("trabajotarea actualizado exitosamente: {}, registros actualizados: {}, {} ", trabajoId, tareaId, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJOTAREA_ACTUALIZAR_MENSAJE + ": trabajoId={}, tareaId={}, {}", trabajoId, tareaId, trabajoTareaDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de TrabajoTarea existentes.
     *
     * @param trabajoTareaLoteDTO lista de TrabajoTarea DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TrabajoTarea tiene errores.
     */
    public void actualizarLote(List<TrabajoTareaDTO> trabajoTareaLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() trabajotarea");

        //  Valida Entrada
        if (trabajoTareaLoteDTO.isEmpty()) {
            logeador.error(Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TrabajoTareaError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TrabajoTarea> trabajoTareaLote = mapper.toEntityList(trabajoTareaLoteDTO);
            int registrosActualizados = trabajoTareaMapper.actualizarLote(trabajoTareaLote);
            logeador.info("Lote trabajotarea actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJOTAREA_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TrabajoTarea por Clave.
     *
     * @param trabajoId la clave de Trabajo a eliminar.
     * @param tareaId   la clave de Tarea a eliminar.
     * @throws RecursoNoEncontradoException si el TrabajoTarea no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si TrabajoTarea esta asociado a otro recurso
     */
    public void eliminar(Long trabajoId, Long tareaId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() trabajotarea: {}, {}", trabajoId, tareaId);

        try {
            TrabajoTareaDTO trabajoTareaDTO = this.encontrarPorClave(trabajoId, tareaId); // Verifica si existe
            int registrosEliminados = trabajoTareaMapper.eliminar(trabajoId, tareaId);
            logeador.info("trabajotarea eliminado: {}, registros eliminados: {}, {}", trabajoId, tareaId, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TRABAJOTAREA_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TrabajoTareaError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TRABAJOTAREA_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_ELIMINAR_MENSAJE + ": {}, {}", trabajoId, tareaId, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TrabajoTarea por Clave.
     *
     * @param idLote lista de claves de TrabajoTarea a eliminar.
     * @throws EntradaInvalidadException si la lista  TrabajoTarea esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TrabajoTarea esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TrabajoTareaError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = trabajoTareaMapper.eliminarLote(idLote);
            logeador.info("Lote trabajotarea eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TRABAJOTAREA_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TrabajoTareaError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TRABAJOTAREA_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJOTAREA_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TrabajoTarea por Clave.
     *
     * @param trabajoId la clave Trabajo a encontrar.
     * @param tareaId   la clave Tarea a encontrar.
     * @return el TrabajoTarea DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TrabajoTarea no es encontrado.
     */
    public TrabajoTareaDTO encontrarPorClave(Long trabajoId, Long tareaId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}, {} ", trabajoId, tareaId);

        try {
            TrabajoTareaDTO trabajoTareaDTO = mapper.toDto(trabajoTareaMapper.encontrarPorClave(trabajoId, tareaId));

            if (trabajoTareaDTO != null) {
                logeador.info("trabajotarea encontrado por clave : {}, {}", trabajoId, tareaId);
            } else {
                logeador.info("trabajotarea clave:{}, {} no encontrado", trabajoId, tareaId);
                throw new RecursoNoEncontradoException(TrabajoTareaError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.TRABAJOTAREA_NO_ENCONTRADO_MENSAGE);
            }

            return trabajoTareaDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, {}", trabajoId, tareaId, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TrabajoTareas.
     *
     * @return una lista de todos TrabajoTarea DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TrabajoTareaDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TrabajoTareaDTO> trabajoTareaLista = mapper.toDtoList(trabajoTareaMapper.obtenerTodos());
            logeador.info("trabajotareas obtenidos");
            return trabajoTareaLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJOTAREA_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
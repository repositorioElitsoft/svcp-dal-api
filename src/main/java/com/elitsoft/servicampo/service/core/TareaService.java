package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TareaDTO;
import com.elitsoft.servicampo.domain.entity.Tarea;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TareaMapper;
import com.elitsoft.servicampo.mapstruct.TareaMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TareaError;
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
 * Clase de Servicio para la entidad Tarea.
 */
@Service
public class TareaService {

    @Autowired
    private TareaMapper tareaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TareaService.class); //Logback


    /**
     * Agrega un nuevo Tarea.
     *
     * @param tareaDTO el Tarea DTO.
     * @return el Tarea DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Tarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso Tarea ya existe.
     */
    public TareaDTO agregar(TareaDTO tareaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Tarea");

        //  Valida Entrada
        if (tareaDTO == null) {
            logeador.error(Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TareaError.REQUERIDO.getCodigoError(),
                    Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Tarea tarea = mapper.toEntity(tareaDTO);
            tarea = tareaMapper.agregar(tarea);
            logeador.info("Tarea agregado exitosamente id: {}", tarea.getId());
            return mapper.toDTO(tarea);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TAREA_DUPLICADO_MENSAGE + ": {}", tareaDTO.getId());
            throw new RecursoDuplicadoException(TareaError.DUPLICADO.getCodigoError(),
                    Constantes.TAREA_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_AGREGAR_MENSAJE + ": {}", tareaDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Tarea.
     *
     * @param tareaLoteDTO lista de Tarea DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Tarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso tarea ya existe.
     */
    public void agregarLote(List<TareaDTO> tareaLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tarea");

        //  Valida Entrada
        if (tareaLoteDTO.isEmpty()) {
            logeador.error(Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TareaError.REQUERIDO.getCodigoError(),
                    Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Tarea> tareaLote = mapper.toEntityList(tareaLoteDTO);

            int registrosAgregados = tareaMapper.agregarLote(tareaLote);
            logeador.info("Lote Tarea agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TAREA_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TareaError.DUPLICADO.getCodigoError(),
                    Constantes.TAREA_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TAREA_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Tarea existente.
     *
     * @param id       la clave de Tarea a actualizar.
     * @param tareaDTO el Tarea DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Tarea no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Tarea tiene errores.
     */
    public void actualizar(Long id, TareaDTO tareaDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() tarea");

        //  Valida Entrada
        if (id == null || tareaDTO == null || tareaDTO.getId() == null) {
            logeador.error(Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tareaDTO != null) ? tareaDTO.toString() : null));
            throw new EntradaInvalidadException(TareaError.REQUERIDO.getCodigoError(),
                    Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tareaDTO.getId())) {
            logeador.error(Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, tareaDTO.toString());
            throw new EntradaInvalidadException(TareaError.ID_INVALIDO.getCodigoError(),
                    Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TareaDTO tareaDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Tarea tarea = mapper.toEntity(tareaDTO);
            tarea.setId(id);
            int registrosActualizados = tareaMapper.actualizar(tarea);
            logeador.info("tarea actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TAREA_ACTUALIZAR_MENSAJE + ": id={} {}", id, tareaDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de Tarea existentes.
     *
     * @param tareaLoteDTO lista de Tarea DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Tarea tiene errores.
     */
    public void actualizarLote(List<TareaDTO> tareaLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tarea");

        //  Valida Entrada
        if (tareaLoteDTO.isEmpty()) {
            logeador.error(Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TareaError.REQUERIDO.getCodigoError(),
                    Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Tarea> tareaLote = mapper.toEntityList(tareaLoteDTO);
            int registrosActualizados = tareaMapper.actualizarLote(tareaLote);
            logeador.info("Lote tarea actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TAREA_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Tarea por Clave.
     *
     * @param id la clave de Tarea a eliminar.
     * @throws RecursoNoEncontradoException si el Tarea no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Tarea esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() tarea: {}", id);

        try {
            TareaDTO tareaDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tareaMapper.eliminar(id);
            logeador.info("tarea eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TAREA_VIOLACION_INTEGRIDAD_MENSAGE + ": {}", id);
            throw new RecursoEliminarException(TareaError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TAREA_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Tarea por Clave.
     *
     * @param idLote lista de claves de Tarea a eliminar.
     * @throws EntradaInvalidadException si la lista  Tarea esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Tarea esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TareaError.REQUERIDO.getCodigoError(),
                    Constantes.TAREA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tareaMapper.eliminarLote(idLote);
            logeador.info("Lote tarea eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TAREA_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TareaError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TAREA_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TAREA_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Tarea por Clave.
     *
     * @param id la clave Tarea a encontrar.
     * @return el Tarea DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Tarea no es encontrado.
     */
    public TareaDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TareaDTO tareaDTO = mapper.toDTO(tareaMapper.encontrarPorClave(id));

            if (tareaDTO != null) {
                logeador.info("tarea encontrado por clave : {}", id);
            } else {
                logeador.info("tarea clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TareaError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.TAREA_NO_ENCONTRADO_MENSAGE);
            }

            return tareaDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Tareas.
     *
     * @return una lista de todos Tarea DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TareaDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TareaDTO> tareaLista = mapper.toDTOList(tareaMapper.obtenerTodos());
            logeador.info("tareas obtenidos");
            return tareaLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TAREA_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
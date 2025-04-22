package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDTO;
import com.elitsoft.servicampo.domain.entity.Trabajo;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TrabajoMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TrabajoError;
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
 * Clase de Servicio para la entidad Trabajo.
 */
@Service
public class TrabajoService {

    @Autowired
    private TrabajoMapper trabajoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoService.class); //Logback

    /**
     * Agrega un nuevo Trabajo.
     *
     * @param trabajoDTO el Trabajo DTO.
     * @return el Trabajo DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso Trabajo ya existe.
     */
    public TrabajoDTO agregar(TrabajoDTO trabajoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Trabajo");

        //  Valida Entrada
        if (trabajoDTO == null) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TrabajoError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Trabajo trabajo = mapper.toEntity(trabajoDTO);
            trabajo = trabajoMapper.agregar(trabajo);
            logeador.info("Trabajo agregado exitosamente id: {}", trabajo.getId());
            return mapper.toDTO(trabajo);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TRABAJO_DUPLICADO_MENSAGE + ": {}", trabajoDTO.getId());
            throw new RecursoDuplicadoException(TrabajoError.DUPLICADO.getCodigoError(),
                    Constantes.TRABAJO_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_AGREGAR_MENSAJE + ": {}", trabajoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Trabajo.
     *
     * @param trabajoLoteDTO lista de Trabajo DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso trabajo ya existe.
     */
    public void agregarLote(List<TrabajoDTO> trabajoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() trabajo");

        //  Valida Entrada
        if (trabajoLoteDTO.isEmpty()) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TrabajoError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Trabajo> trabajoLote = mapper.toEntityList(trabajoLoteDTO);

            int registrosAgregados = trabajoMapper.agregarLote(trabajoLote);
            logeador.info("Lote Trabajo agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TRABAJO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TrabajoError.DUPLICADO.getCodigoError(),
                    Constantes.TRABAJO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Trabajo existente.
     *
     * @param id         la clave de Trabajo a actualizar.
     * @param trabajoDTO el Trabajo DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Trabajo no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Trabajo tiene errores.
     */
    public void actualizar(Long id, TrabajoDTO trabajoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() trabajo");

        //  Valida Entrada
        if (id == null || trabajoDTO == null || trabajoDTO.getId() == null) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((trabajoDTO != null) ? trabajoDTO.toString() : null));
            throw new EntradaInvalidadException(TrabajoError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(trabajoDTO.getId())) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, trabajoDTO.toString());
            throw new EntradaInvalidadException(TrabajoError.ID_INVALIDO.getCodigoError(),
                    Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TrabajoDTO trabajoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Trabajo trabajo = mapper.toEntity(trabajoDTO);
            trabajo.setId(id);
            int registrosActualizados = trabajoMapper.actualizar(trabajo);
            logeador.info("trabajo actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_ACTUALIZAR_MENSAJE + ": id={} {}", id, trabajoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de Trabajo existentes.
     *
     * @param trabajoLoteDTO lista de Trabajo DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     */
    public void actualizarLote(List<TrabajoDTO> trabajoLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() trabajo");

        //  Valida Entrada
        if (trabajoLoteDTO.isEmpty()) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TrabajoError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Trabajo> trabajoLote = mapper.toEntityList(trabajoLoteDTO);
            int registrosActualizados = trabajoMapper.actualizarLote(trabajoLote);
            logeador.info("Lote trabajo actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Trabajo por Clave.
     *
     * @param id la clave de Trabajo a eliminar.
     * @throws RecursoNoEncontradoException si el Trabajo no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Trabajo esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() trabajo: {}", id);

        try {
            TrabajoDTO trabajoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = trabajoMapper.eliminar(id);
            logeador.info("trabajo eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TRABAJO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TrabajoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TRABAJO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Trabajo por Clave.
     *
     * @param idLote lista de claves de Trabajo a eliminar.
     * @throws EntradaInvalidadException si la lista  Trabajo esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Trabajo esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TrabajoError.REQUERIDO.getCodigoError(),
                    Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = trabajoMapper.eliminarLote(idLote);
            logeador.info("Lote trabajo eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TRABAJO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TrabajoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TRABAJO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Trabajo por Clave.
     *
     * @param id la clave Trabajo a encontrar.
     * @return el Trabajo DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Trabajo no es encontrado.
     */
    public TrabajoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TrabajoDTO trabajoDTO = mapper.toDTO(trabajoMapper.encontrarPorClave(id));

            if (trabajoDTO != null) {
                logeador.info("trabajo encontrado por clave : {}", id);
            } else {
                logeador.info("trabajo clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TrabajoError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE);
            }

            return trabajoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Trabajos.
     *
     * @return una lista de todos Trabajo DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TrabajoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TrabajoDTO> trabajoLista = mapper.toDTOList(trabajoMapper.obtenerTodos());
            logeador.info("trabajos obtenidos");
            return trabajoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TRABAJO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
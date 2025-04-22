package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoServicioDTO;
import com.elitsoft.servicampo.domain.entity.TipoServicio;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoServicioMapper;
import com.elitsoft.servicampo.mapstruct.TipoServicioMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TipoServicioError;
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
 * Clase de Servicio para la entidad TipoServicio.
 */
@Service
public class TipoServicioService {

    @Autowired
    private TipoServicioMapper tipoServicioMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoServicioService.class); //Logback


    /**
     * Agrega un nuevo TipoServicio.
     *
     * @param tipoServicioDTO el TipoServicio DTO.
     * @return el TipoServicio DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoServicio ya existe.
     */
    public TipoServicioDTO agregar(TipoServicioDTO tipoServicioDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoServicio");

        //  Valida Entrada
        if (tipoServicioDTO == null) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoServicio tipoServicio = mapper.toEntity(tipoServicioDTO);
            tipoServicio = tipoServicioMapper.agregar(tipoServicio);
            logeador.info("TipoServicio agregado exitosamente id: {}", tipoServicio.getId());
            return mapper.toDTO(tipoServicio);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE + ": {}", tipoServicioDTO.getId());
            throw new RecursoDuplicadoException(TipoServicioError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_AGREGAR_MENSAJE + ": {}", tipoServicioDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPOSERVICIO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoServicio.
     *
     * @param tipoServicioLoteDTO lista de TipoServicio DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso tiposervicio ya existe.
     */
    public void agregarLote(List<TipoServicioDTO> tipoServicioLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tiposervicio");

        //  Valida Entrada
        if (tipoServicioLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoServicio> tipoServicioLote = mapper.toEntityList(tipoServicioLoteDTO);

            int registrosAgregados = tipoServicioMapper.agregarLote(tipoServicioLote);
            logeador.info("Lote TipoServicio agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TipoServicioError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOSERVICIO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoServicio existente.
     *
     * @param id              la clave de TipoServicio a actualizar.
     * @param tipoServicioDTO el TipoServicio DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     * @throws EntradaInvalidadException    si la entrada TipoServicio tiene errores.
     */
    public void actualizar(Long id, TipoServicioDTO tipoServicioDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() tiposervicio");

        //  Valida Entrada
        if (id == null || tipoServicioDTO == null || tipoServicioDTO.getId() == null) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoServicioDTO != null) ? tipoServicioDTO.toString() : null));
            throw new EntradaInvalidadException(TipoServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipoServicioDTO.getId())) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, tipoServicioDTO.toString());
            throw new EntradaInvalidadException(TipoServicioError.ID_INVALIDO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoServicioDTO tipoServicioDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoServicio tipoServicio = mapper.toEntity(tipoServicioDTO);
            tipoServicio.setId(id);
            int registrosActualizados = tipoServicioMapper.actualizar(tipoServicio);
            logeador.info("tiposervicio actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoServicioDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de TipoServicio existentes.
     *
     * @param tipoServicioLoteDTO lista de TipoServicio DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     */
    public void actualizarLote(List<TipoServicioDTO> tipoServicioLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tiposervicio");

        //  Valida Entrada
        if (tipoServicioLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoServicio> tipoServicioLote = mapper.toEntityList(tipoServicioLoteDTO);
            int registrosActualizados = tipoServicioMapper.actualizarLote(tipoServicioLote);
            logeador.info("Lote tiposervicio actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoServicio por Clave.
     *
     * @param id la clave de TipoServicio a eliminar.
     * @throws RecursoNoEncontradoException si el TipoServicio no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si TipoServicio esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() tiposervicio: {}", id);

        try {
            this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoServicioMapper.eliminar(id);
            logeador.info("tiposervicio eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOSERVICIO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TipoServicioError.INTEGRIDAD_VIOLADA.getCodigoError(),
                                               Constantes.TIPOSERVICIO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoServicio por Clave.
     *
     * @param idLote lista de claves de TipoServicio a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoServicio esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TipoServicio esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tipoServicioMapper.eliminarLote(idLote);
            logeador.info("Lote tiposervicio eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOSERVICIO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TipoServicioError.INTEGRIDAD_VIOLADA.getCodigoError(),
                                               Constantes.TIPOSERVICIO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoServicio por Clave.
     *
     * @param id la clave TipoServicio a encontrar.
     * @return el TipoServicio DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     */
    public TipoServicioDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoServicioDTO tipoServicioDTO = mapper.toDTO(tipoServicioMapper.encontrarPorClave(id));

            if (tipoServicioDTO != null) {
                logeador.info("tiposervicio encontrado por clave : {}", id);
            } else {
                logeador.info("tiposervicio clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TipoServicioError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.TIPOSERVICIO_NO_ENCONTRADO_MENSAGE);
            }

            return tipoServicioDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOSERVICIO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoServicios.
     *
     * @return una lista de todos TipoServicio DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoServicioDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoServicioDTO> tipoServicioLista = mapper.toDTOList(tipoServicioMapper.obtenerTodos());
            logeador.info("tiposervicios obtenidos");
            return tipoServicioLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOSERVICIO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
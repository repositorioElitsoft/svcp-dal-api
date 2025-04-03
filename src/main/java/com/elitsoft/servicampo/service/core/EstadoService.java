package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.EstadoDTO;
import com.elitsoft.servicampo.domain.entity.Estado;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EstadoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoMapStruct;
import com.elitsoft.servicampo.service.error.EstadoError;
import com.elitsoft.servicampo.service.error.GeneralError;
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
 * Clase de Servicio para la entidad Estado.
 */
@Service
public class EstadoService {

    @Autowired
    private EstadoMapper estadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoService.class); //Logback

    /**
     * Agrega un nuevo Estado.
     *
     * @param estadoDTO el Estado DTO.
     * @return el Estado DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Estado tiene errores.
     * @throws RecursoDuplicadoException si el recurso Estado ya existe.
     */
    public EstadoDTO agregar(EstadoDTO estadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Estado");

        //  Valida Entrada
        if (estadoDTO == null) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(EstadoError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Estado estado = mapper.toEntity(estadoDTO);
            estado = estadoMapper.agregar(estado);
            logeador.info("Estado agregado exitosamente id: {}", estado.getId());
            return mapper.toDTO(estado);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ESTADO_DUPLICADO_MENSAGE + ": {}", estadoDTO.getId());
            throw new RecursoDuplicadoException(EstadoError.DUPLICADO.getCodigoError(),
                    Constantes.ESTADO_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_AGREGAR_MENSAJE + ": {}", estadoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Estado.
     *
     * @param estadoLoteDTO lista de Estado DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Estado tiene errores.
     * @throws RecursoDuplicadoException si el recurso estado ya existe.
     */
    public void agregarLote(List<EstadoDTO> estadoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() estado");

        //  Valida Entrada
        if (estadoLoteDTO.isEmpty()) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(EstadoError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Estado> estadoLote = mapper.toEntityList(estadoLoteDTO);

            int registrosAgregados = estadoMapper.agregarLote(estadoLote);
            logeador.info("Lote Estado agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ESTADO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(EstadoError.DUPLICADO.getCodigoError(),
                    Constantes.ESTADO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Estado existente.
     *
     * @param id        la clave de Estado a actualizar.
     * @param estadoDTO el Estado DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Estado no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Estado tiene errores.
     */
    public void actualizar(Long id, EstadoDTO estadoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() estado");

        //  Valida Entrada
        if (id == null || estadoDTO == null || estadoDTO.getId() == null) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((estadoDTO != null) ? estadoDTO.toString() : null));
            throw new EntradaInvalidadException(EstadoError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(estadoDTO.getId())) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, estadoDTO.toString());
            throw new EntradaInvalidadException(EstadoError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            EstadoDTO estadoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Estado estado = mapper.toEntity(estadoDTO);
            estado.setId(id);
            int registrosActualizados = estadoMapper.actualizar(estado);
            logeador.info("estado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADO_ACTUALIZAR_MENSAJE + ": id={} {}", id, estadoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de Estado existentes.
     *
     * @param estadoLoteDTO lista de Estado DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Estado tiene errores.
     */
    public void actualizarLote(List<EstadoDTO> estadoLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() estado");

        //  Valida Entrada
        if (estadoLoteDTO.isEmpty()) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(EstadoError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Estado> estadoLote = mapper.toEntityList(estadoLoteDTO);
            int registrosActualizados = estadoMapper.actualizarLote(estadoLote);
            logeador.info("Lote estado actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Estado por Clave.
     *
     * @param id la clave de Estado a eliminar.
     * @throws RecursoNoEncontradoException si el Estado no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Estado esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() estado: {}", id);

        try {
            EstadoDTO estadoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = estadoMapper.eliminar(id);
            logeador.info("estado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.ESTADO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(EstadoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.ESTADO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Estado por Clave.
     *
     * @param idLote lista de claves de Estado a eliminar.
     * @throws EntradaInvalidadException si la lista  Estado esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Estado esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(EstadoError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = estadoMapper.eliminarLote(idLote);
            logeador.info("Lote estado eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.ESTADO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(EstadoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.ESTADO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADO_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Estado por Clave.
     *
     * @param id la clave Estado a encontrar.
     * @return el Estado DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Estado no es encontrado.
     */
    public EstadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            EstadoDTO estadoDTO = mapper.toDTO(estadoMapper.encontrarPorClave(id));

            if (estadoDTO != null) {
                logeador.info("estado encontrado por clave : {}", id);
            } else {
                logeador.info("estado clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(EstadoError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.ESTADO_NO_ENCONTRADO_MENSAGE);
            }

            return estadoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Estados.
     *
     * @return una lista de todos Estado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EstadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<EstadoDTO> estadoLista = mapper.toDTOList(estadoMapper.obtenerTodos());
            logeador.info("estados obtenidos");
            return estadoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
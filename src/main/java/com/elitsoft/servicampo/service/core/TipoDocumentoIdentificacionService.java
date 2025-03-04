package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoDocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.entity.TipoDocumentoIdentificacion;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoDocumentoIdentificacionMapper;
import com.elitsoft.servicampo.mapstruct.TipoDocumentoIdentificacionMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad TipoDocumentoIdentificacion.
 */
@Service
public class TipoDocumentoIdentificacionService {

    @Autowired
    private TipoDocumentoIdentificacionMapper tipoDocumentoIdentificacionMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoDocumentoIdentificacionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoDocumentoIdentificacionService.class); //Logback


    /**
     * Agrega un nuevo TipoDocumentoIdentificacion.
     * @param tipoDocumentoIdentificacionDTO el TipoDocumentoIdentificacion DTO.
     * @return el TipoDocumentoIdentificacion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoDocumentoIdentificacion ya existe.
     */
    public TipoDocumentoIdentificacionDTO agregar(TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoDocumentoIdentificacion");

        //  Valida Entrada
        if (tipoDocumentoIdentificacionDTO == null) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoDocumentoIdentificacion tipoDocumentoIdentificacion = mapper.toEntity(tipoDocumentoIdentificacionDTO);
            tipoDocumentoIdentificacion = tipoDocumentoIdentificacionMapper.agregar(tipoDocumentoIdentificacion);
            logeador.info("TipoDocumentoIdentificacion agregado exitosamente id: {}", tipoDocumentoIdentificacion.getId());
            return mapper.toDTO(tipoDocumentoIdentificacion);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE + ": {}", tipoDocumentoIdentificacionDTO.getId());
            throw new RecursoDuplicadoException(Constantes.TIPODOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_AGREGAR_MENSAJE + ": {}", tipoDocumentoIdentificacionDTO.toString(), e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoDocumentoIdentificacion.
     * @param tipoDocumentoIdentificacionLoteDTO lista de TipoDocumentoIdentificacion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipodocumentoidentificacion ya existe.
     */
    public void agregarLote(List<TipoDocumentoIdentificacionDTO> tipoDocumentoIdentificacionLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipodocumentoidentificacion");

        //  Valida Entrada
        if (tipoDocumentoIdentificacionLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionLote = mapper.toEntityList(tipoDocumentoIdentificacionLoteDTO);

            int registrosAgregados =  tipoDocumentoIdentificacionMapper.agregarLote(tipoDocumentoIdentificacionLote);
            logeador.info("Lote TipoDocumentoIdentificacion agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.TIPODOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoDocumentoIdentificacion existente.
     * @param id la clave de TipoDocumentoIdentificacion a actualizar.
     * @param tipoDocumentoIdentificacionDTO el TipoDocumentoIdentificacion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDocumentoIdentificacion no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     */
    public void actualizar(Long id, TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tipodocumentoidentificacion");

        //  Valida Entrada
        if (id == null || tipoDocumentoIdentificacionDTO == null || tipoDocumentoIdentificacionDTO.getId() == null) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoDocumentoIdentificacionDTO != null) ? tipoDocumentoIdentificacionDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipoDocumentoIdentificacionDTO.getId())) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE + ": {}",  tipoDocumentoIdentificacionDTO.toString());
            throw new EntradaInvalidadException(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoDocumentoIdentificacionDTO tipodocumentoidentificacionDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoDocumentoIdentificacion tipoDocumentoIdentificacion = mapper.toEntity(tipoDocumentoIdentificacionDTO);
            tipoDocumentoIdentificacion.setId(id);
            int registrosActualizados = tipoDocumentoIdentificacionMapper.actualizar(tipoDocumentoIdentificacion);
            logeador.info("tipodocumentoidentificacion actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoDocumentoIdentificacionDTO.toString(), e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoDocumentoIdentificacion existentes.
     * @param tipoDocumentoIdentificacionLoteDTO lista de TipoDocumentoIdentificacion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     */
    public void actualizarLote(List<TipoDocumentoIdentificacionDTO> tipoDocumentoIdentificacionLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipodocumentoidentificacion");

        //  Valida Entrada
        if (tipoDocumentoIdentificacionLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionLote = mapper.toEntityList(tipoDocumentoIdentificacionLoteDTO);
            int registrosActualizados = tipoDocumentoIdentificacionMapper.actualizarLote(tipoDocumentoIdentificacionLote);
            logeador.info("Lote tipodocumentoidentificacion actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoDocumentoIdentificacion por Clave.
     * @param id la clave de TipoDocumentoIdentificacion a eliminar.
     * @throws RecursoNoEncontradoException si el TipoDocumentoIdentificacion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipodocumentoidentificacion: {}", id);

        try {
            TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoDocumentoIdentificacionMapper.eliminar(id);
            logeador.info("tipodocumentoidentificacion eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoDocumentoIdentificacion por Clave.
     * @param idLote lista de claves de TipoDocumentoIdentificacion a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoDocumentoIdentificacion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tipoDocumentoIdentificacionMapper.eliminarLote(idLote);
            logeador.info("Lote tipodocumentoidentificacion eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoDocumentoIdentificacion por Clave.
     * @param id la clave TipoDocumentoIdentificacion a encontrar.
     * @return el TipoDocumentoIdentificacion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDocumentoIdentificacion no es encontrado.
     */
    public TipoDocumentoIdentificacionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO = mapper.toDTO(tipoDocumentoIdentificacionMapper.encontrarPorClave(id));

            if (tipoDocumentoIdentificacionDTO != null) {
                logeador.info("tipodocumentoidentificacion encontrado por clave : {}", id);
            } else {
                logeador.info("tipodocumentoidentificacion clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.TIPODOCUMENTOIDENTIFICACION_NO_ENCONTRADO_MENSAGE);
            }

            return tipoDocumentoIdentificacionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoDocumentoIdentificacions.
     * @return una lista de todos TipoDocumentoIdentificacion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoDocumentoIdentificacionDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoDocumentoIdentificacionDTO> tipoDocumentoIdentificacionLista = mapper.toDTOList(tipoDocumentoIdentificacionMapper.obtenerTodos());
            logeador.info("tipodocumentoidentificacions obtenidos");
            return tipoDocumentoIdentificacionLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPODOCUMENTOIDENTIFICACION_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
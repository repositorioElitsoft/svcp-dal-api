package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.EstadoProductoDTO;
import com.elitsoft.servicampo.domain.entity.EstadoProducto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.EstadoProductoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoProductoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.EstadoProductoError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad EstadoProducto.
 */
@Service
public class EstadoProductoService {

    @Autowired
    private EstadoProductoMapper estadoproductoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoProductoService.class); //Logback


    /**
     * Agrega un nuevo EstadoProducto.
     * @param estadoproductoDTO el EstadoProducto DTO.
     * @return el EstadoProducto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso EstadoProducto ya existe.
     */
    public EstadoProductoDTO agregar(EstadoProductoDTO estadoproductoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() EstadoProducto");

        //  Valida Entrada
        if (estadoproductoDTO == null) {
            logeador.error(Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           EstadoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            EstadoProducto estadoproducto = mapper.toEntity(estadoproductoDTO);
            estadoproducto = estadoproductoMapper.agregar(estadoproducto);
            logeador.info("EstadoProducto agregado exitosamente id: {}", estadoproducto.getId());
            return mapper.toDTO(estadoproducto);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_DUPLICADO_MENSAGE + ": {}, codigoError:{}", estadoproductoDTO.getId(),
                           EstadoProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(EstadoProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_AGREGAR_MENSAJE + ": {}, codigoError:{}", estadoproductoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.ESTADOPRODUCTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos EstadoProducto.
     * @param estadoproductoDTOLote lista de EstadoProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso estadoproducto ya existe.
     */
    public void agregarLote(List<EstadoProductoDTO> estadoproductoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() estadoproducto");

        //  Valida Entrada
        if (estadoproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          EstadoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<EstadoProducto> estadoproductoLote = mapper.toEntityList(estadoproductoDTOLote);

            int registrosAgregados =  estadoproductoMapper.agregarLote(estadoproductoLote);
            logeador.info("Lote EstadoProducto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_DUPLICADO_MENSAGE + " codigoError:{}",
                          EstadoProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(EstadoProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.ESTADOPRODUCTO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un EstadoProducto existente.
     * @param id la clave de EstadoProducto a actualizar.
     * @param estadoproductoDTO el EstadoProducto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si EstadoProducto no es encontrado.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     */
    public void actualizar(Long id, EstadoProductoDTO estadoproductoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() estadoproducto");

        //  Valida Entrada
        if (id == null || estadoproductoDTO == null || estadoproductoDTO.getId() == null) {
            logeador.error(Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((estadoproductoDTO != null) ? estadoproductoDTO.toString() : null  ),
                           EstadoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(estadoproductoDTO.getId())) {
            logeador.error(Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  estadoproductoDTO.toString(),
                           EstadoProductoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoProductoError.ID_INVALIDO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            EstadoProductoDTO estadoproductoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            EstadoProducto estadoproducto = mapper.toEntity(estadoproductoDTO);
            estadoproducto.setId(id);
            int registrosActualizados = estadoproductoMapper.actualizar(estadoproducto);
            logeador.info("estadoproducto actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, estadoproductoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.ESTADOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de EstadoProducto existentes.
     * @param estadoproductoDTOLote lista de EstadoProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     */
    public void actualizarLote(List<EstadoProductoDTO> estadoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() estadoproducto");

        //  Valida Entrada
        if (estadoproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           EstadoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<EstadoProducto> estadoproductoLote = mapper.toEntityList(estadoproductoDTOLote);
            int registrosActualizados = estadoproductoMapper.actualizarLote(estadoproductoLote);
            logeador.info("Lote estadoproducto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.ESTADOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina EstadoProducto por Clave.
     * @param id la clave de EstadoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el EstadoProducto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() estadoproducto: {}", id);


        try {
            EstadoProductoDTO estadoproductoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = estadoproductoMapper.eliminar(id);
            logeador.info("estadoproducto eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.ESTADOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote EstadoProducto por Clave.
     * @param estadoproductoDTOLote lista de claves de EstadoProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  EstadoProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<EstadoProductoDTO> estadoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (estadoproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           EstadoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.ESTADOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = estadoproductoMapper.eliminarLote(mapper.toEntityList(estadoproductoDTOLote));
            logeador.info("Lote estadoproducto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.ESTADOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un EstadoProducto por Clave.
     * @param id la clave EstadoProducto a encontrar.
     * @return el EstadoProducto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si EstadoProducto no es encontrado.
     */
    public EstadoProductoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            EstadoProductoDTO estadoproductoDTO = mapper.toDTO(estadoproductoMapper.encontrarPorClave(id));

            if (estadoproductoDTO != null) {
                logeador.info("estadoproducto encontrado por clave : {}", id);
            } else {
                logeador.info("estadoproducto clave:{} no encontrado codigoError:{}", id,
                              EstadoProductoError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(EstadoProductoError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.ESTADOPRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            return estadoproductoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.ESTADOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los EstadoProductos.
     * @return una lista de todos EstadoProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EstadoProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<EstadoProductoDTO> estadoproductoLista = mapper.toDTOList(estadoproductoMapper.obtenerTodos());
            logeador.info("estadoproductos obtenidos");
            return estadoproductoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOPRODUCTO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.ESTADOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
        }
    }

}
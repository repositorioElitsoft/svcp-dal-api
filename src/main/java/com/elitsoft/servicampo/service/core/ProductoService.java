package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ProductoDTO;
import com.elitsoft.servicampo.domain.entity.Producto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ProductoMapper;
import com.elitsoft.servicampo.mapstruct.ProductoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ProductoError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Producto.
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoMapper productoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ProductoService.class); //Logback

    /**
     * Agrega un nuevo Producto.
     * @param productoDTO el Producto DTO.
     * @return el Producto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Producto ya existe.
     */
    public ProductoDTO agregar(ProductoDTO productoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() Producto");

        //  Valida Entrada
        if (productoDTO == null) {
            logeador.error(Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           ProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Producto producto = mapper.toEntity(productoDTO);
            producto = productoMapper.agregar(producto);
            ProductoDTO productoDTOEncontrado =   this.encontrarPorClave(producto.getId());

            logeador.info("Producto agregado exitosamente id: {}", producto.getId());
            return productoDTOEncontrado;
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.PRODUCTO_DUPLICADO_MENSAGE + ": {}, codigoError:{}", productoDTO.getId(),
                           ProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.PRODUCTO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.PRODUCTO_AGREGAR_MENSAJE + ": {}, codigoError:{}", productoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.PRODUCTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Producto.
     * @param productoDTOLote lista de Producto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     * @throws RecursoDuplicadoException si el recurso producto ya existe.
     */
    public void agregarLote(List<ProductoDTO> productoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() producto");

        //  Valida Entrada
        if (productoDTOLote.isEmpty()) {
            logeador.error(Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          ProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Producto> productoLote = mapper.toEntityList(productoDTOLote);

            int registrosAgregados =  productoMapper.agregarLote(productoLote);
            logeador.info("Lote Producto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.PRODUCTO_DUPLICADO_MENSAGE + " codigoError:{}",
                          ProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.PRODUCTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PRODUCTO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.PRODUCTO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Producto existente.
     * @param id la clave de Producto a actualizar.
     * @param productoDTO el Producto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Producto no es encontrado.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     */
    public void actualizar(Long id, ProductoDTO productoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() producto");

        //  Valida Entrada
        if (id == null || productoDTO == null || productoDTO.getId() == null) {
            logeador.error(Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((productoDTO != null) ? productoDTO.toString() : null  ),
                           ProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(productoDTO.getId())) {
            logeador.error(Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  productoDTO.toString(),
                           ProductoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ProductoError.ID_INVALIDO.getCodigoError(),
                                                Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ProductoDTO productoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Producto producto = mapper.toEntity(productoDTO);
            producto.setId(id);
            int registrosActualizados = productoMapper.actualizar(producto);
            logeador.info("producto actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PRODUCTO_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, productoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.PRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Producto existentes.
     * @param productoDTOLote lista de Producto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     */
    public void actualizarLote(List<ProductoDTO> productoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() producto");

        //  Valida Entrada
        if (productoDTOLote.isEmpty()) {
            logeador.error(Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           ProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Producto> productoLote = mapper.toEntityList(productoDTOLote);
            int registrosActualizados = productoMapper.actualizarLote(productoLote);
            logeador.info("Lote producto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PRODUCTO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.PRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Producto por Clave.
     * @param id la clave de Producto a eliminar.
     * @throws RecursoNoEncontradoException si el Producto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() producto: {}", id);


        try {
            ProductoDTO productoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = productoMapper.eliminar(id);
            logeador.info("producto eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.PRODUCTO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.PRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Producto por Clave.
     * @param productoDTOLote lista de claves de Producto a eliminar.
     * @throws EntradaInvalidadException si la lista  Producto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ProductoDTO> productoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (productoDTOLote.isEmpty()) {
            logeador.error(Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           ProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.PRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = productoMapper.eliminarLote(mapper.toEntityList(productoDTOLote));
            logeador.info("Lote producto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PRODUCTO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.PRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Producto por Clave.
     * @param id la clave Producto a encontrar.
     * @return el Producto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Producto no es encontrado.
     */
    public ProductoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ProductoDTO productoDTO = mapper.toDTO(productoMapper.encontrarPorClave(id));

            if (productoDTO != null) {
                logeador.info("producto encontrado por clave : {}", id);
            } else {
                logeador.info("producto clave:{} no encontrado codigoError:{}", id,
                              ProductoError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ProductoError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.PRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            return productoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.PRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Productos.
     * @return una lista de todos Producto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ProductoDTO> productoLista = mapper.toDTOList(productoMapper.obtenerTodos());
            logeador.info("productos obtenidos");
            return productoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PRODUCTO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.PRODUCTO_OBTENER_TODOS_MENSAJE, e);
        }
    }

}
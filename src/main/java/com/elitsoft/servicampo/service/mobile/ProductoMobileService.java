package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ProductoMapper;
import com.elitsoft.servicampo.mapstruct.ProductoMapStruct;
import com.elitsoft.servicampo.service.core.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Producto.
 */
@Component
public class ProductoMobileService {

    @Autowired
    private ProductoMapper productoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ProductoService productoService; //Logica de Negocio del Core Service

    @Autowired
    private ProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ProductoMobileService.class); //Logback


    /**
     * Agrega un nuevo Producto.
     * @param productoDTO el Producto DTO.
     * @return el Producto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Producto ya existe.
     */
    public ProductoDTO agregar(ProductoDTO productoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() producto");

        return productoService.agregar(productoDTO);
    }

    /**
     * Agrega Lote nuevos Producto.
     * @param productoDTOLote lista de Producto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Producto ya existe.
     */
    public void agregarLote(List<ProductoDTO> productoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() producto");

        productoService.agregarLote(productoDTOLote);
    }

    /**
     * Actualiza un Producto existente.
     * @param id la Clave de Producto a actualizar.
     * @param productoDTO el Producto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Producto no es encontrado.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     */
    public void actualizar(Long id, ProductoDTO productoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() producto");

        productoService.actualizar(id, productoDTO);
    }

    /**
     * Actualiza Lote de Producto existentes.
     * @param productoDTOLote lista de Producto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Producto tiene errores.
     */
    public void actualizarLote(List<ProductoDTO> productoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() producto");

        productoService.actualizarLote(productoDTOLote);
    }

    /**
     * Elimina Producto por Clave.
     * @param id la clave de Producto a eliminar.
     * @throws RecursoNoEncontradoException si el Producto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() producto: {}", id);
        productoService.eliminar(id);
    }

    /**
     * Elimina Lote Producto por Clave.
     * @param productoDTOLote lista de claves de Producto a eliminar.
     * @throws EntradaInvalidadException si la lista  Producto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ProductoDTO> productoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        productoService.eliminarLote(productoDTOLote);
    }

    /**
     * Encuentra un Producto por Clave.
     * @param id la clave Producto a encontrar.
     * @return el Producto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Producto no es encontrado.
     */
    public ProductoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return productoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Productos.
     * @return lista de todos Producto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return productoService.obtenerTodos();
    }
}
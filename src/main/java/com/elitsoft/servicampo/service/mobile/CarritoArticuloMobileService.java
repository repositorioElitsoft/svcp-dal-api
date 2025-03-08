package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.CarritoArticuloDTO;
import com.elitsoft.servicampo.exceptions.CarritoArticuloLimiteException;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.CarritoArticuloNoEncontradoException;
import com.elitsoft.servicampo.mapper.CarritoArticuloMapper;
import com.elitsoft.servicampo.mapstruct.CarritoArticuloMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.elitsoft.servicampo.service.core.CarritoArticuloService;

import java.util.List;

/**
 * Gestiona las acciones relativas a CarritoArticulo para la version mobile
 */
@Component
public class CarritoArticuloMobileService {

    @Autowired
    private CarritoArticuloMapper carritoArticuloMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private CarritoArticuloService carritoArticuloService; //Logica de Negocio del Core Service

    @Autowired
    private CarritoArticuloMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(CarritoArticuloMobileService.class);

    /**
     * Agrega una Articulo de Carrito  a la base de datos
     * @param carritoArticuloDTO
     * @throws EntradaInvalidadException
     * @throws CarritoArticuloLimiteException
     * @throws BaseDatosException
     */
    public void agregar(CarritoArticuloDTO carritoArticuloDTO) throws EntradaInvalidadException, CarritoArticuloLimiteException, BaseDatosException {
        logeador.info("agregar() articulo de carrito" );
        carritoArticuloService.agregar(carritoArticuloDTO);
    }


    /**
     * @param id
     * @param carritoArticuloDTO
     * @throws BaseDatosException
     * @throws CarritoArticuloNoEncontradoException
     */
    public void actualizar(Long id, CarritoArticuloDTO carritoArticuloDTO) throws BaseDatosException, CarritoArticuloNoEncontradoException  {
        logeador.info("actualizar() articulo de carrito");
        carritoArticuloService.actualizar(id, carritoArticuloDTO);
    }


    /**
     * @param id
     * @throws BaseDatosException
     * @throws CarritoArticuloNoEncontradoException
     */
    public void eliminar(Long id) throws BaseDatosException, CarritoArticuloNoEncontradoException {
        logeador.info("eliminar() articulo de carrito: {}", id);
        carritoArticuloService.eliminar(id);
    }


    /**
     * @param id
     * @return
     * @throws BaseDatosException
     * @throws CarritoArticuloNoEncontradoException
     */
    public CarritoArticuloDTO encontrarPorClave(Long id) throws BaseDatosException, CarritoArticuloNoEncontradoException {
        logeador.info("encontrarPorClave(): {}", id);
        return carritoArticuloService.encontrarPorClave(id);
    }

    /**
     * Obtiene una lista de Articulos de Carrito
     * @return
     * @throws BaseDatosException
     */
    public List<CarritoArticuloDTO> obtenerTodos() throws BaseDatosException {
        logeador.info("obtenerTodos()");
        return carritoArticuloService.obtenerTodos ();
    }

}
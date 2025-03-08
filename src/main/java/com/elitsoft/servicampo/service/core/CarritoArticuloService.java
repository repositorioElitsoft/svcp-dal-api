package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.CarritoArticuloDTO;
import com.elitsoft.servicampo.domain.entity.CarritoArticulo;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.CarritoArticuloMapper;
import com.elitsoft.servicampo.mapstruct.CarritoArticuloMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Gestiona las acciones relativas a CarritoArticulo
 */
@Service
@Transactional
public class CarritoArticuloService {

    @Autowired
    private CarritoArticuloMapper carritoArticuloMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private CarritoArticuloMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(CarritoArticuloService.class);


    /**
     * Agrega una Articulo de Carrito  a la base de datos
     * @param carritoArticuloDTO
     * @throws EntradaInvalidadException
     * @throws CarritoArticuloLimiteException
     * @throws BaseDatosException
     */
    public void agregar(CarritoArticuloDTO carritoArticuloDTO) throws EntradaInvalidadException, CarritoArticuloLimiteException, BaseDatosException {
        logeador.info("agregar() articulo de carrito" );

        //  Valida Entrada (EntradaInvalidadException)
        if (carritoArticuloDTO == null || carritoArticuloDTO.getProductId() == null || carritoArticuloDTO.getQuantity() <= 0) {
            logeador.error(Constantes.CARRITO_ARTICULO_ENTRADA_INVALIDA + ": {}", carritoArticuloDTO.getProductId());
            throw new EntradaInvalidadException(Constantes.CARRITO_ARTICULO_ENTRADA_INVALIDA);
        }

        //  Revisa el limite Articulos del Carrito(CarritoArticuloLimiteException)
        List<CarritoArticulo> CarritoArticulosExistentes = carritoArticuloMapper.obtenerTodos();
        if (CarritoArticulosExistentes != null && CarritoArticulosExistentes.size() >= Constantes.CARRITO_ARTICULO_MAXIMO) {
            logeador.error(Constantes.CARRITO_ARTICULO_EXEDE_LIMITE + ": {}", carritoArticuloDTO.getProductId());
            throw new CarritoArticuloLimiteException(Constantes.CARRITO_ARTICULO_EXEDE_LIMITE);
        }


        try {
            CarritoArticulo carritoArticulo = mapper.toEntity(carritoArticuloDTO);
            Long nuevoId = carritoArticuloMapper.agregar(carritoArticulo);
            logeador.debug("Articulo de carrito agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRITO_ARTICULO_AGREGAR_EXECPTION +": {}", carritoArticuloDTO.toString(), e);
            throw new BaseDatosException(Constantes.CARRITO_ARTICULO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Articulo de Carrito identificado por su clave
     * @param id
     * @param carritoArticuloDTO
     * @throws CarritoArticuloNoEncontradoException
     * @throws BaseDatosException
     */
    public void actualizar(Long id, CarritoArticuloDTO carritoArticuloDTO) throws BaseDatosException, CarritoArticuloNoEncontradoException {
        logeador.info("actualizar() articulo de carrito");


        try {
            //  Revisa si el articulo de carrito existe (CarritoArticuloNoEncontradoException)
            CarritoArticuloDTO carritoArticuloDTOEncontrador = this.encontrarPorClave(id);

            CarritoArticulo carritoArticulo = mapper.toEntity(carritoArticuloDTO);
            carritoArticulo.setId(id);
            int registrosActualizados =  carritoArticuloMapper.actualizar(carritoArticulo);
            logeador.debug("articulo de carrito actualizado exitosamente: {}, registros actualizados: {}",id, registrosActualizados);
        } catch (CarritoArticuloNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRITO_ARTICULO_ACTUALIZAR_EXECPTION +": id={} {}", id, carritoArticuloDTO.toString(), e);
            throw new BaseDatosException(Constantes.CARRITO_ARTICULO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina un Articulo de Carrito identificado por su clave
     * @param id
     * @throws BaseDatosException
     */
    public void eliminar(Long id) throws BaseDatosException, CarritoArticuloNoEncontradoException {
        logeador.info("eliminar() articulo de carrito: {}", id);


        try {
            //  Revisa si el articulo de carrito existe (CarritoArticuloNoEncontradoException)
            CarritoArticuloDTO carritoArticuloDTO = this.encontrarPorClave(id);
            int registrosEliminados =  carritoArticuloMapper.eliminar (id);
            logeador.debug("articulo de carrito eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (CarritoArticuloNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRITO_ARTICULO_ELIMINAR_EXECPTION +": {}", id, e);
            throw new BaseDatosException(Constantes.CARRITO_ARTICULO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Filtra un Articulo de Carrito identificado por su clave
     * @param id
     * @return
     * @throws BaseDatosException
     */
    public CarritoArticuloDTO encontrarPorClave(Long id) throws BaseDatosException, CarritoArticuloNoEncontradoException {
        logeador.info("obtenerPorClave(): {}", id);

        try {
            CarritoArticuloDTO carritoArticuloDTO = mapper.toDto(carritoArticuloMapper.encontrarPorClave(id));

            if (carritoArticuloDTO!= null){
                logeador.debug("articulo de carrito encontrado por clave : {}", id);
            } else {
                logeador.debug("articulo de carrito clave:{} no encontrado", id);
                throw new CarritoArticuloNoEncontradoException(Constantes.CARRITO_ARTICULO_NO_ENCONTRADO_MENSAGE);
            }

            return carritoArticuloDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRITO_ARTICULO_ENCONTRAR_POR_CLAVE_EXECPTION +" {}", id, e);
            throw new BaseDatosException(Constantes.CARRITO_ARTICULO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene una lista de Articulos de Carrito
     * @return
     * @throws BaseDatosException
     */
    public List<CarritoArticuloDTO> obtenerTodos() throws BaseDatosException {
        logeador.info("obtenerTodos()");

        try {
            List<CarritoArticuloDTO> carritoArticuloLista = mapper.toDtoList(carritoArticuloMapper.obtenerTodos());
            logeador.debug("articulo de carrito obtenidos");
            return carritoArticuloLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRITO_ARTICULO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.CARRITO_ARTICULO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
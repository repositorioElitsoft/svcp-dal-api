package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDto;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad TipoProducto.
 */
@Service
@Transactional
public class TipoProductoService {

    @Autowired
    private TipoProductoMapper tipoproductoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoService.class);

    /**
     * Agrega un nuevo TipoProducto.
     * @param tipoproductoDto El TipoProducto DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TipoProductoDto tipoproductoDto) throws BaseDatosException {
        logeador.debug("agregar() tipoproducto");


        try {
            TipoProducto tipoproducto = mapper.toEntity(tipoproductoDto);
            Long nuevoId = tipoproductoMapper.agregar(tipoproducto);
            logeador.info("TipoProducto agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_AGREGAR_EXECPTION + ": {}", tipoproductoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un TipoProducto existente.
     * @param id La Clave de TipoProducto a actualizar.
     * @param tipoproductoDto El TipoProducto DTO con informacion actualizada.
     * @throws TipoProductoNoEncontradoException Si TipoProducto no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TipoProductoDto tipoproductoDto) throws TipoProductoNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() tipoproducto");

        try {
            TipoProductoDto tipoproductoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            TipoProducto tipoproducto = mapper.toEntity(tipoproductoDto);
            tipoproducto.setId(id);
            int registrosActualizados = tipoproductoMapper.actualizar(tipoproducto);
            logeador.info("tipoproducto actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (TipoProductoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ACTUALIZAR_EXECPTION + ": id={} {}", id, tipoproductoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina TipoProducto por Clave.
     * @param id La Clave de TipoProducto a eliminar.
     * @throws TipoProductoNoEncontradoException Si el TipoProducto no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws TipoProductoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoproducto: {}", id);

        try {
            TipoProductoDto tipoproductoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoproductoMapper.eliminar(id);
            logeador.info("tipoproducto eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (TipoProductoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un TipoProducto por Clave.
     * @param id La Clave TipoProducto a encontrar.
     * @return El TipoProducto DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TipoProductoNoEncontradoException Si TipoProducto no es encontrado.
     */
    public TipoProductoDto encontrarPorClave(Long id) throws BaseDatosException, TipoProductoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoProductoDto tipoproductoDto = mapper.toDto(tipoproductoMapper.encontrarPorClave(id));

            if (tipoproductoDto != null) {
                logeador.info("tipoproducto encontrado por clave : {}", id);
            } else {
                logeador.info("tipoproducto clave:{} no encontrado", id);
                throw new TipoProductoNoEncontradoException(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            return tipoproductoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los TipoProductos.
     * @return Una lista de todos TipoProducto DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TipoProductoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoProductoDto> tipoproductoList = mapper.toDtoList(tipoproductoMapper.obtenerTodos());
            logeador.info("tipoproductos obtenidos");
            return tipoproductoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
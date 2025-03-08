package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ModuloDTO;
import com.elitsoft.servicampo.domain.entity.Modulo;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ModuloMapper;
import com.elitsoft.servicampo.mapstruct.ModuloMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Modulo.
 */
@Service
@Transactional
public class ModuloService {

    @Autowired
    private ModuloMapper moduloMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ModuloMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ModuloService.class);

    /**
     * Agrega un nuevo Modulo.
     * @param moduloDTO El Modulo DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(ModuloDTO moduloDTO) throws BaseDatosException {
        logeador.debug("agregar() modulo");


        try {
            Modulo modulo = mapper.toEntity(moduloDTO);
            Long nuevoId = moduloMapper.agregar(modulo);
            logeador.info("Modulo agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.MODULO_AGREGAR_EXECPTION + ": {}", moduloDTO.toString(), e);
            throw new BaseDatosException(Constantes.MODULO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Modulo existente.
     * @param id La Clave de Modulo a actualizar.
     * @param moduloDTO El Modulo DTO con informacion actualizada.
     * @throws ModuloNoEncontradoException Si Modulo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, ModuloDTO moduloDTO) throws ModuloNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() modulo");

        try {
            ModuloDTO moduloDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Modulo modulo = mapper.toEntity(moduloDTO);
            modulo.setId(id);
            int registrosActualizados = moduloMapper.actualizar(modulo);
            logeador.info("modulo actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (ModuloNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MODULO_ACTUALIZAR_EXECPTION + ": id={} {}", id, moduloDTO.toString(), e);
            throw new BaseDatosException(Constantes.MODULO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Modulo por Clave.
     * @param id La Clave de Modulo a eliminar.
     * @throws ModuloNoEncontradoException Si el Modulo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws ModuloNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() modulo: {}", id);

        try {
            ModuloDTO moduloDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = moduloMapper.eliminar(id);
            logeador.info("modulo eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (ModuloNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MODULO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.MODULO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Modulo por Clave.
     * @param id La Clave Modulo a encontrar.
     * @return El Modulo DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws ModuloNoEncontradoException Si Modulo no es encontrado.
     */
    public ModuloDTO encontrarPorClave(Long id) throws BaseDatosException, ModuloNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ModuloDTO moduloDTO = mapper.toDto(moduloMapper.encontrarPorClave(id));

            if (moduloDTO != null) {
                logeador.info("modulo encontrado por clave : {}", id);
            } else {
                logeador.info("modulo clave:{} no encontrado", id);
                throw new ModuloNoEncontradoException(Constantes.MODULO_NO_ENCONTRADO_MENSAGE);
            }

            return moduloDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MODULO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.MODULO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Modulos.
     * @return Una lista de todos Modulo DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<ModuloDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ModuloDTO> moduloLista = mapper.toDtoList(moduloMapper.obtenerTodos());
            logeador.info("modulos obtenidos");
            return moduloLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MODULO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.MODULO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
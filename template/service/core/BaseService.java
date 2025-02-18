package com.elitsoft.#app_name#.service.core;

import com.elitsoft.#app_name#.domain.dto.core.#Base#Dto;
import com.elitsoft.#app_name#.domain.entity.#Base#;
import com.elitsoft.#app_name#.exceptions.*;
import com.elitsoft.#app_name#.mapper.#Base#Mapper;
import com.elitsoft.#app_name#.mapstruct.#Base#MapStruct;
import com.elitsoft.#app_name#.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad #Base#.
 */
@Service
@Transactional
public class #Base#Service {

    @Autowired
    private #Base#Mapper #base#Mapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private #Base#MapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(#Base#Service.class);

    /**
     * Agrega un nuevo #Base#.
     * @param #base#Dto El #Base# DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(#Base#Dto #base#Dto) throws BaseDatosException {
        logeador.debug("agregar() #base#");


        try {
            #Base# #base# = mapper.toEntity(#base#Dto);
            Long nuevoId = #base#Mapper.agregar(#base#);
            logeador.info("#Base# agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_AGREGAR_EXECPTION + ": {}", #base#Dto.toString(), e);
            throw new BaseDatosException(Constantes.#BASE#_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un #Base# existente.
     * @param id La Clave de #Base# a actualizar.
     * @param #base#Dto El #Base# DTO con informacion actualizada.
     * @throws #Base#NoEncontradoException Si #Base# no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, #Base#Dto #base#Dto) throws #Base#NoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() #base#");

        try {
            #Base#Dto #base#DtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            #Base# #base# = mapper.toEntity(#base#Dto);
            #base#.setId(id);
            int registrosActualizados = #base#Mapper.actualizar(#base#);
            logeador.info("#base# actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (#Base#NoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_ACTUALIZAR_EXECPTION + ": id={} {}", id, #base#Dto.toString(), e);
            throw new BaseDatosException(Constantes.#BASE#_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina #Base# por Clave.
     * @param id La Clave de #Base# a eliminar.
     * @throws #Base#NoEncontradoException Si el #Base# no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws #Base#NoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() #base#: {}", id);

        try {
            #Base#Dto #base#Dto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = #base#Mapper.eliminar(id);
            logeador.info("#base# eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (#Base#NoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.#BASE#_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un #Base# por Clave.
     * @param id La Clave #Base# a encontrar.
     * @return El #Base# DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws #Base#NoEncontradoException Si #Base# no es encontrado.
     */
    public #Base#Dto encontrarPorClave(Long id) throws BaseDatosException, #Base#NoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            #Base#Dto #base#Dto = mapper.toDto(#base#Mapper.encontrarPorClave(id));

            if (#base#Dto != null) {
                logeador.info("#base# encontrado por clave : {}", id);
            } else {
                logeador.info("#base# clave:{} no encontrado", id);
                throw new #Base#NoEncontradoException(Constantes.#BASE#_NO_ENCONTRADO_MENSAGE);
            }

            return #base#Dto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.#BASE#_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los #Base#s.
     * @return Una lista de todos #Base# DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<#Base#Dto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<#Base#Dto> #base#List = mapper.toDtoList(#base#Mapper.obtenerTodos());
            logeador.info("#base#s obtenidos");
            return #base#List;
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.#BASE#_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
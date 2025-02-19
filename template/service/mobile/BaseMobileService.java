package com.elitsoft.#app_name#.service.mobile;

import com.elitsoft.#app_name#.domain.dto.core.#Base#Dto;
import com.elitsoft.#app_name#.exceptions.BaseDatosException;
import com.elitsoft.#app_name#.exceptions.#Base#NoEncontradoException;
import com.elitsoft.#app_name#.mapper.#Base#Mapper;
import com.elitsoft.#app_name#.mapstruct.#Base#MapStruct;
import com.elitsoft.#app_name#.service.core.#Base#Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  #Base#.
 */
@Component
public class #Base#MobileService {

    @Autowired
    private #Base#Mapper #base#Mapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private #Base#Service #base#Service; //Logica de Negocio del Core Service

    @Autowired
    private #Base#MapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(#Base#MobileService.class);

    /**
     * Agrega un nuevo #Base#.
     * @param #base#Dto El #Base# DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(#Base#Dto #base#Dto) throws BaseDatosException {
        logeador.debug("agregar() #base#");
        #base#Service.agregar(#base#Dto);
    }

    /**
     * Actualiza un #Base# existente.
     * @param id La Clave de #Base# a actualizar.
     * @param #base#Dto El #Base# DTO con informacion actualizada.
     * @throws #Base#NoEncontradoException Si #Base# no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, #Base#Dto #base#Dto) throws BaseDatosException, #Base#NoEncontradoException {
        logeador.debug("actualizar() #base#");
        #base#Service.actualizar(id, #base#Dto);
    }

    /**
     * Elimina #Base# por Clave.
     * @param id La Clave de #Base# a eliminar.
     * @throws #Base#NoEncontradoException Si el #Base# no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, #Base#NoEncontradoException {
        logeador.debug("eliminar() #base#: {}", id);
        #base#Service.eliminar(id);
    }

    /**
     * Encuentra un #Base# por Clave.
     * @param id La Clave #Base# a encontrar.
     * @return El #Base# DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws #Base#NoEncontradoException Si #Base# no es encontrado.
     */
    public #Base#Dto encontrarPorClave(Long id) throws BaseDatosException, #Base#NoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return #base#Service.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los #Base#s.
     * @return Una lista de todos #Base# DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<#Base#Dto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return #base#Service.obtenerTodos();
    }
}
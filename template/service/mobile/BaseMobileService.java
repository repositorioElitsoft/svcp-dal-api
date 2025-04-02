package com.elitsoft.#app_name#.service.mobile;

import com.elitsoft.#app_name#.domain.dto.core.#Base#DTO;
import com.elitsoft.#app_name#.exceptions.BaseDatosException;
import com.elitsoft.#app_name#.exceptions.EntradaInvalidadException;
import com.elitsoft.#app_name#.exceptions.RecursoDuplicadoException;
import com.elitsoft.#app_name#.exceptions.RecursoNoEncontradoException;
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
    private #Base#MapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(#Base#MobileService.class); //Logback

    /**
     * Agrega un nuevo #Base#.
     * @param #base#DTO el #Base# DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     * @throws RecursoDuplicadoException si el recurso #Base# ya existe.
     */
    public void agregar(#Base#DTO #base#DTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() #base#");

        #base#Service.agregar(#base#DTO);
    }

    /**
     * Agrega un nuevo #Base#.
     * @param #base#DTO el #Base# DTO.
     * @return el #Base# DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     * @throws RecursoDuplicadoException si el recurso #Base# ya existe.
     */
    public #Base#DTO agregar(#Base#DTO #base#DTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() #base#");

        return #base#Service.agregar(#base#DTO);
    }

    /**
     * Agrega Lote nuevos #Base#.
     * @param #base#DTOLote lista de #Base# DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     * @throws RecursoDuplicadoException si el recurso #Base# ya existe.
     */
    public void agregarLote(List<#Base#DTO> #base#DTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() #base#");

        #base#Service.agregarLote(#base#DTOLote);
    }

    /**
     * Actualiza un #Base# existente.
     * @param id la Clave de #Base# a actualizar.
     * @param #base#DTO el #Base# DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si #Base# no es encontrado.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     */
    public void actualizar(Long id, #Base#DTO #base#DTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() #base#");

        #base#Service.actualizar(id, #base#DTO);
    }

    /**
     * Actualiza Lote de #Base# existentes.
     * @param #base#DTOLote lista de #Base# DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     */
    public void actualizarLote(List<#Base#DTO> #base#DTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() #base#");

        #base#Service.actualizarLote(#base#DTOLote);
    }

    /**
     * Elimina #Base# por Clave.
     * @param id la clave de #Base# a eliminar.
     * @throws RecursoNoEncontradoException si el #Base# no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si #Base# esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() #base#: {}", id);
        #base#Service.eliminar(id);
    }

    /**
     * Elimina Lote #Base# por Clave.
     * @param #base#DTOLote lista de claves de #Base# a eliminar.
     * @throws EntradaInvalidadException si la lista  #Base# esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si #Base# esta asociado a otro recurso
     */
    public void eliminarLote(List<#Base#DTO> #base#DTOLote) throws  BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        #base#Service.eliminarLote(#base#DTOLote);
    }

    /**
     * Encuentra un #Base# por Clave.
     * @param id la clave #Base# a encontrar.
     * @return el #Base# DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si #Base# no es encontrado.
     */
    public #Base#DTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return #base#Service.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los #Base#s.
     * @return lista de todos #Base# DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<#Base#DTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return #base#Service.obtenerTodos();
    }
}
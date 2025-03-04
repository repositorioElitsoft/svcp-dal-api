package com.elitsoft.#app_name#.service.core;

import com.elitsoft.#app_name#.domain.dto.core.#Base#DTO;
import com.elitsoft.#app_name#.domain.entity.#Base#;
import com.elitsoft.#app_name#.exceptions.*;
import com.elitsoft.#app_name#.mapper.#Base#Mapper;
import com.elitsoft.#app_name#.mapstruct.#Base#MapStruct;
import com.elitsoft.#app_name#.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad #Base#.
 */
@Service
public class #Base#Service {

    @Autowired
    private #Base#Mapper #base#Mapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private #Base#MapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(#Base#Service.class); //Logback

    /**
     * Agrega un nuevo #Base#.
     * @param #base#DTO el #Base# DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     * @throws RecursoDuplicadoException si el recurso #base# ya existe.
     */
    public void agregar(#Base#DTO #base#DTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() #base#");

        //  Valida Entrada
        if (#base#DTO == null || #base#DTO.getId() == null) {
            logeador.error(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE + ": {}", ((#base#DTO != null) ? #base#DTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            #Base# #base# = mapper.toEntity(#base#DTO);
            Long nuevoId = #base#Mapper.agregar(#base#);
            logeador.info("#Base# agregado exitosamente id: {}", nuevoId);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.#BASE#_DUPLICADO_MENSAGE + ": {}", #base#DTO.getId());
            throw new RecursoDuplicadoException(Constantes.#BASE#_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_AGREGAR_MENSAJE + ": {}", #base#DTO.toString(), e);
            throw new BaseDatosException(Constantes.#BASE#_AGREGAR_MENSAJE, e);
        }
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
        logeador.debug("agregar() #Base#");

        //  Valida Entrada
        if (#base#DTO == null) {
            logeador.error(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            #Base# #base# = mapper.toEntity(#base#DTO);
            #base# = #base#Mapper.agregar(#base#);
            logeador.info("#Base# agregado exitosamente id: {}", #base#.getId());
            return mapper.toDTO(#base#);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.#BASE#_DUPLICADO_MENSAGE + ": {}", #base#DTO.getId());
            throw new RecursoDuplicadoException(Constantes.#BASE#_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_AGREGAR_MENSAJE + ": {}", #base#DTO.toString(), e);
            throw new BaseDatosException(Constantes.#BASE#_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos #Base#.
     * @param #base#LoteDTO lista de #Base# DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     * @throws RecursoDuplicadoException si el recurso #base# ya existe.
     */
    public void agregarLote(List<#Base#DTO> #base#LoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() #base#");

        //  Valida Entrada
        if (#base#LoteDTO.isEmpty()) {
            logeador.error(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<#Base#> #base#Lote = mapper.toEntityList(#base#LoteDTO);

            int registrosAgregados =  #base#Mapper.agregarLote(#base#Lote);
            logeador.info("Lote #Base# agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.#BASE#_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.#BASE#_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.#BASE#_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.#BASE#_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un #Base# existente.
     * @param id la clave de #Base# a actualizar.
     * @param #base#DTO el #Base# DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si #Base# no es encontrado.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     */
    public void actualizar(Long id, #Base#DTO #base#DTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() #base#");

        //  Valida Entrada
        if (id == null || #base#DTO == null || #base#DTO.getId() == null) {
            logeador.error(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE + ": {}", ((#base#DTO != null) ? #base#DTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(#base#DTO.getId())) {
            logeador.error(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE + ": {}",  #base#DTO.toString());
            throw new EntradaInvalidadException(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            #Base#DTO #base#DTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            #Base# #base# = mapper.toEntity(#base#DTO);
            #base#.setId(id);
            int registrosActualizados = #base#Mapper.actualizar(#base#);
            logeador.info("#base# actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.#BASE#_ACTUALIZAR_MENSAJE + ": id={} {}", id, #base#DTO.toString(), e);
            throw new BaseDatosException(Constantes.#BASE#_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de #Base# existentes.
     * @param #base#LoteDTO lista de #Base# DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada #Base# tiene errores.
     */
    public void actualizarLote(List<#Base#DTO> #base#LoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() #base#");

        //  Valida Entrada
        if (#base#LoteDTO.isEmpty()) {
            logeador.error(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<#Base#> #base#Lote = mapper.toEntityList(#base#LoteDTO);
            int registrosActualizados = #base#Mapper.actualizarLote(#base#Lote);
            logeador.info("Lote #base# actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.#BASE#_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.#BASE#_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina #Base# por Clave.
     * @param id la clave de #Base# a eliminar.
     * @throws RecursoNoEncontradoException si el #Base# no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() #base#: {}", id);

        try {
            #Base#DTO #base#DTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = #base#Mapper.eliminar(id);
            logeador.info("#base# eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.#BASE#_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote #Base# por Clave.
     * @param idLote lista de claves de #Base# a eliminar.
     * @throws EntradaInvalidadException si la lista  #Base# esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.#BASE#_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = #base#Mapper.eliminarLote(idLote);
            logeador.info("Lote #base# eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.#BASE#_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.#BASE#_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un #Base# por Clave.
     * @param id la clave #Base# a encontrar.
     * @return el #Base# DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si #Base# no es encontrado.
     */
    public #Base#DTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            #Base#DTO #base#DTO = mapper.toDTO(#base#Mapper.encontrarPorClave(id));

            if (#base#DTO != null) {
                logeador.info("#base# encontrado por clave : {}", id);
            } else {
                logeador.info("#base# clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.#BASE#_NO_ENCONTRADO_MENSAGE);
            }

            return #base#DTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.#BASE#_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los #Base#s.
     * @return una lista de todos #Base# DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<#Base#DTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<#Base#DTO> #base#Lista = mapper.toDTOList(#base#Mapper.obtenerTodos());
            logeador.info("#base#s obtenidos");
            return #base#Lista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.#BASE#_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.#BASE#_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
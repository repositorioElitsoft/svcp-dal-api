package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.RoleDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.RoleMapper;
import com.elitsoft.servicampo.mapstruct.RoleMapStruct;
import com.elitsoft.servicampo.service.core.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Role.
 */
@Component
public class RoleMobileService {

    @Autowired
    private RoleMapper roleMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RoleService roleService; //Logica de Negocio del Core Service

    @Autowired
    private RoleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(RoleMobileService.class); //Logback


    /**
     * Agrega un nuevo Role.
     *
     * @param roleDTO el Role DTO.
     * @return el Role DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Role tiene errores.
     * @throws RecursoDuplicadoException si el recurso Role ya existe.
     */
    public RoleDTO agregar(RoleDTO roleDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() role");

        return roleService.agregar(roleDTO);
    }

    /**
     * Agrega Lote nuevos Role.
     *
     * @param roleLoteDTO lista de Role DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Role tiene errores.
     * @throws RecursoDuplicadoException si el recurso Role ya existe.
     */
    public void agregarLote(List<RoleDTO> roleLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() role");

        roleService.agregarLote(roleLoteDTO);
    }

    /**
     * Actualiza un Role existente.
     *
     * @param id      la Clave de Role a actualizar.
     * @param roleDTO el Role DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Role no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Role tiene errores.
     */
    public void actualizar(Long id, RoleDTO roleDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() role");

        roleService.actualizar(id, roleDTO);
    }

    /**
     * Actualiza Lote de Role existentes.
     *
     * @param roleLoteDTO lista de Role DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Role tiene errores.
     */
    public void actualizarLote(List<RoleDTO> roleLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() role");

        roleService.actualizarLote(roleLoteDTO);
    }

    /**
     * Elimina Role por Clave.
     *
     * @param id la clave de Role a eliminar.
     * @throws RecursoNoEncontradoException si el Role no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Role esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() role: {}", id);
        roleService.eliminar(id);
    }

    /**
     * Elimina Lote Role por Clave.
     *
     * @param idLote lista de claves de Role a eliminar.
     * @throws EntradaInvalidadException si la lista  Role esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Role esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        roleService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Role por Clave.
     *
     * @param id la clave Role a encontrar.
     * @return el Role DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Role no es encontrado.
     */
    public RoleDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return roleService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Roles.
     *
     * @return lista de todos Role DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<RoleDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return roleService.obtenerTodos();
    }
}
package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.RoleDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.RoleNoEncontradoException;
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
    private RoleMapper roleMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RoleService roleService; //Logica de Negocio del Core Service

    @Autowired
    private RoleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(RoleMobileService.class);

    /**
     * Agrega un nuevo Role.
     * @param roleDto El Role DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(RoleDto roleDto) throws BaseDatosException {
        logeador.debug("agregar() role");
        roleService.agregar(roleDto);
    }

    /**
     * Actualiza un Role existente.
     * @param id La Clave de Role a actualizar.
     * @param roleDto El Role DTO con informacion actualizada.
     * @throws RoleNoEncontradoException Si Role no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, RoleDto roleDto) throws BaseDatosException, RoleNoEncontradoException {
        logeador.debug("actualizar() role");
        roleService.actualizar(id, roleDto);
    }

    /**
     * Elimina Role por Clave.
     * @param id La Clave de Role a eliminar.
     * @throws RoleNoEncontradoException Si el Role no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, RoleNoEncontradoException {
        logeador.debug("eliminar() role: {}", id);
        roleService.eliminar(id);
    }

    /**
     * Encuentra un Role por Clave.
     * @param id La Clave Role a encontrar.
     * @return El Role DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws RoleNoEncontradoException Si Role no es encontrado.
     */
    public RoleDto encontrarPorClave(Long id) throws BaseDatosException, RoleNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return roleService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Roles.
     * @return Una lista de todos Role DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<RoleDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return roleService.obtenerTodos();
    }
}
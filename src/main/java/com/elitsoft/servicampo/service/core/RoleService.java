package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.RoleDto;
import com.elitsoft.servicampo.domain.entity.Role;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.RoleMapper;
import com.elitsoft.servicampo.mapstruct.RoleMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Role.
 */
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RoleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(RoleService.class);

    /**
     * Agrega un nuevo Role.
     * @param roleDto El Role DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(RoleDto roleDto) throws BaseDatosException {
        logeador.debug("agregar() role");


        try {
            Role role = mapper.toEntity(roleDto);
            Long nuevoId = roleMapper.agregar(role);
            logeador.info("Role agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_AGREGAR_EXECPTION + ": {}", roleDto.toString(), e);
            throw new BaseDatosException(Constantes.ROLE_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Role existente.
     * @param id La Clave de Role a actualizar.
     * @param roleDto El Role DTO con informacion actualizada.
     * @throws RoleNoEncontradoException Si Role no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, RoleDto roleDto) throws RoleNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() role");

        try {
            RoleDto roleDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Role role = mapper.toEntity(roleDto);
            role.setId(id);
            int registrosActualizados = roleMapper.actualizar(role);
            logeador.info("role actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (RoleNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_ACTUALIZAR_EXECPTION + ": id={} {}", id, roleDto.toString(), e);
            throw new BaseDatosException(Constantes.ROLE_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Role por Clave.
     * @param id La Clave de Role a eliminar.
     * @throws RoleNoEncontradoException Si el Role no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RoleNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() role: {}", id);

        try {
            RoleDto roleDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = roleMapper.eliminar(id);
            logeador.info("role eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (RoleNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.ROLE_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Role por Clave.
     * @param id La Clave Role a encontrar.
     * @return El Role DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws RoleNoEncontradoException Si Role no es encontrado.
     */
    public RoleDto encontrarPorClave(Long id) throws BaseDatosException, RoleNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            RoleDto roleDto = mapper.toDto(roleMapper.encontrarPorClave(id));

            if (roleDto != null) {
                logeador.info("role encontrado por clave : {}", id);
            } else {
                logeador.info("role clave:{} no encontrado", id);
                throw new RoleNoEncontradoException(Constantes.ROLE_NO_ENCONTRADO_MENSAGE);
            }

            return roleDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.ROLE_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Roles.
     * @return Una lista de todos Role DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<RoleDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<RoleDto> roleList = mapper.toDtoList(roleMapper.obtenerTodos());
            logeador.info("roles obtenidos");
            return roleList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.ROLE_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
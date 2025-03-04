package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.RoleDTO;
import com.elitsoft.servicampo.domain.entity.Role;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.RoleMapper;
import com.elitsoft.servicampo.mapstruct.RoleMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Role.
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RoleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(RoleService.class); //Logback

    /**
     * Agrega un nuevo Role.
     * @param roleDTO el Role DTO.
     * @return el Role DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Role tiene errores.
     * @throws RecursoDuplicadoException si el recurso Role ya existe.
     */
    public RoleDTO agregar(RoleDTO roleDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Role");

        //  Valida Entrada
        if (roleDTO == null) {
            logeador.error(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Role role = mapper.toEntity(roleDTO);
            role = roleMapper.agregar(role);
            logeador.info("Role agregado exitosamente id: {}", role.getId());
            return mapper.toDTO(role);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.ROLE_DUPLICADO_MENSAGE + ": {}", roleDTO.getId());
            throw new RecursoDuplicadoException(Constantes.ROLE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_AGREGAR_MENSAJE + ": {}", roleDTO.toString(), e);
            throw new BaseDatosException(Constantes.ROLE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Role.
     * @param roleLoteDTO lista de Role DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Role tiene errores.
     * @throws RecursoDuplicadoException si el recurso role ya existe.
     */
    public void agregarLote(List<RoleDTO> roleLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() role");

        //  Valida Entrada
        if (roleLoteDTO.isEmpty()) {
            logeador.error(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Role> roleLote = mapper.toEntityList(roleLoteDTO);

            int registrosAgregados =  roleMapper.agregarLote(roleLote);
            logeador.info("Lote Role agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ROLE_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.ROLE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ROLE_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.ROLE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Role existente.
     * @param id la clave de Role a actualizar.
     * @param roleDTO el Role DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Role no es encontrado.
     * @throws EntradaInvalidadException si la entrada Role tiene errores.
     */
    public void actualizar(Long id, RoleDTO roleDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() role");

        //  Valida Entrada
        if (id == null || roleDTO == null || roleDTO.getId() == null) {
            logeador.error(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE + ": {}", ((roleDTO != null) ? roleDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(roleDTO.getId())) {
            logeador.error(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE + ": {}",  roleDTO.toString());
            throw new EntradaInvalidadException(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            RoleDTO roleDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Role role = mapper.toEntity(roleDTO);
            role.setId(id);
            int registrosActualizados = roleMapper.actualizar(role);
            logeador.info("role actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ROLE_ACTUALIZAR_MENSAJE + ": id={} {}", id, roleDTO.toString(), e);
            throw new BaseDatosException(Constantes.ROLE_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Role existentes.
     * @param roleLoteDTO lista de Role DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Role tiene errores.
     */
    public void actualizarLote(List<RoleDTO> roleLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() role");

        //  Valida Entrada
        if (roleLoteDTO.isEmpty()) {
            logeador.error(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Role> roleLote = mapper.toEntityList(roleLoteDTO);
            int registrosActualizados = roleMapper.actualizarLote(roleLote);
            logeador.info("Lote role actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ROLE_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.ROLE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Role por Clave.
     * @param id la clave de Role a eliminar.
     * @throws RecursoNoEncontradoException si el Role no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() role: {}", id);

        try {
            RoleDTO roleDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = roleMapper.eliminar(id);
            logeador.info("role eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.ROLE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Role por Clave.
     * @param idLote lista de claves de Role a eliminar.
     * @throws EntradaInvalidadException si la lista  Role esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ROLE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = roleMapper.eliminarLote(idLote);
            logeador.info("Lote role eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ROLE_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.ROLE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Role por Clave.
     * @param id la clave Role a encontrar.
     * @return el Role DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Role no es encontrado.
     */
    public RoleDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            RoleDTO roleDTO = mapper.toDTO(roleMapper.encontrarPorClave(id));

            if (roleDTO != null) {
                logeador.info("role encontrado por clave : {}", id);
            } else {
                logeador.info("role clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.ROLE_NO_ENCONTRADO_MENSAGE);
            }

            return roleDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.ROLE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Roles.
     * @return una lista de todos Role DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<RoleDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<RoleDTO> roleLista = mapper.toDTOList(roleMapper.obtenerTodos());
            logeador.info("roles obtenidos");
            return roleLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ROLE_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.ROLE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.PermisoDTO;
import com.elitsoft.servicampo.domain.entity.Permiso;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.PermisoMapper;
import com.elitsoft.servicampo.mapstruct.PermisoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Permiso.
 */
@Service
@Transactional
public class PermisoService {

    @Autowired
    private PermisoMapper permisoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PermisoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PermisoService.class);

    /**
     * Agrega un nuevo Permiso.
     * @param permisoDTO El Permiso DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(PermisoDTO permisoDTO) throws BaseDatosException {
        logeador.debug("agregar() permiso");


        try {
            Permiso permiso = mapper.toEntity(permisoDTO);
            Long nuevoId = permisoMapper.agregar(permiso);
            logeador.info("Permiso agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.PERMISO_AGREGAR_EXECPTION + ": {}", permisoDTO.toString(), e);
            throw new BaseDatosException(Constantes.PERMISO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Permiso existente.
     * @param id La Clave de Permiso a actualizar.
     * @param permisoDTO El Permiso DTO con informacion actualizada.
     * @throws PermisoNoEncontradoException Si Permiso no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, PermisoDTO permisoDTO) throws PermisoNoEncontradoException, BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizar() permiso");

        //  Valida Entrada
        if (id == null || permisoDTO == null || permisoDTO.getId() == null) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((permisoDTO != null) ? permisoDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(permisoDTO.getId())) {
            logeador.error(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  permisoDTO.toString());
            throw new EntradaInvalidadException(Constantes.ESTADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            PermisoDTO permisoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Permiso permiso = mapper.toEntity(permisoDTO);
            permiso.setId(id);
            int registrosActualizados = permisoMapper.actualizar(permiso);
            logeador.info("permiso actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (PermisoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PERMISO_ACTUALIZAR_EXECPTION + ": id={} {}", id, permisoDTO.toString(), e);
            throw new BaseDatosException(Constantes.PERMISO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Permiso por Clave.
     * @param id La Clave de Permiso a eliminar.
     * @throws PermisoNoEncontradoException Si el Permiso no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws PermisoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() permiso: {}", id);

        try {
            PermisoDTO permisoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = permisoMapper.eliminar(id);
            logeador.info("permiso eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (PermisoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PERMISO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.PERMISO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Permiso por Clave.
     * @param id La Clave Permiso a encontrar.
     * @return El Permiso DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws PermisoNoEncontradoException Si Permiso no es encontrado.
     */
    public PermisoDTO encontrarPorClave(Long id) throws BaseDatosException, PermisoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            PermisoDTO permisoDTO = mapper.toDto(permisoMapper.encontrarPorClave(id));

            if (permisoDTO != null) {
                logeador.info("permiso encontrado por clave : {}", id);
            } else {
                logeador.info("permiso clave:{} no encontrado", id);
                throw new PermisoNoEncontradoException(Constantes.PERMISO_NO_ENCONTRADO_MENSAGE);
            }

            return permisoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PERMISO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.PERMISO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Permisos.
     * @return Una lista de todos Permiso DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<PermisoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<PermisoDTO> permisoLista = mapper.toDtoList(permisoMapper.obtenerTodos());
            logeador.info("permisos obtenidos");
            return permisoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PERMISO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.PERMISO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
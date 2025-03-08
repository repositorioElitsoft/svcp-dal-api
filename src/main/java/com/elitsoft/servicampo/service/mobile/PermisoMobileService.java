package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.PermisoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.PermisoNoEncontradoException;
import com.elitsoft.servicampo.mapper.PermisoMapper;
import com.elitsoft.servicampo.mapstruct.PermisoMapStruct;
import com.elitsoft.servicampo.service.core.PermisoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Permiso.
 */
@Component
public class PermisoMobileService {

    @Autowired
    private PermisoMapper permisoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PermisoService permisoService; //Logica de Negocio del Core Service

    @Autowired
    private PermisoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PermisoMobileService.class);

    /**
     * Agrega un nuevo Permiso.
     * @param permisoDTO El Permiso DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(PermisoDTO permisoDTO) throws BaseDatosException {
        logeador.debug("agregar() permiso");
        permisoService.agregar(permisoDTO);
    }

    /**
     * Actualiza un Permiso existente.
     * @param id La Clave de Permiso a actualizar.
     * @param permisoDTO El Permiso DTO con informacion actualizada.
     * @throws PermisoNoEncontradoException Si Permiso no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, PermisoDTO permisoDTO) throws BaseDatosException, PermisoNoEncontradoException {
        logeador.debug("actualizar() permiso");
        permisoService.actualizar(id, permisoDTO);
    }

    /**
     * Elimina Permiso por Clave.
     * @param id La Clave de Permiso a eliminar.
     * @throws PermisoNoEncontradoException Si el Permiso no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, PermisoNoEncontradoException {
        logeador.debug("eliminar() permiso: {}", id);
        permisoService.eliminar(id);
    }

    /**
     * Encuentra un Permiso por Clave.
     * @param id La Clave Permiso a encontrar.
     * @return El Permiso DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws PermisoNoEncontradoException Si Permiso no es encontrado.
     */
    public PermisoDTO encontrarPorClave(Long id) throws BaseDatosException, PermisoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return permisoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Permisos.
     * @return Una lista de todos Permiso DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<PermisoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return permisoService.obtenerTodos();
    }
}
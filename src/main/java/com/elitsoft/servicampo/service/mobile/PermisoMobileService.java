package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.PermisoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
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
     *
     * @param permisoDTO el Permiso DTO.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Permiso tiene errores.
     */
    public void agregar(PermisoDTO permisoDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("agregar() permiso");
        permisoService.agregar(permisoDTO);
    }

    /**
     * Actualiza un Permiso existente.
     *
     * @param id         la Clave de Permiso a actualizar.
     * @param permisoDTO el Permiso DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Permiso no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Permiso tiene errores.
     */
    public void actualizar(Long id, PermisoDTO permisoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() permiso");
        permisoService.actualizar(id, permisoDTO);
    }

    /**
     * Elimina Permiso por Clave.
     *
     * @param id la clave de Permiso a eliminar.
     * @throws RecursoNoEncontradoException si el Permiso no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si el Permiso está siendo utilizado por otros registros.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() permiso: {}", id);
        permisoService.eliminar(id);
    }

    /**
     * Encuentra un Permiso por Clave.
     *
     * @param id la clave Permiso a encontrar.
     * @return el Permiso DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Permiso no es encontrado.
     */
    public PermisoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return permisoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Permisos.
     *
     * @return lista de todos Permiso DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<PermisoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return permisoService.obtenerTodos();
    }
}
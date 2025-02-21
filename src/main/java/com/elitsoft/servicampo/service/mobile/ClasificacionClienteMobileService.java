package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.ClasificacionClienteNoEncontradoException;
import com.elitsoft.servicampo.mapper.ClasificacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClasificacionClienteMapStruct;
import com.elitsoft.servicampo.service.core.ClasificacionClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  ClasificacionCliente.
 */
@Component
public class ClasificacionClienteMobileService {

    @Autowired
    private ClasificacionClienteMapper clasificacionclienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClasificacionClienteService clasificacionclienteService; //Logica de Negocio del Core Service

    @Autowired
    private ClasificacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteMobileService.class);

    /**
     * Agrega un nuevo ClasificacionCliente.
     * @param clasificacionclienteDto El ClasificacionCliente DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(ClasificacionClienteDto clasificacionclienteDto) throws BaseDatosException {
        logeador.debug("agregar() clasificacioncliente");
        clasificacionclienteService.agregar(clasificacionclienteDto);
    }

    /**
     * Actualiza un ClasificacionCliente existente.
     * @param id La Clave de ClasificacionCliente a actualizar.
     * @param clasificacionclienteDto El ClasificacionCliente DTO con informacion actualizada.
     * @throws ClasificacionClienteNoEncontradoException Si ClasificacionCliente no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, ClasificacionClienteDto clasificacionclienteDto) throws BaseDatosException, ClasificacionClienteNoEncontradoException {
        logeador.debug("actualizar() clasificacioncliente");
        clasificacionclienteService.actualizar(id, clasificacionclienteDto);
    }

    /**
     * Elimina ClasificacionCliente por Clave.
     * @param id La Clave de ClasificacionCliente a eliminar.
     * @throws ClasificacionClienteNoEncontradoException Si el ClasificacionCliente no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, ClasificacionClienteNoEncontradoException {
        logeador.debug("eliminar() clasificacioncliente: {}", id);
        clasificacionclienteService.eliminar(id);
    }

    /**
     * Encuentra un ClasificacionCliente por Clave.
     * @param id La Clave ClasificacionCliente a encontrar.
     * @return El ClasificacionCliente DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws ClasificacionClienteNoEncontradoException Si ClasificacionCliente no es encontrado.
     */
    public ClasificacionClienteDto encontrarPorClave(Long id) throws BaseDatosException, ClasificacionClienteNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return clasificacionclienteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los ClasificacionClientes.
     * @return Una lista de todos ClasificacionCliente DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<ClasificacionClienteDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return clasificacionclienteService.obtenerTodos();
    }
}
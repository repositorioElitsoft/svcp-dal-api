package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EstadoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EstadoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EstadoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoMapStruct;
import com.elitsoft.servicampo.service.core.EstadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Estado.
 */
@Component
public class EstadoMobileService {

    @Autowired
    private EstadoMapper estadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoService estadoService; //Logica de Negocio del Core Service

    @Autowired
    private EstadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoMobileService.class);

    /**
     * Agrega un nuevo Estado.
     * @param estadoDto El Estado DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(EstadoDto estadoDto) throws BaseDatosException {
        logeador.debug("agregar() estado");
        estadoService.agregar(estadoDto);
    }

    /**
     * Actualiza un Estado existente.
     * @param id La Clave de Estado a actualizar.
     * @param estadoDto El Estado DTO con informacion actualizada.
     * @throws EstadoNoEncontradoException Si Estado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, EstadoDto estadoDto) throws BaseDatosException, EstadoNoEncontradoException {
        logeador.debug("actualizar() estado");
        estadoService.actualizar(id, estadoDto);
    }

    /**
     * Elimina Estado por Clave.
     * @param id La Clave de Estado a eliminar.
     * @throws EstadoNoEncontradoException Si el Estado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, EstadoNoEncontradoException {
        logeador.debug("eliminar() estado: {}", id);
        estadoService.eliminar(id);
    }

    /**
     * Encuentra un Estado por Clave.
     * @param id La Clave Estado a encontrar.
     * @return El Estado DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws EstadoNoEncontradoException Si Estado no es encontrado.
     */
    public EstadoDto encontrarPorClave(Long id) throws BaseDatosException, EstadoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return estadoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Estados.
     * @return Una lista de todos Estado DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<EstadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return estadoService.obtenerTodos();
    }
}
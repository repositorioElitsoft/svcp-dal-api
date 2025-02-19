package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.TrabajoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TrabajoMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoMapStruct;
import com.elitsoft.servicampo.service.core.TrabajoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Trabajo.
 */
@Component
public class TrabajoMobileService {

    @Autowired
    private TrabajoMapper trabajoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoService trabajoService; //Logica de Negocio del Core Service

    @Autowired
    private TrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoMobileService.class);

    /**
     * Agrega un nuevo Trabajo.
     * @param trabajoDto El Trabajo DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TrabajoDto trabajoDto) throws BaseDatosException {
        logeador.debug("agregar() trabajo");
        trabajoService.agregar(trabajoDto);
    }

    /**
     * Actualiza un Trabajo existente.
     * @param id La Clave de Trabajo a actualizar.
     * @param trabajoDto El Trabajo DTO con informacion actualizada.
     * @throws TrabajoNoEncontradoException Si Trabajo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TrabajoDto trabajoDto) throws BaseDatosException, TrabajoNoEncontradoException {
        logeador.debug("actualizar() trabajo");
        trabajoService.actualizar(id, trabajoDto);
    }

    /**
     * Elimina Trabajo por Clave.
     * @param id La Clave de Trabajo a eliminar.
     * @throws TrabajoNoEncontradoException Si el Trabajo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, TrabajoNoEncontradoException {
        logeador.debug("eliminar() trabajo: {}", id);
        trabajoService.eliminar(id);
    }

    /**
     * Encuentra un Trabajo por Clave.
     * @param id La Clave Trabajo a encontrar.
     * @return El Trabajo DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TrabajoNoEncontradoException Si Trabajo no es encontrado.
     */
    public TrabajoDto encontrarPorClave(Long id) throws BaseDatosException, TrabajoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return trabajoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Trabajos.
     * @return Una lista de todos Trabajo DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TrabajoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return trabajoService.obtenerTodos();
    }
}
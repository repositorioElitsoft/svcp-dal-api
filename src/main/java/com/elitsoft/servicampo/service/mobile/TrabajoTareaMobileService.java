package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TrabajoTareaDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.TrabajoTareaNoEncontradoException;
import com.elitsoft.servicampo.mapper.TrabajoTareaMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoTareaMapStruct;
import com.elitsoft.servicampo.service.core.TrabajoTareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TrabajoTarea.
 */
@Component
public class TrabajoTareaMobileService {

    @Autowired
    private TrabajoTareaMapper trabajotareaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoTareaService trabajotareaService; //Logica de Negocio del Core Service

    @Autowired
    private TrabajoTareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoTareaMobileService.class);

    /**
     * Agrega un nuevo TrabajoTarea.
     * @param trabajotareaDto El TrabajoTarea DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TrabajoTareaDto trabajotareaDto) throws BaseDatosException {
        logeador.debug("agregar() trabajotarea");
        trabajotareaService.agregar(trabajotareaDto);
    }

    /**
     * Actualiza un TrabajoTarea existente.
     * @param trabajoId La Clave compuesta de TrabajoTarea a actualizar.
     * @param tareaId La Clave compuesta de TrabajoTarea a actualizar.
     * @param trabajotareaDto El TrabajoTarea DTO con informacion actualizada.
     * @throws TrabajoTareaNoEncontradoException Si TrabajoTarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long trabajoId, Long tareaId, TrabajoTareaDto trabajotareaDto) throws BaseDatosException, TrabajoTareaNoEncontradoException {
        logeador.debug("actualizar() trabajotarea");
        trabajotareaService.actualizar(trabajoId, tareaId, trabajotareaDto);
    }

    /**
     * Elimina TrabajoTarea por Clave.
     * @param trabajoId La Clave compuesta de TrabajoTarea a eliminar.
     * @param tareaId La Clave compuesta de TrabajoTarea a eliminar.
     * @throws TrabajoTareaNoEncontradoException Si el TrabajoTarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long trabajoId, Long tareaId) throws BaseDatosException, TrabajoTareaNoEncontradoException {
        logeador.debug("eliminar() trabajotarea: {}, {}", trabajoId, tareaId);
        trabajotareaService.eliminar(trabajoId, tareaId);
    }

    /**
     * Encuentra un TrabajoTarea por Clave.
     * @param trabajoId La Clave compuesta TrabajoTarea a encontrar.
     * @param tareaId La Clave compuesta TrabajoTarea a encontrar.
     * @return El TrabajoTarea DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TrabajoTareaNoEncontradoException Si TrabajoTarea no es encontrado.
     */
    public TrabajoTareaDto encontrarPorClave(Long trabajoId, Long tareaId) throws BaseDatosException, TrabajoTareaNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}, {}", trabajoId, tareaId);
        return trabajotareaService.encontrarPorClave(trabajoId,tareaId );
    }

    /**
     * Obtiene todos los TrabajoTareas.
     * @return Una lista de todos TrabajoTarea DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TrabajoTareaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return trabajotareaService.obtenerTodos();
    }
}
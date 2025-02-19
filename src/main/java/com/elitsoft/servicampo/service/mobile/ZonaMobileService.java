package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ZonaDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.ZonaNoEncontradoException;
import com.elitsoft.servicampo.mapper.ZonaMapper;
import com.elitsoft.servicampo.mapstruct.ZonaMapStruct;
import com.elitsoft.servicampo.service.core.ZonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Zona.
 */
@Component
public class ZonaMobileService {

    @Autowired
    private ZonaMapper zonaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ZonaService zonaService; //Logica de Negocio del Core Service

    @Autowired
    private ZonaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ZonaMobileService.class);

    /**
     * Agrega un nuevo Zona.
     * @param zonaDto El Zona DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(ZonaDto zonaDto) throws BaseDatosException {
        logeador.debug("agregar() zona");
        zonaService.agregar(zonaDto);
    }

    /**
     * Actualiza un Zona existente.
     * @param id La Clave de Zona a actualizar.
     * @param zonaDto El Zona DTO con informacion actualizada.
     * @throws ZonaNoEncontradoException Si Zona no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, ZonaDto zonaDto) throws BaseDatosException, ZonaNoEncontradoException {
        logeador.debug("actualizar() zona");
        zonaService.actualizar(id, zonaDto);
    }

    /**
     * Elimina Zona por Clave.
     * @param id La Clave de Zona a eliminar.
     * @throws ZonaNoEncontradoException Si el Zona no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, ZonaNoEncontradoException {
        logeador.debug("eliminar() zona: {}", id);
        zonaService.eliminar(id);
    }

    /**
     * Encuentra un Zona por Clave.
     * @param id La Clave Zona a encontrar.
     * @return El Zona DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws ZonaNoEncontradoException Si Zona no es encontrado.
     */
    public ZonaDto encontrarPorClave(Long id) throws BaseDatosException, ZonaNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return zonaService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Zonas.
     * @return Una lista de todos Zona DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<ZonaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return zonaService.obtenerTodos();
    }
}
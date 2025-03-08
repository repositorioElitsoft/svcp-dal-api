package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.SectorDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.SectorMapper;
import com.elitsoft.servicampo.mapstruct.SectorMapStruct;
import com.elitsoft.servicampo.service.core.SectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Sector.
 */
@Component
public class SectorMobileService {

    @Autowired
    private SectorMapper sectorMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SectorService sectorService; //Logica de Negocio del Core Service

    @Autowired
    private SectorMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SectorMobileService.class); //Logback


    /**
     * Agrega un nuevo Sector.
     * @param sectorDTO el Sector DTO.
     * @return el Sector DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     * @throws RecursoDuplicadoException si el recurso Sector ya existe.
     */

    public SectorDTO agregar(SectorDTO sectorDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() sector");

        return sectorService.agregar(sectorDTO);
    }

    /**
     * Agrega Lote nuevos Sector.
     * @param sectorLoteDTO lista de Sector DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     * @throws RecursoDuplicadoException si el recurso Sector ya existe.
     */
    public void agregarLote(List<SectorDTO> sectorLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() sector");

        sectorService.agregarLote(sectorLoteDTO);
    }

    /**
     * Actualiza un Sector existente.
     * @param id la Clave de Sector a actualizar.
     * @param sectorDTO el Sector DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Sector no es encontrado.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     */
    public void actualizar(Long id, SectorDTO sectorDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() sector");

        sectorService.actualizar(id, sectorDTO);
    }

    /**
     * Actualiza Lote de Sector existentes.
     * @param sectorLoteDTO lista de Sector DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     */
    public void actualizarLote(List<SectorDTO> sectorLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() sector");

        sectorService.actualizarLote(sectorLoteDTO);
    }

    /**
     * Elimina Sector por Clave.
     * @param id la clave de Sector a eliminar.
     * @throws RecursoNoEncontradoException si el Sector no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() sector: {}", id);
        sectorService.eliminar(id);
    }

    /**
     * Elimina Lote Sector por Clave.
     * @param idLote lista de claves de Sector a eliminar.
     * @throws EntradaInvalidadException si la lista  Sector esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        sectorService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Sector por Clave.
     * @param id la clave Sector a encontrar.
     * @return el Sector DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Sector no es encontrado.
     */
    public SectorDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return sectorService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Sectors.
     * @return lista de todos Sector DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<SectorDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return sectorService.obtenerTodos();
    }
}
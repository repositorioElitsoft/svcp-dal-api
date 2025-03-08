package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.RegionDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.RegionMapper;
import com.elitsoft.servicampo.mapstruct.RegionMapStruct;
import com.elitsoft.servicampo.service.core.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Region.
 */
@Component
public class RegionMobileService {

    @Autowired
    private RegionMapper regionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RegionService regionService; //Logica de Negocio del Core Service

    @Autowired
    private RegionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(RegionMobileService.class); //Logback


    /**
     * Agrega un nuevo Region.
     * @param regionDTO el Region DTO.
     * @return el Region DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     * @throws RecursoDuplicadoException si el recurso Region ya existe.
     */
    public RegionDTO agregar(RegionDTO regionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() region");

        return regionService.agregar(regionDTO);
    }

    /**
     * Agrega Lote nuevos Region.
     * @param regionLoteDTO lista de Region DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     * @throws RecursoDuplicadoException si el recurso Region ya existe.
     */
    public void agregarLote(List<RegionDTO> regionLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() region");

        regionService.agregarLote(regionLoteDTO);
    }

    /**
     * Actualiza un Region existente.
     * @param id la Clave de Region a actualizar.
     * @param regionDTO el Region DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Region no es encontrado.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     */
    public void actualizar(Long id, RegionDTO regionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() region");

        regionService.actualizar(id, regionDTO);
    }

    /**
     * Actualiza Lote de Region existentes.
     * @param regionLoteDTO lista de Region DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     */
    public void actualizarLote(List<RegionDTO> regionLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() region");

        regionService.actualizarLote(regionLoteDTO);
    }

    /**
     * Elimina Region por Clave.
     * @param id la clave de Region a eliminar.
     * @throws RecursoNoEncontradoException si el Region no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() region: {}", id);
        regionService.eliminar(id);
    }

    /**
     * Elimina Lote Region por Clave.
     * @param idLote lista de claves de Region a eliminar.
     * @throws EntradaInvalidadException si la lista  Region esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        regionService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Region por Clave.
     * @param id la clave Region a encontrar.
     * @return el Region DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Region no es encontrado.
     */
    public RegionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return regionService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Regions.
     * @param paisId la Clave Pais a encontrar.
     * @return lista de todos Region DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<RegionDTO> obtenerTodos(Long paisId) throws BaseDatosException {
        logeador.debug("obtenerTodos() {}",paisId);
        return regionService.obtenerTodos(paisId);
    }
}
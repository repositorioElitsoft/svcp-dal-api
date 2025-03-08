package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.RegionDTO;
import com.elitsoft.servicampo.domain.entity.Region;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.RegionMapper;
import com.elitsoft.servicampo.mapstruct.RegionMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Region.
 */
@Service
public class RegionService {

    @Autowired
    private RegionMapper regionMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RegionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(RegionService.class); //Logback


    /**
     * Agrega un nuevo Region.
     * @param regionDTO el Region DTO.
     * @return el Region DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     * @throws RecursoDuplicadoException si el recurso Region ya existe.
     */
    public RegionDTO agregar(RegionDTO regionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Region");

        //  Valida Entrada
        if (regionDTO == null) {
            logeador.error(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Region region = mapper.toEntity(regionDTO);
            region = regionMapper.agregar(region);
            logeador.info("Region agregado exitosamente id: {}", region.getId());
            return mapper.toDto(region);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.REGION_DUPLICADO_MENSAGE + ": {}", regionDTO.getId());
            throw new RecursoDuplicadoException(Constantes.REGION_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.REGION_AGREGAR_MENSAJE + ": {}", regionDTO.toString(), e);
            throw new BaseDatosException(Constantes.REGION_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Region.
     * @param regionLoteDTO lista de Region DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     * @throws RecursoDuplicadoException si el recurso region ya existe.
     */
    public void agregarLote(List<RegionDTO> regionLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() region");

        //  Valida Entrada
        if (regionLoteDTO.isEmpty()) {
            logeador.error(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Region> regionLote = mapper.toEntityList(regionLoteDTO);

            int registrosAgregados =  regionMapper.agregarLote(regionLote);
            logeador.info("Lote Region agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.REGION_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.REGION_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.REGION_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.REGION_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Region existente.
     * @param id la clave de Region a actualizar.
     * @param regionDTO el Region DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Region no es encontrado.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     */
    public void actualizar(Long id, RegionDTO regionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() region");

        //  Valida Entrada
        if (id == null || regionDTO == null || regionDTO.getId() == null) {
            logeador.error(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE + ": {}", ((regionDTO != null) ? regionDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            RegionDTO regionDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Region region = mapper.toEntity(regionDTO);
            region.setId(id);
            int registrosActualizados = regionMapper.actualizar(region);
            logeador.info("region actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.REGION_ACTUALIZAR_MENSAJE + ": id={} {}", id, regionDTO.toString(), e);
            throw new BaseDatosException(Constantes.REGION_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Region existentes.
     * @param regionLoteDTO lista de Region DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Region tiene errores.
     */
    public void actualizarLote(List<RegionDTO> regionLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() region");

        //  Valida Entrada
        if (regionLoteDTO.isEmpty()) {
            logeador.error(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Region> regionLote = mapper.toEntityList(regionLoteDTO);
            int registrosActualizados = regionMapper.actualizarLote(regionLote);
            logeador.info("Lote region actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.REGION_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.REGION_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Region por Clave.
     * @param id la clave de Region a eliminar.
     * @throws RecursoNoEncontradoException si el Region no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() region: {}", id);

        try {
            RegionDTO regionDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = regionMapper.eliminar(id);
            logeador.info("region eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.REGION_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.REGION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Region por Clave.
     * @param idLote lista de claves de Region a eliminar.
     * @throws EntradaInvalidadException si la lista  Region esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.REGION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = regionMapper.eliminarLote(idLote);
            logeador.info("Lote region eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.REGION_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.REGION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Region por Clave.
     * @param id la clave Region a encontrar.
     * @return el Region DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Region no es encontrado.
     */
    public RegionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {

            RegionDTO regionDTO = mapper.toDto(regionMapper.encontrarPorClave(id));

            if (regionDTO != null) {
                logeador.info("region encontrado por clave : {}", id);
            } else {
                logeador.info("region clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.REGION_NO_ENCONTRADO_MENSAGE);
            }

            return regionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.REGION_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.REGION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Regions.
     * @param paisId la Clave Pais a encontrar.
     * @return una lista de todos Region DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<RegionDTO> obtenerTodos(Long paisId) throws BaseDatosException {
        logeador.debug("obtenerTodos() {}",paisId);

        try {
            List<RegionDTO> regionLista = mapper.toDtoList(regionMapper.obtenerTodos(paisId));
            logeador.info("regions obtenidos");
            return regionLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.REGION_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.REGION_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
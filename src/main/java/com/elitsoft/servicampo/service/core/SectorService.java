package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.SectorDto;
import com.elitsoft.servicampo.domain.entity.Sector;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.SectorMapper;
import com.elitsoft.servicampo.mapstruct.SectorMapStruct;
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
 * Clase de Servicio para la entidad Sector.
 */
@Service
public class SectorService {

    @Autowired
    private SectorMapper sectorMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SectorMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SectorService.class); //Logback


    /**
     * Agrega un nuevo Sector.
     * @param sectorDto el Sector DTO.
     * @return el Sector DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     * @throws RecursoDuplicadoException si el recurso Sector ya existe.
     */
    public SectorDto agregar(SectorDto sectorDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Sector");

        //  Valida Entrada
        if (sectorDto == null) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Sector sector = mapper.toEntity(sectorDto);
            sector = sectorMapper.agregar(sector);
            logeador.info("Sector agregado exitosamente id: {}", sector.getId());
            return mapper.toDto(sector);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.SECTOR_DUPLICADO_MENSAGE + ": {}", sectorDto.getId());
            throw new RecursoDuplicadoException(Constantes.SECTOR_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_AGREGAR_MENSAJE + ": {}", sectorDto.toString(), e);
            throw new BaseDatosException(Constantes.SECTOR_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Agrega Lote nuevos Sector.
     * @param sectorLoteDto lista de Sector DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     * @throws RecursoDuplicadoException si el recurso sector ya existe.
     */
    public void agregarLote(List<SectorDto> sectorLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() sector");

        //  Valida Entrada
        if (sectorLoteDto.isEmpty()) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Sector> sectorLote = mapper.toEntityList(sectorLoteDto);

            int registrosAgregados =  sectorMapper.agregarLote(sectorLote);
            logeador.info("Lote Sector agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.SECTOR_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.SECTOR_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.SECTOR_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Sector existente.
     * @param id la clave de Sector a actualizar.
     * @param sectorDto el Sector DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Sector no es encontrado.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     */
    public void actualizar(Long id, SectorDto sectorDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() sector");

        //  Valida Entrada
        if (id == null || sectorDto == null || sectorDto.getId() == null) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE + ": {}", ((sectorDto != null) ? sectorDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(sectorDto.getId())) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  sectorDto.toString());
            throw new EntradaInvalidadException(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            SectorDto sectorDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Sector sector = mapper.toEntity(sectorDto);
            sector.setId(id);
            int registrosActualizados = sectorMapper.actualizar(sector);
            logeador.info("sector actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_ACTUALIZAR_MENSAJE + ": id={} {}", id, sectorDto.toString(), e);
            throw new BaseDatosException(Constantes.SECTOR_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Sector existentes.
     * @param sectorLoteDto lista de Sector DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     */
    public void actualizarLote(List<SectorDto> sectorLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() sector");

        //  Valida Entrada
        if (sectorLoteDto.isEmpty()) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Sector> sectorLote = mapper.toEntityList(sectorLoteDto);
            int registrosActualizados = sectorMapper.actualizarLote(sectorLote);
            logeador.info("Lote sector actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.SECTOR_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Sector por Clave.
     * @param id la clave de Sector a eliminar.
     * @throws RecursoNoEncontradoException si el Sector no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() sector: {}", id);

        try {
            SectorDto sectorDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = sectorMapper.eliminar(id);
            logeador.info("sector eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.SECTOR_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Sector por Clave.
     * @param idLote lista de claves de Sector a eliminar.
     * @throws EntradaInvalidadException si la lista  Sector esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = sectorMapper.eliminarLote(idLote);
            logeador.info("Lote sector eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.SECTOR_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Sector por Clave.
     * @param id la clave Sector a encontrar.
     * @return el Sector DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Sector no es encontrado.
     */
    public SectorDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            SectorDto sectorDto = mapper.toDto(sectorMapper.encontrarPorClave(id));

            if (sectorDto != null) {
                logeador.info("sector encontrado por clave : {}", id);
            } else {
                logeador.info("sector clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.SECTOR_NO_ENCONTRADO_MENSAGE);
            }

            return sectorDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Sectors.
     * @return una lista de todos Sector DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<SectorDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<SectorDto> sectorList = mapper.toDtoList(sectorMapper.obtenerTodos());
            logeador.info("sectors obtenidos");
            return sectorList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.SECTOR_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
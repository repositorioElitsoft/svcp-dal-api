package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.SectorDTO;
import com.elitsoft.servicampo.domain.entity.Sector;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.SectorMapper;
import com.elitsoft.servicampo.mapstruct.SectorMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.SectorError;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
     *
     * @param sectorDTO el Sector DTO.
     * @return el Sector DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     * @throws RecursoDuplicadoException si el recurso Sector ya existe.
     */
    public SectorDTO agregar(SectorDTO sectorDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Sector");

        //  Valida Entrada
        if (sectorDTO == null) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(SectorError.REQUERIDO.getCodigoError(),
                    Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Sector sector = mapper.toEntity(sectorDTO);
            sector = sectorMapper.agregar(sector);
            logeador.info("Sector agregado exitosamente id: {}", sector.getId());
            return mapper.toDto(sector);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.SECTOR_DUPLICADO_MENSAGE + ": {}", sectorDTO.getId());
            throw new RecursoDuplicadoException(SectorError.DUPLICADO.getCodigoError(),
                    Constantes.SECTOR_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_AGREGAR_MENSAJE + ": {}", sectorDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Agrega Lote nuevos Sector.
     *
     * @param sectorLoteDTO lista de Sector DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     * @throws RecursoDuplicadoException si el recurso sector ya existe.
     */
    public void agregarLote(List<SectorDTO> sectorLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() sector");

        //  Valida Entrada
        if (sectorLoteDTO.isEmpty()) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(SectorError.REQUERIDO.getCodigoError(),
                    Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Sector> sectorLote = mapper.toEntityList(sectorLoteDTO);

            int registrosAgregados = sectorMapper.agregarLote(sectorLote);
            logeador.info("Lote Sector agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.SECTOR_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(SectorError.DUPLICADO.getCodigoError(),
                    Constantes.SECTOR_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Sector existente.
     *
     * @param id        la clave de Sector a actualizar.
     * @param sectorDTO el Sector DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Sector no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Sector tiene errores.
     */
    public void actualizar(Long id, SectorDTO sectorDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() sector");

        //  Valida Entrada
        if (id == null || sectorDTO == null || sectorDTO.getId() == null) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE + ": {}", ((sectorDTO != null) ? sectorDTO.toString() : null));
            throw new EntradaInvalidadException(SectorError.REQUERIDO.getCodigoError(),
                    Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(sectorDTO.getId())) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, sectorDTO.toString());
            throw new EntradaInvalidadException(SectorError.ID_INVALIDO.getCodigoError(),
                    Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            SectorDTO sectorDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Sector sector = mapper.toEntity(sectorDTO);
            sector.setId(id);
            int registrosActualizados = sectorMapper.actualizar(sector);
            logeador.info("sector actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_ACTUALIZAR_MENSAJE + ": id={} {}", id, sectorDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de Sector existentes.
     *
     * @param sectorLoteDTO lista de Sector DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Sector tiene errores.
     */
    public void actualizarLote(List<SectorDTO> sectorLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() sector");

        //  Valida Entrada
        if (sectorLoteDTO.isEmpty()) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(SectorError.REQUERIDO.getCodigoError(),
                    Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Sector> sectorLote = mapper.toEntityList(sectorLoteDTO);
            int registrosActualizados = sectorMapper.actualizarLote(sectorLote);
            logeador.info("Lote sector actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Sector por Clave.
     *
     * @param id la clave de Sector a eliminar.
     * @throws RecursoNoEncontradoException si el Sector no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si ocurre una violación de integridad.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() sector: {}", id);

        try {
            SectorDTO sectorDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = sectorMapper.eliminar(id);
            logeador.info("sector eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.SECTOR_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(SectorError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.SECTOR_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Sector por Clave.
     *
     * @param idLote lista de claves de Sector a eliminar.
     * @throws EntradaInvalidadException si la lista  Sector esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si ocurre una violación de integridad.
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(SectorError.REQUERIDO.getCodigoError(),
                    Constantes.SECTOR_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = sectorMapper.eliminarLote(idLote);
            logeador.info("Lote sector eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.SECTOR_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(SectorError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.SECTOR_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SECTOR_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Sector por Clave.
     *
     * @param id la clave Sector a encontrar.
     * @return el Sector DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Sector no es encontrado.
     */
    public SectorDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            SectorDTO sectorDTO = mapper.toDto(sectorMapper.encontrarPorClave(id));

            if (sectorDTO != null) {
                logeador.info("sector encontrado por clave : {}", id);
            } else {
                logeador.info("sector clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(SectorError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.SECTOR_NO_ENCONTRADO_MENSAGE);
            }

            return sectorDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Sectors.
     *
     * @param zonaId La clave de Zona a encontrar.
     * @return una lista de todos Sector DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<SectorDTO> obtenerTodos(Long zonaId) throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<SectorDTO> sectorLista = mapper.toDtoList(sectorMapper.obtenerTodos(zonaId));
            logeador.info("sectors obtenidos");
            return sectorLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SECTOR_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.SECTOR_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.SegmentacionClienteDto;
import com.elitsoft.servicampo.domain.entity.SegmentacionCliente;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.SegmentacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.SegmentacionClienteMapStruct;
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
 * Clase de Servicio para la entidad SegmentacionCliente.
 */
@Service
public class SegmentacionClienteService {

    @Autowired
    private SegmentacionClienteMapper segmentacionClienteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SegmentacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SegmentacionClienteService.class); //Logback


    /**
     * Agrega un nuevo SegmentacionCliente.
     * @param segmentacionClienteDto el SegmentacionCliente DTO.
     * @return el SegmentacionCliente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso SegmentacionCliente ya existe.
     */
    public SegmentacionClienteDto agregar(SegmentacionClienteDto segmentacionClienteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() SegmentacionCliente");

        //  Valida Entrada
        if (segmentacionClienteDto == null) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            SegmentacionCliente segmentacioncliente = mapper.toEntity(segmentacionClienteDto);
            segmentacioncliente = segmentacionClienteMapper.agregar(segmentacioncliente);
            logeador.info("SegmentacionCliente agregado exitosamente id: {}", segmentacioncliente.getId());
            return mapper.toDto(segmentacioncliente);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_DUPLICADO_MENSAGE + ": {}", segmentacionClienteDto.getId());
            throw new RecursoDuplicadoException(Constantes.SEGMENTACIONCLIENTE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_AGREGAR_MENSAJE + ": {}", segmentacionClienteDto.toString(), e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos SegmentacionCliente.
     * @param segmentacionClienteLoteDto lista de SegmentacionCliente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso segmentacioncliente ya existe.
     */
    public void agregarLote(List<SegmentacionClienteDto> segmentacionClienteLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() segmentacioncliente");

        //  Valida Entrada
        if (segmentacionClienteLoteDto.isEmpty()) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<SegmentacionCliente> segmentacionClienteLote = mapper.toEntityList(segmentacionClienteLoteDto);

            int registrosAgregados =  segmentacionClienteMapper.agregarLote(segmentacionClienteLote);
            logeador.info("Lote SegmentacionCliente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.SEGMENTACIONCLIENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un SegmentacionCliente existente.
     * @param id la clave de SegmentacionCliente a actualizar.
     * @param segmentacionClienteDto el SegmentacionCliente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si SegmentacionCliente no es encontrado.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     */
    public void actualizar(Long id, SegmentacionClienteDto segmentacionClienteDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() segmentacioncliente");

        //  Valida Entrada
        if (id == null || segmentacionClienteDto == null || segmentacionClienteDto.getId() == null) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}", ((segmentacionClienteDto != null) ? segmentacionClienteDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(segmentacionClienteDto.getId())) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  segmentacionClienteDto.toString());
            throw new EntradaInvalidadException(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            SegmentacionClienteDto segmentacionClienteDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            SegmentacionCliente segmentacionClienteliente = mapper.toEntity(segmentacionClienteDto);
            segmentacionClienteliente.setId(id);
            int registrosActualizados = segmentacionClienteMapper.actualizar(segmentacionClienteliente);
            logeador.info("segmentacioncliente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ACTUALIZAR_MENSAJE + ": id={} {}", id, segmentacionClienteDto.toString(), e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de SegmentacionCliente existentes.
     * @param segmentacionClienteLoteDto lista de SegmentacionCliente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     */
    public void actualizarLote(List<SegmentacionClienteDto> segmentacionClienteLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() segmentacioncliente");

        //  Valida Entrada
        if (segmentacionClienteLoteDto.isEmpty()) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<SegmentacionCliente> segmentacionClienteLote = mapper.toEntityList(segmentacionClienteLoteDto);
            int registrosActualizados = segmentacionClienteMapper.actualizarLote(segmentacionClienteLote);
            logeador.info("Lote segmentacioncliente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina SegmentacionCliente por Clave.
     * @param id la clave de SegmentacionCliente a eliminar.
     * @throws RecursoNoEncontradoException si el SegmentacionCliente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() segmentacioncliente: {}", id);

        try {
            SegmentacionClienteDto segmentacionClienteDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = segmentacionClienteMapper.eliminar(id);
            logeador.info("segmentacioncliente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote SegmentacionCliente por Clave.
     * @param idLote lista de claves de SegmentacionCliente a eliminar.
     * @throws EntradaInvalidadException si la lista  SegmentacionCliente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.SEGMENTACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = segmentacionClienteMapper.eliminarLote(idLote);
            logeador.info("Lote segmentacioncliente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un SegmentacionCliente por Clave.
     * @param id la clave SegmentacionCliente a encontrar.
     * @return el SegmentacionCliente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si SegmentacionCliente no es encontrado.
     */
    public SegmentacionClienteDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            SegmentacionClienteDto segmentacionClienteDto = mapper.toDto(segmentacionClienteMapper.encontrarPorClave(id));

            if (segmentacionClienteDto != null) {
                logeador.info("segmentacioncliente encontrado por clave : {}", id);
            } else {
                logeador.info("segmentacioncliente clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.SEGMENTACIONCLIENTE_NO_ENCONTRADO_MENSAGE);
            }

            return segmentacionClienteDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los SegmentacionClientes.
     * @return una lista de todos SegmentacionCliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<SegmentacionClienteDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<SegmentacionClienteDto> segmentacionClienteList = mapper.toDtoList(segmentacionClienteMapper.obtenerTodos());
            logeador.info("segmentacionclientes obtenidos");
            return segmentacionClienteList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SEGMENTACIONCLIENTE_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
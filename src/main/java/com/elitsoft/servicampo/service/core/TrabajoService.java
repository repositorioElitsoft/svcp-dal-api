package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDto;
import com.elitsoft.servicampo.domain.entity.Trabajo;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TrabajoMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoMapStruct;
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
 * Clase de Servicio para la entidad Trabajo.
 */
@Service
public class TrabajoService {

    @Autowired
    private TrabajoMapper trabajoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoService.class); //Logback

    /**
     * Agrega un nuevo Trabajo.
     * @param trabajoDto el Trabajo DTO.
     * @return el Trabajo DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso Trabajo ya existe.
     */
    public TrabajoDto agregar(TrabajoDto trabajoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Trabajo");

        //  Valida Entrada
        if (trabajoDto == null) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Trabajo trabajo = mapper.toEntity(trabajoDto);
            trabajo = trabajoMapper.agregar(trabajo);
            logeador.info("Trabajo agregado exitosamente id: {}", trabajo.getId());
            return mapper.toDto(trabajo);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TRABAJO_DUPLICADO_MENSAGE + ": {}", trabajoDto.getId());
            throw new RecursoDuplicadoException(Constantes.TRABAJO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_AGREGAR_MENSAJE + ": {}", trabajoDto.toString(), e);
            throw new BaseDatosException(Constantes.TRABAJO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Trabajo.
     * @param trabajoLoteDto lista de Trabajo DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso trabajo ya existe.
     */
    public void agregarLote(List<TrabajoDto> trabajoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() trabajo");

        //  Valida Entrada
        if (trabajoLoteDto.isEmpty()) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Trabajo> trabajoLote = mapper.toEntityList(trabajoLoteDto);

            int registrosAgregados =  trabajoMapper.agregarLote(trabajoLote);
            logeador.info("Lote Trabajo agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TRABAJO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.TRABAJO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.TRABAJO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Trabajo existente.
     * @param id la clave de Trabajo a actualizar.
     * @param trabajoDto el Trabajo DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Trabajo no es encontrado.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     */
    public void actualizar(Long id, TrabajoDto trabajoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() trabajo");

        //  Valida Entrada
        if (id == null || trabajoDto == null || trabajoDto.getId() == null) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((trabajoDto != null) ? trabajoDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TrabajoDto trabajoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Trabajo trabajo = mapper.toEntity(trabajoDto);
            trabajo.setId(id);
            int registrosActualizados = trabajoMapper.actualizar(trabajo);
            logeador.info("trabajo actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_ACTUALIZAR_MENSAJE + ": id={} {}", id, trabajoDto.toString(), e);
            throw new BaseDatosException(Constantes.TRABAJO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Trabajo existentes.
     * @param trabajoLoteDto lista de Trabajo DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     */
    public void actualizarLote(List<TrabajoDto> trabajoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() trabajo");

        //  Valida Entrada
        if (trabajoLoteDto.isEmpty()) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Trabajo> trabajoLote = mapper.toEntityList(trabajoLoteDto);
            int registrosActualizados = trabajoMapper.actualizarLote(trabajoLote);
            logeador.info("Lote trabajo actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.TRABAJO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Trabajo por Clave.
     * @param id la clave de Trabajo a eliminar.
     * @throws RecursoNoEncontradoException si el Trabajo no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() trabajo: {}", id);

        try {
            TrabajoDto trabajoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = trabajoMapper.eliminar(id);
            logeador.info("trabajo eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TRABAJO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Trabajo por Clave.
     * @param idLote lista de claves de Trabajo a eliminar.
     * @throws EntradaInvalidadException si la lista  Trabajo esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = trabajoMapper.eliminarLote(idLote);
            logeador.info("Lote trabajo eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TRABAJO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.TRABAJO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Trabajo por Clave.
     * @param id la clave Trabajo a encontrar.
     * @return el Trabajo DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Trabajo no es encontrado.
     */
    public TrabajoDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TrabajoDto trabajoDto = mapper.toDto(trabajoMapper.encontrarPorClave(id));

            if (trabajoDto != null) {
                logeador.info("trabajo encontrado por clave : {}", id);
            } else {
                logeador.info("trabajo clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE);
            }

            return trabajoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.TRABAJO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Trabajos.
     * @return una lista de todos Trabajo DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TrabajoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TrabajoDto> trabajoLista = mapper.toDtoList(trabajoMapper.obtenerTodos());
            logeador.info("trabajos obtenidos");
            return trabajoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.TRABAJO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
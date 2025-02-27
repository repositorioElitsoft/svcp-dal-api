package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoServicioDto;
import com.elitsoft.servicampo.domain.entity.TipoServicio;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoServicioMapper;
import com.elitsoft.servicampo.mapstruct.TipoServicioMapStruct;
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
 * Clase de Servicio para la entidad TipoServicio.
 */
@Service
public class TipoServicioService {

    @Autowired
    private TipoServicioMapper tiposervicioMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoServicioService.class); //Logback


    /**
     * Agrega un nuevo TipoServicio.
     * @param tiposervicioDto el TipoServicio DTO.
     * @return el TipoServicio DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoServicio ya existe.
     */
    public TipoServicioDto agregar(TipoServicioDto tiposervicioDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoServicio");

        //  Valida Entrada
        if (tiposervicioDto == null) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoServicio tiposervicio = mapper.toEntity(tiposervicioDto);
            tiposervicio = tiposervicioMapper.agregar(tiposervicio);
            logeador.info("TipoServicio agregado exitosamente id: {}", tiposervicio.getId());
            return mapper.toDto(tiposervicio);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE + ": {}", tiposervicioDto.getId());
            throw new RecursoDuplicadoException(Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_AGREGAR_MENSAJE + ": {}", tiposervicioDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoServicio.
     * @param tiposervicioLoteDto lista de TipoServicio DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso tiposervicio ya existe.
     */
    public void agregarLote(List<TipoServicioDto> tiposervicioLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tiposervicio");

        //  Valida Entrada
        if (tiposervicioLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoServicio> tiposervicioLote = mapper.toEntityList(tiposervicioLoteDto);

            int registrosAgregados =  tiposervicioMapper.agregarLote(tiposervicioLote);
            logeador.info("Lote TipoServicio agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.TIPOSERVICIO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoServicio existente.
     * @param id la clave de TipoServicio a actualizar.
     * @param tiposervicioDto el TipoServicio DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     */
    public void actualizar(Long id, TipoServicioDto tiposervicioDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tiposervicio");

        //  Valida Entrada
        if (id == null || tiposervicioDto == null || tiposervicioDto.getId() == null) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tiposervicioDto != null) ? tiposervicioDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoServicioDto tiposervicioDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoServicio tiposervicio = mapper.toEntity(tiposervicioDto);
            tiposervicio.setId(id);
            int registrosActualizados = tiposervicioMapper.actualizar(tiposervicio);
            logeador.info("tiposervicio actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE + ": id={} {}", id, tiposervicioDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoServicio existentes.
     * @param tiposervicioLoteDto lista de TipoServicio DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     */
    public void actualizarLote(List<TipoServicioDto> tiposervicioLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tiposervicio");

        //  Valida Entrada
        if (tiposervicioLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoServicio> tiposervicioLote = mapper.toEntityList(tiposervicioLoteDto);
            int registrosActualizados = tiposervicioMapper.actualizarLote(tiposervicioLote);
            logeador.info("Lote tiposervicio actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoServicio por Clave.
     * @param id la clave de TipoServicio a eliminar.
     * @throws RecursoNoEncontradoException si el TipoServicio no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tiposervicio: {}", id);

        try {
            TipoServicioDto tiposervicioDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tiposervicioMapper.eliminar(id);
            logeador.info("tiposervicio eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoServicio por Clave.
     * @param idLote lista de claves de TipoServicio a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoServicio esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOSERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tiposervicioMapper.eliminarLote(idLote);
            logeador.info("Lote tiposervicio eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoServicio por Clave.
     * @param id la clave TipoServicio a encontrar.
     * @return el TipoServicio DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     */
    public TipoServicioDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoServicioDto tiposervicioDto = mapper.toDto(tiposervicioMapper.encontrarPorClave(id));

            if (tiposervicioDto != null) {
                logeador.info("tiposervicio encontrado por clave : {}", id);
            } else {
                logeador.info("tiposervicio clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.TIPOSERVICIO_NO_ENCONTRADO_MENSAGE);
            }

            return tiposervicioDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoServicios.
     * @return una lista de todos TipoServicio DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoServicioDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoServicioDto> tiposervicioList = mapper.toDtoList(tiposervicioMapper.obtenerTodos());
            logeador.info("tiposervicios obtenidos");
            return tiposervicioList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOSERVICIO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOSERVICIO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
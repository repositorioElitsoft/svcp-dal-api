package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDto;
import com.elitsoft.servicampo.domain.entity.ClasificacionCliente;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ClasificacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClasificacionClienteMapStruct;
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
 * Clase de Servicio para la entidad ClasificacionCliente.
 */
@Service
public class ClasificacionClienteService {

    @Autowired
    private ClasificacionClienteMapper clasificacionclienteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClasificacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteService.class); //Logback

    /**
     * Agrega un nuevo ClasificacionCliente.
     * @param clasificacionclienteDto el ClasificacionCliente DTO.
     * @return el ClasificacionCliente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ClasificacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso ClasificacionCliente ya existe.
     */
    public ClasificacionClienteDto agregar(ClasificacionClienteDto clasificacionclienteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() ClasificacionCliente");

        //  Valida Entrada
        if (clasificacionclienteDto == null) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ClasificacionCliente clasificacionCliente = mapper.toEntity(clasificacionclienteDto);
            clasificacionCliente = clasificacionclienteMapper.agregar(clasificacionCliente);
            logeador.info("ClasificacionCliente agregado exitosamente id: {}", clasificacionCliente.getId());
            return mapper.toDto(clasificacionCliente);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_DUPLICADO_MENSAGE + ": {}", clasificacionclienteDto.getId());
            throw new RecursoDuplicadoException(Constantes.CLASIFICACIONCLIENTE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_AGREGAR_MENSAJE + ": {}", clasificacionclienteDto.toString(), e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Agrega Lote nuevos ClasificacionCliente.
     * @param clasificacionclienteLoteDto lista de ClasificacionCliente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ClasificacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso clasificacioncliente ya existe.
     */
    public void agregarLote(List<ClasificacionClienteDto> clasificacionclienteLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() clasificacioncliente");

        //  Valida Entrada
        if (clasificacionclienteLoteDto.isEmpty()) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<ClasificacionCliente> clasificacionclienteLote = mapper.toEntityList(clasificacionclienteLoteDto);

            int registrosAgregados =  clasificacionclienteMapper.agregarLote(clasificacionclienteLote);
            logeador.info("Lote ClasificacionCliente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.CLASIFICACIONCLIENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un ClasificacionCliente existente.
     * @param id la clave de ClasificacionCliente a actualizar.
     * @param clasificacionclienteDto el ClasificacionCliente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ClasificacionCliente no es encontrado.
     * @throws EntradaInvalidadException si la entrada ClasificacionCliente tiene errores.
     */
    public void actualizar(Long id, ClasificacionClienteDto clasificacionclienteDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() clasificacioncliente");

        //  Valida Entrada
        if (id == null || clasificacionclienteDto == null || clasificacionclienteDto.getId() == null) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}", ((clasificacionclienteDto != null) ? clasificacionclienteDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ClasificacionClienteDto clasificacionclienteDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            ClasificacionCliente clasificacioncliente = mapper.toEntity(clasificacionclienteDto);
            clasificacioncliente.setId(id);
            int registrosActualizados = clasificacionclienteMapper.actualizar(clasificacioncliente);
            logeador.info("clasificacioncliente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ACTUALIZAR_MENSAJE + ": id={} {}", id, clasificacionclienteDto.toString(), e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de ClasificacionCliente existentes.
     * @param clasificacionclienteLoteDto lista de ClasificacionCliente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ClasificacionCliente tiene errores.
     */
    public void actualizarLote(List<ClasificacionClienteDto> clasificacionclienteLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() clasificacioncliente");

        //  Valida Entrada
        if (clasificacionclienteLoteDto.isEmpty()) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<ClasificacionCliente> clasificacionclienteLote = mapper.toEntityList(clasificacionclienteLoteDto);
            int registrosActualizados = clasificacionclienteMapper.actualizarLote(clasificacionclienteLote);
            logeador.info("Lote clasificacioncliente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina ClasificacionCliente por Clave.
     * @param id la clave de ClasificacionCliente a eliminar.
     * @throws RecursoNoEncontradoException si el ClasificacionCliente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() clasificacioncliente: {}", id);

        try {
            ClasificacionClienteDto clasificacionclienteDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = clasificacionclienteMapper.eliminar(id);
            logeador.info("clasificacioncliente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote ClasificacionCliente por Clave.
     * @param idLote lista de claves de ClasificacionCliente a eliminar.
     * @throws EntradaInvalidadException si la lista  ClasificacionCliente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.CLASIFICACIONCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = clasificacionclienteMapper.eliminarLote(idLote);
            logeador.info("Lote clasificacioncliente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un ClasificacionCliente por Clave.
     * @param id la clave ClasificacionCliente a encontrar.
     * @return el ClasificacionCliente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ClasificacionCliente no es encontrado.
     */
    public ClasificacionClienteDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ClasificacionClienteDto clasificacionclienteDto = mapper.toDto(clasificacionclienteMapper.encontrarPorClave(id));

            if (clasificacionclienteDto != null) {
                logeador.info("clasificacioncliente encontrado por clave : {}", id);
            } else {
                logeador.info("clasificacioncliente clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.CLASIFICACIONCLIENTE_NO_ENCONTRADO_MENSAGE);
            }

            return clasificacionclienteDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los ClasificacionClientes.
     * @return una lista de todos ClasificacionCliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ClasificacionClienteDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ClasificacionClienteDto> clasificacionclienteList = mapper.toDtoList(clasificacionclienteMapper.obtenerTodos());
            logeador.info("clasificacionclientes obtenidos");
            return clasificacionclienteList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
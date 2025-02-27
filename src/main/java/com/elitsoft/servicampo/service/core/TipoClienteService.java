package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoClienteDto;
import com.elitsoft.servicampo.domain.entity.TipoCliente;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoClienteMapper;
import com.elitsoft.servicampo.mapstruct.TipoClienteMapStruct;
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
 * Clase de Servicio para la entidad TipoCliente.
 */
@Service
public class TipoClienteService {

    @Autowired
    private TipoClienteMapper tipoClienteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoClienteService.class); //Logback

    /**
     * Agrega un nuevo TipoCliente.
     * @param tipoClienteDto el TipoCliente DTO.
     * @return el TipoCliente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoCliente ya existe.
     */
    public TipoClienteDto agregar(TipoClienteDto tipoClienteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoCliente");

        //  Valida Entrada
        if (tipoClienteDto == null) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoCliente tipocliente = mapper.toEntity(tipoClienteDto);
            tipocliente = tipoClienteMapper.agregar(tipocliente);
            logeador.info("TipoCliente agregado exitosamente id: {}", tipocliente.getId());
            return mapper.toDto(tipocliente);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE + ": {}", tipoClienteDto.getId());
            throw new RecursoDuplicadoException(Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_AGREGAR_MENSAJE + ": {}", tipoClienteDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoCliente.
     * @param tipoClienteLoteDto lista de TipoCliente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipocliente ya existe.
     */
    public void agregarLote(List<TipoClienteDto> tipoClienteLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipocliente");

        //  Valida Entrada
        if (tipoClienteLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoCliente> tipoclienteLote = mapper.toEntityList(tipoClienteLoteDto);

            int registrosAgregados =  tipoClienteMapper.agregarLote(tipoclienteLote);
            logeador.info("Lote TipoCliente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoCliente existente.
     * @param id la clave de TipoCliente a actualizar.
     * @param tipoClienteDto el TipoCliente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoCliente no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     */
    public void actualizar(Long id, TipoClienteDto tipoClienteDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tipocliente");

        //  Valida Entrada
        if (id == null || tipoClienteDto == null || tipoClienteDto.getId() == null) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoClienteDto != null) ? tipoClienteDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoClienteDto tipoClienteDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoCliente tipocliente = mapper.toEntity(tipoClienteDto);
            tipocliente.setId(id);
            int registrosActualizados = tipoClienteMapper.actualizar(tipocliente);
            logeador.info("tipocliente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoClienteDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoCliente existentes.
     * @param tipoClienteLoteDto lista de TipoCliente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     */
    public void actualizarLote(List<TipoClienteDto> tipoClienteLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipocliente");

        //  Valida Entrada
        if (tipoClienteLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoCliente> tipoClienteLote = mapper.toEntityList(tipoClienteLoteDto);
            int registrosActualizados = tipoClienteMapper.actualizarLote(tipoClienteLote);
            logeador.info("Lote tipocliente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoCliente por Clave.
     * @param id la clave de TipoCliente a eliminar.
     * @throws RecursoNoEncontradoException si el TipoCliente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipocliente: {}", id);

        try {
            TipoClienteDto tipoClienteDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoClienteMapper.eliminar(id);
            logeador.info("tipocliente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoCliente por Clave.
     * @param idLote lista de claves de TipoCliente a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoCliente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tipoClienteMapper.eliminarLote(idLote);
            logeador.info("Lote tipocliente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoCliente por Clave.
     * @param id la clave TipoCliente a encontrar.
     * @return el TipoCliente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoCliente no es encontrado.
     */
    public TipoClienteDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoClienteDto tipoClienteDto = mapper.toDto(tipoClienteMapper.encontrarPorClave(id));

            if (tipoClienteDto != null) {
                logeador.info("tipocliente encontrado por clave : {}", id);
            } else {
                logeador.info("tipocliente clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.TIPOCLIENTE_NO_ENCONTRADO_MENSAGE);
            }

            return tipoClienteDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoClientes.
     * @return una lista de todos TipoCliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoClienteDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoClienteDto> tipoclienteList = mapper.toDtoList(tipoClienteMapper.obtenerTodos());
            logeador.info("tipoclientes obtenidos");
            return tipoclienteList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOCLIENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
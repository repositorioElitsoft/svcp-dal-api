package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoClienteDTO;
import com.elitsoft.servicampo.domain.entity.TipoCliente;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoClienteMapper;
import com.elitsoft.servicampo.mapstruct.TipoClienteMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TipoClienteError;
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
     *
     * @param tipoClienteDTO el TipoCliente DTO.
     * @return el TipoCliente DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoCliente ya existe.
     */
    public TipoClienteDTO agregar(TipoClienteDTO tipoClienteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoCliente");

        //  Valida Entrada
        if (tipoClienteDTO == null) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoClienteError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoCliente tipocliente = mapper.toEntity(tipoClienteDTO);
            tipocliente = tipoClienteMapper.agregar(tipocliente);
            logeador.info("TipoCliente agregado exitosamente id: {}", tipocliente.getId());
            return mapper.toDTO(tipocliente);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE + ": {}", tipoClienteDTO.getId());
            throw new RecursoDuplicadoException(TipoClienteError.DUPLICADO.getCodigoError(),
                    Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_AGREGAR_MENSAJE + ": {}", tipoClienteDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoCliente.
     *
     * @param tipoClienteLoteDTO lista de TipoCliente DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipocliente ya existe.
     */
    public void agregarLote(List<TipoClienteDTO> tipoClienteLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipocliente");

        //  Valida Entrada
        if (tipoClienteLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoClienteError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoCliente> tipoclienteLote = mapper.toEntityList(tipoClienteLoteDTO);

            int registrosAgregados = tipoClienteMapper.agregarLote(tipoclienteLote);
            logeador.info("Lote TipoCliente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TipoClienteError.DUPLICADO.getCodigoError(),
                    Constantes.TIPOCLIENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoCliente existente.
     *
     * @param id             la clave de TipoCliente a actualizar.
     * @param tipoClienteDTO el TipoCliente DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoCliente no es encontrado.
     * @throws EntradaInvalidadException    si la entrada TipoCliente tiene errores.
     */
    public void actualizar(Long id, TipoClienteDTO tipoClienteDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() tipocliente");

        //  Valida Entrada
        if (id == null || tipoClienteDTO == null || tipoClienteDTO.getId() == null) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoClienteDTO != null) ? tipoClienteDTO.toString() : null));
            throw new EntradaInvalidadException(TipoClienteError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipoClienteDTO.getId())) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, tipoClienteDTO.toString());
            throw new EntradaInvalidadException(TipoClienteError.ID_REQUERIDO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoCliente tipocliente = mapper.toEntity(tipoClienteDTO);
            tipocliente.setId(id);
            int registrosActualizados = tipoClienteMapper.actualizar(tipocliente);
            logeador.info("tipocliente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoClienteDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de TipoCliente existentes.
     *
     * @param tipoClienteLoteDTO lista de TipoCliente DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     */
    public void actualizarLote(List<TipoClienteDTO> tipoClienteLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipocliente");

        //  Valida Entrada
        if (tipoClienteLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoClienteError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoCliente> tipoClienteLote = mapper.toEntityList(tipoClienteLoteDTO);
            int registrosActualizados = tipoClienteMapper.actualizarLote(tipoClienteLote);
            logeador.info("Lote tipocliente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoCliente por Clave.
     *
     * @param id la clave de TipoCliente a eliminar.
     * @throws RecursoNoEncontradoException si el TipoCliente no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si TipoCliente esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() tipocliente: {}", id);

        try {
            this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoClienteMapper.eliminar(id);
            logeador.info("tipocliente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOCLIENTE_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TipoClienteError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TIPOCLIENTE_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoCliente por Clave.
     *
     * @param idLote lista de claves de TipoCliente a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoCliente esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TipoCliente esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoClienteError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tipoClienteMapper.eliminarLote(idLote);
            logeador.info("Lote tipocliente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOCLIENTE_VIOLACION_INTEGRIDAD_MENSAGE, e);
            throw new RecursoEliminarException(TipoClienteError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TIPOCLIENTE_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ELIMINAR_MENSAJE, e);
        }

    }

    /**
     * Encuentra un TipoCliente por Clave.
     *
     * @param id la clave TipoCliente a encontrar.
     * @return el TipoCliente DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoCliente no es encontrado.
     */
    public TipoClienteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoClienteDTO tipoClienteDTO = mapper.toDTO(tipoClienteMapper.encontrarPorClave(id));

            if (tipoClienteDTO != null) {
                logeador.info("tipocliente encontrado por clave : {}", id);
            } else {
                logeador.info("tipocliente clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TipoClienteError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.TIPOCLIENTE_NO_ENCONTRADO_MENSAGE);
            }

            return tipoClienteDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoClientes.
     *
     * @return una lista de todos TipoCliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoClienteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoClienteDTO> tipoClienteLista = mapper.toDTOList(tipoClienteMapper.obtenerTodos());
            logeador.info("tipoclientes obtenidos");
            return tipoClienteLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCLIENTE_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOCLIENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDTO;
import com.elitsoft.servicampo.domain.entity.TipoEmpleado;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EmpleadoMapper;
import com.elitsoft.servicampo.mapper.TipoEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.TipoEmpleadoMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TipoEmpleadoError;
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
 * Clase de Servicio para la entidad TipoEmpleado.
 */
@Service
public class TipoEmpleadoService {

    @Autowired
    private TipoEmpleadoMapper tipoEmpleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EmpleadoMapper empleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoService.class); //Logback


    /**
     * Agrega un nuevo TipoEmpleado.
     *
     * @param tipoEmpleadoDTO el TipoEmpleado DTO.
     * @return el TipoEmpleado DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoEmpleado ya existe.
     */
    public TipoEmpleadoDTO agregar(TipoEmpleadoDTO tipoEmpleadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoEmpleado");

        //  Valida Entrada
        if (tipoEmpleadoDTO == null) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoEmpleadoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoEmpleado tipoempleado = mapper.toEntity(tipoEmpleadoDTO);
            tipoempleado = tipoEmpleadoMapper.agregar(tipoempleado);
            logeador.info("TipoEmpleado agregado exitosamente id: {}", tipoempleado.getId());
            return mapper.toDTO(tipoempleado);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE + ": {}", tipoEmpleadoDTO.getId());
            throw new RecursoDuplicadoException(TipoEmpleadoError.DUPLICADO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_AGREGAR_MENSAJE + ": {}", tipoEmpleadoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoEmpleado.
     *
     * @param tipoEmpleadoLoteDTO lista de TipoEmpleado DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipoempleado ya existe.
     */
    public void agregarLote(List<TipoEmpleadoDTO> tipoEmpleadoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoempleado");

        //  Valida Entrada
        if (tipoEmpleadoLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoEmpleadoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoEmpleado> tipoEmpleadoLote = mapper.toEntityList(tipoEmpleadoLoteDTO);

            int registrosAgregados = tipoEmpleadoMapper.agregarLote(tipoEmpleadoLote);
            logeador.info("Lote TipoEmpleado agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TipoEmpleadoError.DUPLICADO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoEmpleado existente.
     *
     * @param id              la clave de TipoEmpleado a actualizar.
     * @param tipoEmpleadoDTO el TipoEmpleado DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     * @throws EntradaInvalidadException    si la entrada TipoEmpleado tiene errores.
     */
    public void actualizar(Long id, TipoEmpleadoDTO tipoEmpleadoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() tipoempleado");

        //  Valida Entrada
        if (id == null || tipoEmpleadoDTO == null || tipoEmpleadoDTO.getId() == null) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoEmpleadoDTO != null) ? tipoEmpleadoDTO.toString() : null));
            throw new EntradaInvalidadException(TipoEmpleadoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipoEmpleadoDTO.getId())) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, tipoEmpleadoDTO.toString());
            throw new EntradaInvalidadException(TipoEmpleadoError.ID_INVALIDO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoEmpleado tipoempleado = mapper.toEntity(tipoEmpleadoDTO);
            tipoempleado.setId(id);
            int registrosActualizados = tipoEmpleadoMapper.actualizar(tipoempleado);
            logeador.info("tipoempleado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoEmpleadoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de TipoEmpleado existentes.
     *
     * @param tipoEmpleadoLoteDTO lista de TipoEmpleado DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     */
    public void actualizarLote(List<TipoEmpleadoDTO> tipoEmpleadoLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoempleado");

        //  Valida Entrada
        if (tipoEmpleadoLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoEmpleadoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoEmpleado> tipoEmpleadoLote = mapper.toEntityList(tipoEmpleadoLoteDTO);
            int registrosActualizados = tipoEmpleadoMapper.actualizarLote(tipoEmpleadoLote);
            logeador.info("Lote tipoempleado actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoEmpleado por Clave.
     *
     * @param id la clave de TipoEmpleado a eliminar.
     * @throws RecursoNoEncontradoException si el TipoEmpleado no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si TipoEmpleado esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() tipoempleado: {}", id);


        try {
            TipoEmpleadoDTO tipoEmpleadoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoEmpleadoMapper.eliminar(id);
            logeador.info("tipoempleado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOEMPLEADO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TipoEmpleadoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TIPOEMPLEADO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoEmpleado por Clave.
     *
     * @param idLote lista de claves de TipoEmpleado a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoEmpleado esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TipoEmpleado esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoEmpleadoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = tipoEmpleadoMapper.eliminarLote(idLote);
            logeador.info("Lote tipoempleado eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOEMPLEADO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TipoEmpleadoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TIPOEMPLEADO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoEmpleado por Clave.
     *
     * @param id la clave TipoEmpleado a encontrar.
     * @return el TipoEmpleado DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     */
    public TipoEmpleadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoEmpleadoDTO tipoEmpleadoDTO = mapper.toDTO(tipoEmpleadoMapper.encontrarPorClave(id));

            if (tipoEmpleadoDTO != null) {
                logeador.info("tipoempleado encontrado por clave : {}", id);
            } else {
                logeador.info("tipoempleado clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TipoEmpleadoError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE);
            }

            return tipoEmpleadoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoEmpleados.
     *
     * @return una lista de todos TipoEmpleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoEmpleadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoEmpleadoDTO> tipoEmpleadoDTOLista = mapper.toDTOList(tipoEmpleadoMapper.obtenerTodos());
            logeador.info("tipoempleados obtenidos");
            return tipoEmpleadoDTOLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOEMPLEADO_OBTENER_TODOS_MENSAJE, e);
        }
    }

}
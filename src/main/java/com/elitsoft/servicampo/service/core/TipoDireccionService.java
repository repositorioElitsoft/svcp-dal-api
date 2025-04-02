package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoDireccionDTO;
import com.elitsoft.servicampo.domain.entity.TipoDireccion;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoDireccionMapper;
import com.elitsoft.servicampo.mapstruct.TipoDireccionMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TipoDireccionError;
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
 * Clase de Servicio para la entidad TipoDireccion.
 */
@Service
public class TipoDireccionService {

    @Autowired
    private TipoDireccionMapper tipodireccionMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoDireccionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoDireccionService.class); //Logback


    /**
     * Agrega un nuevo TipoDireccion.
     * @param tipodireccionDTO el TipoDireccion DTO.
     * @return el TipoDireccion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoDireccion ya existe.
     */
    public TipoDireccionDTO agregar(TipoDireccionDTO tipodireccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoDireccion");

        //  Valida Entrada
        if (tipodireccionDTO == null) {
            logeador.error(Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoDireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoDireccion tipodireccion = mapper.toEntity(tipodireccionDTO);
            tipodireccion = tipodireccionMapper.agregar(tipodireccion);
            logeador.info("TipoDireccion agregado exitosamente id: {}", tipodireccion.getId());
            return mapper.toDTO(tipodireccion);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPODIRECCION_DUPLICADO_MENSAGE + ": {}", tipodireccionDTO.getId());
            throw new RecursoDuplicadoException(TipoDireccionError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPODIRECCION_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPODIRECCION_AGREGAR_MENSAJE + ": {}", tipodireccionDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPODIRECCION_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoDireccion.
     * @param tipodireccionDTOLote lista de TipoDireccion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipodireccion ya existe.
     */
    public void agregarLote(List<TipoDireccionDTO> tipodireccionDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipodireccion");

        //  Valida Entrada
        if (tipodireccionDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoDireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoDireccion> tipodireccionLote = mapper.toEntityList(tipodireccionDTOLote);

            int registrosAgregados =  tipodireccionMapper.agregarLote(tipodireccionLote);
            logeador.info("Lote TipoDireccion agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPODIRECCION_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TipoDireccionError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPODIRECCION_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODIRECCION_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPODIRECCION_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoDireccion existente.
     * @param id la clave de TipoDireccion a actualizar.
     * @param tipodireccionDTO el TipoDireccion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDireccion no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     */
    public void actualizar(Long id, TipoDireccionDTO tipodireccionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tipodireccion");

        //  Valida Entrada
        if (id == null || tipodireccionDTO == null || tipodireccionDTO.getId() == null) {
            logeador.error(Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipodireccionDTO != null) ? tipodireccionDTO.toString() : null  ));
            throw new EntradaInvalidadException(TipoDireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipodireccionDTO.getId())) {
            logeador.error(Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {}",  tipodireccionDTO.toString());
            throw new EntradaInvalidadException(TipoDireccionError.ID_INVALIDO.getCodigoError(),
                                                Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoDireccionDTO tipodireccionDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoDireccion tipodireccion = mapper.toEntity(tipodireccionDTO);
            tipodireccion.setId(id);
            int registrosActualizados = tipodireccionMapper.actualizar(tipodireccion);
            logeador.info("tipodireccion actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODIRECCION_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipodireccionDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPODIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoDireccion existentes.
     * @param tipodireccionDTOLote lista de TipoDireccion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     */
    public void actualizarLote(List<TipoDireccionDTO> tipodireccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipodireccion");

        //  Valida Entrada
        if (tipodireccionDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoDireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoDireccion> tipodireccionLote = mapper.toEntityList(tipodireccionDTOLote);
            int registrosActualizados = tipodireccionMapper.actualizarLote(tipodireccionLote);
            logeador.info("Lote tipodireccion actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODIRECCION_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPODIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoDireccion por Clave.
     * @param id la clave de TipoDireccion a eliminar.
     * @throws RecursoNoEncontradoException si el TipoDireccion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipodireccion: {}", id);


        try {
            TipoDireccionDTO tipodireccionDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipodireccionMapper.eliminar(id);
            logeador.info("tipodireccion eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPODIRECCION_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPODIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoDireccion por Clave.
     * @param idLote lista de claves de TipoDireccion a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoDireccion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoDireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPODIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }



        try {
            int registrosEliminados = tipodireccionMapper.eliminarLote(idLote);
            logeador.info("Lote tipodireccion eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPODIRECCION_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPODIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoDireccion por Clave.
     * @param id la clave TipoDireccion a encontrar.
     * @return el TipoDireccion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDireccion no es encontrado.
     */
    public TipoDireccionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoDireccionDTO tipodireccionDTO = mapper.toDTO(tipodireccionMapper.encontrarPorClave(id));

            if (tipodireccionDTO != null) {
                logeador.info("tipodireccion encontrado por clave : {}", id);
            } else {
                logeador.info("tipodireccion clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TipoDireccionError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.TIPODIRECCION_NO_ENCONTRADO_MENSAGE);
            }

            return tipodireccionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPODIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPODIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoDireccions.
     * @return una lista de todos TipoDireccion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoDireccionDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoDireccionDTO> tipodireccionLista = mapper.toDTOList(tipodireccionMapper.obtenerTodos());
            logeador.info("tipodireccions obtenidos");
            return tipodireccionLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPODIRECCION_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPODIRECCION_OBTENER_TODOS_MENSAJE, e);
        }
    }




}
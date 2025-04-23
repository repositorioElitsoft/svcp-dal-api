package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.EstadoComponenteDTO;
import com.elitsoft.servicampo.domain.entity.EstadoComponente;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EstadoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.EstadoComponenteMapStruct;
import com.elitsoft.servicampo.service.error.EstadoComponenteError;
import com.elitsoft.servicampo.service.error.GeneralError;
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
 * Clase de Servicio para la entidad EstadoComponente.
 */
@Service
public class EstadoComponenteService {

    @Autowired
    private EstadoComponenteMapper estadocomponenteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoComponenteService.class); //Logback


    /**
     * Agrega un nuevo EstadoComponente.
     *
     * @param estadocomponenteDTO el EstadoComponente DTO.
     * @return el EstadoComponente DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso EstadoComponente ya existe.
     */
    public EstadoComponenteDTO agregar(EstadoComponenteDTO estadocomponenteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() EstadoComponente");

        //  Valida Entrada
        if (estadocomponenteDTO == null) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    EstadoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            EstadoComponente estadocomponente = mapper.toEntity(estadocomponenteDTO);
            estadocomponente = estadocomponenteMapper.agregar(estadocomponente);
            logeador.info("EstadoComponente agregado exitosamente id: {}", estadocomponente.getId());
            return mapper.toDTO(estadocomponente);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_DUPLICADO_MENSAGE + ": {}, codigoError:{}", estadocomponenteDTO.getId(),
                    EstadoComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(EstadoComponenteError.DUPLICADO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_AGREGAR_MENSAJE + ": {}, codigoError:{}", estadocomponenteDTO.toString(),
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos EstadoComponente.
     *
     * @param estadocomponenteDTOLote lista de EstadoComponente DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso estadocomponente ya existe.
     */
    public void agregarLote(List<EstadoComponenteDTO> estadocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() estadocomponente");

        //  Valida Entrada
        if (estadocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                    EstadoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<EstadoComponente> estadocomponenteLote = mapper.toEntityList(estadocomponenteDTOLote);

            int registrosAgregados = estadocomponenteMapper.agregarLote(estadocomponenteLote);
            logeador.info("Lote EstadoComponente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_DUPLICADO_MENSAGE + " codigoError:{}",
                    EstadoComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(EstadoComponenteError.DUPLICADO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un EstadoComponente existente.
     *
     * @param id                  la clave de EstadoComponente a actualizar.
     * @param estadocomponenteDTO el EstadoComponente DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si EstadoComponente no es encontrado.
     * @throws EntradaInvalidadException    si la entrada EstadoComponente tiene errores.
     */
    public void actualizar(Long id, EstadoComponenteDTO estadocomponenteDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() estadocomponente");

        //  Valida Entrada
        if (id == null || estadocomponenteDTO == null || estadocomponenteDTO.getId() == null) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((estadocomponenteDTO != null) ? estadocomponenteDTO.toString() : null),
                    EstadoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(estadocomponenteDTO.getId())) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", estadocomponenteDTO.toString(),
                    EstadoComponenteError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoComponenteError.ID_INVALIDO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            EstadoComponenteDTO estadocomponenteDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            EstadoComponente estadocomponente = mapper.toEntity(estadocomponenteDTO);
            estadocomponente.setId(id);
            int registrosActualizados = estadocomponenteMapper.actualizar(estadocomponente);
            logeador.info("estadocomponente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, estadocomponenteDTO.toString(),
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de EstadoComponente existentes.
     *
     * @param estadocomponenteDTOLote lista de EstadoComponente DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoComponente tiene errores.
     */
    public void actualizarLote(List<EstadoComponenteDTO> estadocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() estadocomponente");

        //  Valida Entrada
        if (estadocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    EstadoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<EstadoComponente> estadocomponenteLote = mapper.toEntityList(estadocomponenteDTOLote);
            int registrosActualizados = estadocomponenteMapper.actualizarLote(estadocomponenteLote);
            logeador.info("Lote estadocomponente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina EstadoComponente por Clave.
     *
     * @param id la clave de EstadoComponente a eliminar.
     * @throws RecursoNoEncontradoException si el EstadoComponente no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si EstadoComponente esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() estadocomponente: {}", id);


        try {
            this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = estadocomponenteMapper.eliminar(id);
            logeador.info("estadocomponente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(EstadoComponenteError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote EstadoComponente por Clave.
     *
     * @param estadocomponenteDTOLote lista de claves de EstadoComponente a eliminar.
     * @throws EntradaInvalidadException si la lista  EstadoComponente esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si EstadoComponente esta asociado a otro recurso
     */
    public void eliminarLote(List<EstadoComponenteDTO> estadocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (estadocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                    EstadoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(EstadoComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = estadocomponenteMapper.eliminarLote(mapper.toEntityList(estadocomponenteDTOLote));
            logeador.info("Lote estadocomponente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(EstadoComponenteError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ELIMINAR_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un EstadoComponente por Clave.
     *
     * @param id la clave EstadoComponente a encontrar.
     * @return el EstadoComponente DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si EstadoComponente no es encontrado.
     */
    public EstadoComponenteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            EstadoComponenteDTO estadocomponenteDTO = mapper.toDTO(estadocomponenteMapper.encontrarPorClave(id));

            if (estadocomponenteDTO != null) {
                logeador.info("estadocomponente encontrado por clave : {}", id);
            } else {
                logeador.info("estadocomponente clave:{} no encontrado codigoError:{}", id,
                        EstadoComponenteError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(EstadoComponenteError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.ESTADOCOMPONENTE_NO_ENCONTRADO_MENSAGE);
            }

            return estadocomponenteDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los EstadoComponentes.
     *
     * @return una lista de todos EstadoComponente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EstadoComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<EstadoComponenteDTO> estadoComponenteDTOLista = mapper.toDTOList(estadocomponenteMapper.obtenerTodos());
            logeador.info("estadocomponentes obtenidos");
            return estadoComponenteDTOLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADOCOMPONENTE_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.ESTADOCOMPONENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
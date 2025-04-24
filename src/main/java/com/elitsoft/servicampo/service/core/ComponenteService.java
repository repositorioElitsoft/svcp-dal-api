package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ComponenteDTO;
import com.elitsoft.servicampo.domain.entity.Componente;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ComponenteMapper;
import com.elitsoft.servicampo.mapstruct.ComponenteMapStruct;
import com.elitsoft.servicampo.service.error.ComponenteError;
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
 * Clase de Servicio para la entidad Componente.
 */
@Service
public class ComponenteService {

    @Autowired
    private ComponenteMapper componenteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ComponenteService.class); //Logback


    /**
     * Agrega un nuevo Componente.
     *
     * @param componenteDTO el Componente DTO.
     * @return el Componente DTO agregado con campo auto generado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws EntradaInvalidadException    si la entrada Componente tiene errores.
     * @throws RecursoDuplicadoException    si el recurso Componente ya existe.
     * @throws RecursoNoEncontradoException si Componente no es encontrado.
     */
    public ComponenteDTO agregar(ComponenteDTO componenteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() Componente");

        //  Valida Entrada
        if (componenteDTO == null) {
            logeador.error(Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    ComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Componente componente = mapper.toEntity(componenteDTO);
            componente = componenteMapper.agregar(componente);
            logeador.info("Componente agregado exitosamente id: {}", componente.getId());
            return this.encontrarPorClave(componente.getId());
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.COMPONENTE_DUPLICADO_MENSAGE + ": {}, codigoError:{}", componenteDTO.getId(),
                    ComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ComponenteError.DUPLICADO.getCodigoError(),
                    Constantes.COMPONENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.COMPONENTE_AGREGAR_MENSAJE + ": {}, codigoError:{}", componenteDTO.toString(),
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Componente.
     *
     * @param componenteDTOLote lista de Componente DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Componente tiene errores.
     * @throws RecursoDuplicadoException si el recurso componente ya existe.
     */
    public void agregarLote(List<ComponenteDTO> componenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() componente");

        //  Valida Entrada
        if (componenteDTOLote.isEmpty()) {
            logeador.error(Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                    ComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Componente> componenteLote = mapper.toEntityList(componenteDTOLote);

            int registrosAgregados = componenteMapper.agregarLote(componenteLote);
            logeador.info("Lote Componente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.COMPONENTE_DUPLICADO_MENSAGE + " codigoError:{}",
                    ComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ComponenteError.DUPLICADO.getCodigoError(),
                    Constantes.COMPONENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMPONENTE_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Componente existente.
     *
     * @param id            la clave de Componente a actualizar.
     * @param componenteDTO el Componente DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Componente no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Componente tiene errores.
     */
    public void actualizar(Long id, ComponenteDTO componenteDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() componente");

        //  Valida Entrada
        if (id == null || componenteDTO == null || componenteDTO.getId() == null) {
            logeador.error(Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((componenteDTO != null) ? componenteDTO.toString() : null),
                    ComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(componenteDTO.getId())) {
            logeador.error(Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", componenteDTO.toString(),
                    ComponenteError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ComponenteError.ID_INVALIDO.getCodigoError(),
                    Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida Codigo
        if (componenteDTO.getCodigo() == null || componenteDTO.getCodigo().isEmpty()) {
            logeador.error(Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", componenteDTO.toString(),
                    ComponenteError.CODiGO_REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ComponenteError.CODiGO_REQUERIDO.getCodigoError(),
                    Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            this.encontrarPorClave(id); // Verifica si existe el recurso
            Componente componente = mapper.toEntity(componenteDTO);
            componente.setId(id);
            int registrosActualizados = componenteMapper.actualizar(componente);
            logeador.info("componente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMPONENTE_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, componenteDTO.toString(),
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de Componente existentes.
     *
     * @param componenteDTOLote lista de Componente DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Componente tiene errores.
     */
    public void actualizarLote(List<ComponenteDTO> componenteDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() componente");

        //  Valida Entrada
        if (componenteDTOLote.isEmpty()) {
            logeador.error(Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    ComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Componente> componenteLote = mapper.toEntityList(componenteDTOLote);
            int registrosActualizados = componenteMapper.actualizarLote(componenteLote);
            logeador.info("Lote componente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMPONENTE_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Componente por Clave.
     *
     * @param id la clave de Componente a eliminar.
     * @throws RecursoNoEncontradoException si el Componente no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Componente esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() componente: {}", id);


        try {
            this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = componenteMapper.eliminar(id);
            logeador.info("componente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.COMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(ComponenteError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.COMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.COMPONENTE_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Componente por Clave.
     *
     * @param componenteDTOLote lista de claves de Componente a eliminar.
     * @throws EntradaInvalidadException si la lista  Componente esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Componente esta asociado a otro recurso
     */
    public void eliminarLote(List<ComponenteDTO> componenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (componenteDTOLote.isEmpty()) {
            logeador.error(Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                    ComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ComponenteError.REQUERIDO.getCodigoError(),
                    Constantes.COMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = componenteMapper.eliminarLote(mapper.toEntityList(componenteDTOLote));
            logeador.info("Lote componente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.COMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(ComponenteError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.COMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMPONENTE_ELIMINAR_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Componente por Clave.
     *
     * @param id la clave Componente a encontrar.
     * @return el Componente DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Componente no es encontrado.
     */
    public ComponenteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ComponenteDTO componenteDTO = mapper.toDTO(componenteMapper.encontrarPorClave(id));

            if (componenteDTO != null) {
                logeador.info("componente encontrado por clave : {}", id);
            } else {
                logeador.info("componente clave:{} no encontrado codigoError:{}", id,
                        ComponenteError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ComponenteError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.COMPONENTE_NO_ENCONTRADO_MENSAGE);
            }

            return componenteDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.COMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Componentes.
     *
     * @return una lista de todos Componente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ComponenteDTO> componenteDTOLista = mapper.toDTOList(componenteMapper.obtenerTodos());
            logeador.info("componentes obtenidos");
            return componenteDTOLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.COMPONENTE_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.COMPONENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
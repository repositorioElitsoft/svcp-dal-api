package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ComponenteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ComponenteMapper;
import com.elitsoft.servicampo.mapstruct.ComponenteMapStruct;
import com.elitsoft.servicampo.service.core.ComponenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Componente.
 */
@Service
public class ComponenteMobileService {

    @Autowired
    private ComponenteMapper componenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ComponenteService componenteService; //Logica de Negocio del Core Service

    @Autowired
    private ComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ComponenteMobileService.class); //Logback


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
        logeador.debug("agregar() componente");

        return componenteService.agregar(componenteDTO);
    }

    /**
     * Agrega Lote nuevos Componente.
     *
     * @param componenteDTOLote lista de Componente DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Componente tiene errores.
     * @throws RecursoDuplicadoException si el recurso Componente ya existe.
     */
    public void agregarLote(List<ComponenteDTO> componenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() componente");

        componenteService.agregarLote(componenteDTOLote);
    }

    /**
     * Actualiza un Componente existente.
     *
     * @param id            la Clave de Componente a actualizar.
     * @param componenteDTO el Componente DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Componente no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Componente tiene errores.
     */
    public void actualizar(Long id, ComponenteDTO componenteDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() componente");

        componenteService.actualizar(id, componenteDTO);
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

        componenteService.actualizarLote(componenteDTOLote);
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
        componenteService.eliminar(id);
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

        componenteService.eliminarLote(componenteDTOLote);
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
        logeador.debug("encontrarPorClave(): {}", id);
        return componenteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Componentes.
     *
     * @return lista de todos Componente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return componenteService.obtenerTodos();
    }
}
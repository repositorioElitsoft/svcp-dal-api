package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoComponenteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.TipoComponenteMapStruct;
import com.elitsoft.servicampo.service.core.TipoComponenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoComponente.
 */
@Component
public class TipoComponenteMobileService {

    @Autowired
    private TipoComponenteMapper tipocomponenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoComponenteService tipocomponenteService; //Logica de Negocio del Core Service

    @Autowired
    private TipoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoComponenteMobileService.class); //Logback

    /**
     * Agrega un nuevo TipoComponente.
     *
     * @param tipocomponenteDTO el TipoComponente DTO.
     * @return el TipoComponente DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoComponente ya existe.
     */
    public TipoComponenteDTO agregar(TipoComponenteDTO tipocomponenteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipocomponente");

        return tipocomponenteService.agregar(tipocomponenteDTO);
    }

    /**
     * Agrega Lote nuevos TipoComponente.
     *
     * @param tipocomponenteDTOLote lista de TipoComponente DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoComponente ya existe.
     */
    public void agregarLote(List<TipoComponenteDTO> tipocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipocomponente");

        tipocomponenteService.agregarLote(tipocomponenteDTOLote);
    }

    /**
     * Actualiza un TipoComponente existente.
     *
     * @param id                la Clave de TipoComponente a actualizar.
     * @param tipocomponenteDTO el TipoComponente DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoComponente no es encontrado.
     * @throws EntradaInvalidadException    si la entrada TipoComponente tiene errores.
     */
    public void actualizar(Long id, TipoComponenteDTO tipocomponenteDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() tipocomponente");

        tipocomponenteService.actualizar(id, tipocomponenteDTO);
    }

    /**
     * Actualiza Lote de TipoComponente existentes.
     *
     * @param tipocomponenteDTOLote lista de TipoComponente DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoComponente tiene errores.
     */
    public void actualizarLote(List<TipoComponenteDTO> tipocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipocomponente");

        tipocomponenteService.actualizarLote(tipocomponenteDTOLote);
    }

    /**
     * Elimina TipoComponente por Clave.
     *
     * @param id la clave de TipoComponente a eliminar.
     * @throws RecursoNoEncontradoException si el TipoComponente no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si TipoComponente esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() tipocomponente: {}", id);
        tipocomponenteService.eliminar(id);
    }

    /**
     * Elimina Lote TipoComponente por Clave.
     *
     * @param tipocomponenteDTOLote lista de claves de TipoComponente a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoComponente esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TipoComponente esta asociado a otro recurso
     */
    public void eliminarLote(List<TipoComponenteDTO> tipocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        tipocomponenteService.eliminarLote(tipocomponenteDTOLote);
    }

    /**
     * Encuentra un TipoComponente por Clave.
     *
     * @param id la clave TipoComponente a encontrar.
     * @return el TipoComponente DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoComponente no es encontrado.
     */
    public TipoComponenteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipocomponenteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoComponentes.
     *
     * @return lista de todos TipoComponente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipocomponenteService.obtenerTodos();
    }
}
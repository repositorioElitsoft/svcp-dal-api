package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EstadoComponenteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EstadoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.EstadoComponenteMapStruct;
import com.elitsoft.servicampo.service.core.EstadoComponenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  EstadoComponente.
 */
@Service
public class EstadoComponenteMobileService {

    @Autowired
    private EstadoComponenteMapper estadocomponenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoComponenteService estadocomponenteService; //Logica de Negocio del Core Service

    @Autowired
    private EstadoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoComponenteMobileService.class); //Logback


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
        logeador.debug("agregar() estadocomponente");

        return estadocomponenteService.agregar(estadocomponenteDTO);
    }

    /**
     * Agrega Lote nuevos EstadoComponente.
     *
     * @param estadocomponenteDTOLote lista de EstadoComponente DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso EstadoComponente ya existe.
     */
    public void agregarLote(List<EstadoComponenteDTO> estadocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() estadocomponente");

        estadocomponenteService.agregarLote(estadocomponenteDTOLote);
    }

    /**
     * Actualiza un EstadoComponente existente.
     *
     * @param id                  la Clave de EstadoComponente a actualizar.
     * @param estadocomponenteDTO el EstadoComponente DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si EstadoComponente no es encontrado.
     * @throws EntradaInvalidadException    si la entrada EstadoComponente tiene errores.
     */
    public void actualizar(Long id, EstadoComponenteDTO estadocomponenteDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() estadocomponente");

        estadocomponenteService.actualizar(id, estadocomponenteDTO);
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

        estadocomponenteService.actualizarLote(estadocomponenteDTOLote);
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
        estadocomponenteService.eliminar(id);
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

        estadocomponenteService.eliminarLote(estadocomponenteDTOLote);
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
        logeador.debug("encontrarPorClave(): {}", id);
        return estadocomponenteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los EstadoComponentes.
     *
     * @return lista de todos EstadoComponente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EstadoComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return estadocomponenteService.obtenerTodos();
    }
}
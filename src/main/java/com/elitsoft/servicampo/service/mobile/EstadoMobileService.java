package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EstadoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EstadoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoMapStruct;
import com.elitsoft.servicampo.service.core.EstadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Estado.
 */
@Component
public class EstadoMobileService {

    @Autowired
    private EstadoMapper estadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoService estadoService; //Logica de Negocio del Core Service

    @Autowired
    private EstadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoMobileService.class); //Logback


    /**
     * Agrega un nuevo Estado.
     * @param estadoDTO el Estado DTO.
     * @return el Estado DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Estado tiene errores.
     * @throws RecursoDuplicadoException si el recurso Estado ya existe.
     */
    public EstadoDTO agregar(EstadoDTO estadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() estado");

        return estadoService.agregar(estadoDTO);
    }

    /**
     * Agrega Lote nuevos Estado.
     * @param estadoLoteDTO lista de Estado DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Estado tiene errores.
     * @throws RecursoDuplicadoException si el recurso Estado ya existe.
     */
    public void agregarLote(List<EstadoDTO> estadoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() estado");

        estadoService.agregarLote(estadoLoteDTO);
    }

    /**
     * Actualiza un Estado existente.
     * @param id la Clave de Estado a actualizar.
     * @param estadoDTO el Estado DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Estado no es encontrado.
     * @throws EntradaInvalidadException si la entrada Estado tiene errores.
     */
    public void actualizar(Long id, EstadoDTO estadoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() estado");

        estadoService.actualizar(id, estadoDTO);
    }

    /**
     * Actualiza Lote de Estado existentes.
     * @param estadoLoteDTO lista de Estado DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Estado tiene errores.
     */
    public void actualizarLote(List<EstadoDTO> estadoLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() estado");

        estadoService.actualizarLote(estadoLoteDTO);
    }

    /**
     * Elimina Estado por Clave.
     * @param id la clave de Estado a eliminar.
     * @throws RecursoNoEncontradoException si el Estado no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() estado: {}", id);
        estadoService.eliminar(id);
    }

    /**
     * Elimina Lote Estado por Clave.
     * @param idLote lista de claves de Estado a eliminar.
     * @throws EntradaInvalidadException si la lista  Estado esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        estadoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Estado por Clave.
     * @param id la clave Estado a encontrar.
     * @return el Estado DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Estado no es encontrado.
     */
    public EstadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return estadoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Estados.
     * @return lista de todos Estado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EstadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return estadoService.obtenerTodos();
    }
}
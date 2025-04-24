package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.BanoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.BanoMapper;
import com.elitsoft.servicampo.mapstruct.BanoMapStruct;
import com.elitsoft.servicampo.service.core.BanoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Bano.
 */
@Service
public class BanoMobileService {

    @Autowired
    private BanoMapper banoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private BanoService banoService; //Logica de Negocio del Core Service

    @Autowired
    private BanoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(BanoMobileService.class); //Logback

    /**
     * Agrega un nuevo Bano.
     *
     * @param banoDTO el Bano DTO.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws EntradaInvalidadException    si la entrada Bano tiene errores.
     * @throws RecursoDuplicadoException    si el recurso Bano ya existe.
     * @throws RecursoNoEncontradoException si Componente no es encontrado.
     */
    public void agregar(BanoDTO banoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() bano");

        banoService.agregar(banoDTO);
    }

    /**
     * Agrega Lote nuevos Bano.
     *
     * @param banoDTOLote lista de Bano DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Bano tiene errores.
     * @throws RecursoDuplicadoException si el recurso Bano ya existe.
     */
    public void agregarLote(List<BanoDTO> banoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() bano");

        banoService.agregarLote(banoDTOLote);
    }

    /**
     * Actualiza un Bano existente.
     *
     * @param id      la Clave de Bano a actualizar.
     * @param banoDTO el Bano DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Bano no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Bano tiene errores.
     */
    public void actualizar(Long id, BanoDTO banoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() bano");

        banoService.actualizar(id, banoDTO);
    }

    /**
     * Actualiza Lote de Bano existentes.
     *
     * @param banoDTOLote lista de Bano DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Bano tiene errores.
     */
    public void actualizarLote(List<BanoDTO> banoDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() bano");

        banoService.actualizarLote(banoDTOLote);
    }

    /**
     * Elimina Bano por Clave.
     *
     * @param id la clave de Bano a eliminar.
     * @throws RecursoNoEncontradoException si el Bano no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Bano esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() bano: {}", id);
        banoService.eliminar(id);
    }

    /**
     * Elimina Lote Bano por Clave.
     *
     * @param banoDTOLote lista de claves de Bano a eliminar.
     * @throws EntradaInvalidadException si la lista  Bano esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Bano esta asociado a otro recurso
     */
    public void eliminarLote(List<BanoDTO> banoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        banoService.eliminarLote(banoDTOLote);
    }

    /**
     * Encuentra un Bano por Clave.
     *
     * @param id la clave Bano a encontrar.
     * @return el Bano DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Bano no es encontrado.
     */
    public BanoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return banoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Banos.
     *
     * @return lista de todos Bano DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<BanoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return banoService.obtenerTodos();
    }
}
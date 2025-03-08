package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.PorotoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.PorotoMapper;
import com.elitsoft.servicampo.mapstruct.PorotoMapStruct;
import com.elitsoft.servicampo.service.core.PorotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Poroto.
 */
@Component
public class PorotoMobileService {

    @Autowired
    private PorotoMapper porotoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PorotoService porotoService; //Logica de Negocio del Core Service

    @Autowired
    private PorotoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PorotoMobileService.class); //Logback


    /**
     * Agrega un nuevo Poroto.
     * @param porotoDTO el Poroto DTO.
     * @return el Poroto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Poroto ya existe.
     */
    public PorotoDTO agregar(PorotoDTO porotoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() poroto");

        return porotoService.agregar(porotoDTO);
    }

    /**
     * Agrega Lote nuevos Poroto.
     * @param porotoLoteDTO lista de Poroto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Poroto ya existe.
     */
    public void agregarLote(List<PorotoDTO> porotoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() poroto");

        porotoService.agregarLote(porotoLoteDTO);
    }

    /**
     * Actualiza un Poroto existente.
     * @param id la Clave de Poroto a actualizar.
     * @param porotoDTO el Poroto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Poroto no es encontrado.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     */
    public void actualizar(Long id, PorotoDTO porotoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() poroto");

        porotoService.actualizar(id, porotoDTO);
    }

    /**
     * Actualiza Lote de Poroto existentes.
     * @param porotoLoteDTO lista de Poroto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     */
    public void actualizarLote(List<PorotoDTO> porotoLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() poroto");

        porotoService.actualizarLote(porotoLoteDTO);
    }

    /**
     * Elimina Poroto por Clave.
     * @param id la clave de Poroto a eliminar.
     * @throws RecursoNoEncontradoException si el Poroto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() poroto: {}", id);
        porotoService.eliminar(id);
    }

    /**
     * Elimina Lote Poroto por Clave.
     * @param idLote lista de claves de Poroto a eliminar.
     * @throws EntradaInvalidadException si la lista  Poroto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        porotoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Poroto por Clave.
     * @param id la clave Poroto a encontrar.
     * @return el Poroto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Poroto no es encontrado.
     */
    public PorotoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return porotoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Porotos.
     * @return lista de todos Poroto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<PorotoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return porotoService.obtenerTodos();
    }
}
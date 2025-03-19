package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContratoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ContratoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoMapStruct;
import com.elitsoft.servicampo.service.core.ContratoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Contrato.
 */
@Component
public class ContratoMobileService {

    @Autowired
    private ContratoMapper contratoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoService contratoService; //Logica de Negocio del Core Service

    @Autowired
    private ContratoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoMobileService.class); //Logback


    /**
     * Agrega un nuevo Contrato.
     * @param contratoDTO el Contrato DTO.
     * @return el Contrato DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contrato tiene errores.
     * @throws RecursoDuplicadoException si el recurso Contrato ya existe.
     */
    public ContratoDTO agregar(ContratoDTO contratoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() contrato");

        return contratoService.agregar(contratoDTO);
    }


    /**
     * Actualiza un Contrato existente.
     * @param id la Clave de Contrato a actualizar.
     * @param contratoDTO el Contrato DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contrato no es encontrado.
     * @throws EntradaInvalidadException si la entrada Contrato tiene errores.
     */
    public void actualizar(Long id, ContratoDTO contratoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() contrato");

        contratoService.actualizar(id, contratoDTO);
    }


    /**
     * Elimina Contrato por Clave.
     * @param id la clave de Contrato a eliminar.
     * @throws RecursoNoEncontradoException si el Contrato no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() contrato: {}", id);
        contratoService.eliminar(id);
    }

    /**
     * Elimina Lote Contrato por Clave.
     * @param contratoDTOLote lista de claves de Contrato a eliminar.
     * @throws EntradaInvalidadException si la lista  Contrato esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDTO> contratoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        contratoService.eliminarLote(contratoDTOLote);
    }

    /**
     * Encuentra un Contrato por Clave.
     * @param id la clave Contrato a encontrar.
     * @return el Contrato DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contrato no es encontrado.
     */
    public ContratoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return contratoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Contratos.
     * @return lista de todos Contrato DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return contratoService.obtenerTodos();
    }
}
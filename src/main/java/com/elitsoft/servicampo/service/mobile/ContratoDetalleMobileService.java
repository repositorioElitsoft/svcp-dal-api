package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ContratoDetalleMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleMapStruct;
import com.elitsoft.servicampo.service.core.ContratoDetalleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  ContratoDetalle.
 */
@Component
public class ContratoDetalleMobileService {

    @Autowired
    private ContratoDetalleMapper contratodetalleMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleService contratodetalleService; //Logica de Negocio del Core Service

    @Autowired
    private ContratoDetalleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleMobileService.class); //Logback


    /**
     * Agrega un nuevo ContratoDetalle.
     * @param contratodetalleDTO el ContratoDetalle DTO.
     * @return el ContratoDetalle DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalle tiene errores.
     * @throws RecursoDuplicadoException si el recurso ContratoDetalle ya existe.
     */
    public ContratoDetalleDTO agregar(ContratoDetalleDTO contratodetalleDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() contratodetalle");

        return contratodetalleService.agregar(contratodetalleDTO);
    }


    /**
     * Actualiza un ContratoDetalle existente.
     * @param id la Clave de ContratoDetalle a actualizar.
     * @param contratodetalleDTO el ContratoDetalle DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalle no es encontrado.
     * @throws EntradaInvalidadException si la entrada ContratoDetalle tiene errores.
     */
    public void actualizar(Long id, ContratoDetalleDTO contratodetalleDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() contratodetalle");

        contratodetalleService.actualizar(id, contratodetalleDTO);
    }


    /**
     * Elimina ContratoDetalle por Clave.
     * @param id la clave de ContratoDetalle a eliminar.
     * @throws RecursoNoEncontradoException si el ContratoDetalle no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() contratodetalle: {}", id);
        contratodetalleService.eliminar(id);
    }

    /**
     * Elimina Lote ContratoDetalle por Clave.
     * @param contratodetalleDTOLote lista de claves de ContratoDetalle a eliminar.
     * @throws EntradaInvalidadException si la lista  ContratoDetalle esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDetalleDTO> contratodetalleDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        contratodetalleService.eliminarLote(contratodetalleDTOLote);
    }

    /**
     * Encuentra un ContratoDetalle por Clave.
     * @param id la clave ContratoDetalle a encontrar.
     * @return el ContratoDetalle DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalle no es encontrado.
     */
    public ContratoDetalleDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return contratodetalleService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los ContratoDetalles.
     * @return lista de todos ContratoDetalle DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDetalleDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return contratodetalleService.obtenerTodos();
    }
}
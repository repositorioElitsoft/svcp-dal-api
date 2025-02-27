package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.AgrupacionComercialDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.AgrupacionComercialMapper;
import com.elitsoft.servicampo.mapstruct.AgrupacionComercialMapStruct;
import com.elitsoft.servicampo.service.core.AgrupacionComercialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  AgrupacionComercial.
 */
@Component
public class AgrupacionComercialMobileService {

    @Autowired
    private AgrupacionComercialMapper agrupacionComercialMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private AgrupacionComercialService agrupacionComercialService; //Logica de Negocio del Core Service

    @Autowired
    private AgrupacionComercialMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(AgrupacionComercialMobileService.class); //Logback

    /**
     * Agrega un nuevo AgrupacionComercial.
     * @param agrupacionComercialDto el AgrupacionComercial DTO.
     * @return el AgrupacionComercial DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     * @throws RecursoDuplicadoException si el recurso AgrupacionComercial ya existe.
     */
    public AgrupacionComercialDto agregar(AgrupacionComercialDto agrupacionComercialDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() agrupacioncomercial");

        return agrupacionComercialService.agregar(agrupacionComercialDto);
    }

    /**
     * Agrega Lote nuevos AgrupacionComercial.
     * @param agrupacioncomercialLoteDto lista de AgrupacionComercial DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     * @throws RecursoDuplicadoException si el recurso AgrupacionComercial ya existe.
     */
    public void agregarLote(List<AgrupacionComercialDto> agrupacioncomercialLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() agrupacioncomercial");

        agrupacionComercialService.agregarLote(agrupacioncomercialLoteDto);
    }

    /**
     * Actualiza un AgrupacionComercial existente.
     * @param id la Clave de AgrupacionComercial a actualizar.
     * @param agrupacionComercialDto el AgrupacionComercial DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si AgrupacionComercial no es encontrado.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     */
    public void actualizar(Long id, AgrupacionComercialDto agrupacionComercialDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() agrupacioncomercial");

        agrupacionComercialService.actualizar(id, agrupacionComercialDto);
    }

    /**
     * Actualiza Lote de AgrupacionComercial existentes.
     * @param agrupacionComercialLoteDto lista de AgrupacionComercial DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     */
    public void actualizarLote(List<AgrupacionComercialDto> agrupacionComercialLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() agrupacioncomercial");

        agrupacionComercialService.actualizarLote(agrupacionComercialLoteDto);
    }

    /**
     * Elimina AgrupacionComercial por Clave.
     * @param id la clave de AgrupacionComercial a eliminar.
     * @throws RecursoNoEncontradoException si el AgrupacionComercial no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() agrupacioncomercial: {}", id);
        agrupacionComercialService.eliminar(id);
    }

    /**
     * Elimina Lote AgrupacionComercial por Clave.
     * @param idLote lista de claves de AgrupacionComercial a eliminar.
     * @throws EntradaInvalidadException si la lista  AgrupacionComercial esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        agrupacionComercialService.eliminarLote(idLote);
    }

    /**
     * Encuentra un AgrupacionComercial por Clave.
     * @param id la clave AgrupacionComercial a encontrar.
     * @return el AgrupacionComercial DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si AgrupacionComercial no es encontrado.
     */
    public AgrupacionComercialDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return agrupacionComercialService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los AgrupacionComercials.
     * @return lista de todos AgrupacionComercial DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<AgrupacionComercialDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return agrupacionComercialService.obtenerTodos();
    }
}
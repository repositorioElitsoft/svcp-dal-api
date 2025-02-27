package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoClienteDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoClienteMapper;
import com.elitsoft.servicampo.mapstruct.TipoClienteMapStruct;
import com.elitsoft.servicampo.service.core.TipoClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoCliente.
 */
@Component
public class TipoClienteMobileService {

    @Autowired
    private TipoClienteMapper tipoClienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoClienteService tipoClienteService; //Logica de Negocio del Core Service

    @Autowired
    private TipoClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoClienteMobileService.class); //Logback


    /**
     * Agrega un nuevo TipoCliente.
     * @param tipoClienteDto el TipoCliente DTO.
     * @return el TipoCliente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoCliente ya existe.
     */
    public TipoClienteDto agregar(TipoClienteDto tipoClienteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipocliente");

        return tipoClienteService.agregar(tipoClienteDto);
    }

    /**
     * Agrega Lote nuevos TipoCliente.
     * @param tipoClienteLoteDto lista de TipoCliente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoCliente ya existe.
     */
    public void agregarLote(List<TipoClienteDto> tipoClienteLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipocliente");

        tipoClienteService.agregarLote(tipoClienteLoteDto);
    }

    /**
     * Actualiza un TipoCliente existente.
     * @param id la Clave de TipoCliente a actualizar.
     * @param tipoClienteDto el TipoCliente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoCliente no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     */
    public void actualizar(Long id, TipoClienteDto tipoClienteDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tipocliente");

        tipoClienteService.actualizar(id, tipoClienteDto);
    }

    /**
     * Actualiza Lote de TipoCliente existentes.
     * @param tipoClienteLoteDto lista de TipoCliente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoCliente tiene errores.
     */
    public void actualizarLote(List<TipoClienteDto> tipoClienteLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipocliente");

        tipoClienteService.actualizarLote(tipoClienteLoteDto);
    }

    /**
     * Elimina TipoCliente por Clave.
     * @param id la clave de TipoCliente a eliminar.
     * @throws RecursoNoEncontradoException si el TipoCliente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipocliente: {}", id);
        tipoClienteService.eliminar(id);
    }

    /**
     * Elimina Lote TipoCliente por Clave.
     * @param idLote lista de claves de TipoCliente a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoCliente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipoClienteService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoCliente por Clave.
     * @param id la clave TipoCliente a encontrar.
     * @return el TipoCliente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoCliente no es encontrado.
     */
    public TipoClienteDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoClienteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoClientes.
     * @return lista de todos TipoCliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoClienteDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoClienteService.obtenerTodos();
    }
}
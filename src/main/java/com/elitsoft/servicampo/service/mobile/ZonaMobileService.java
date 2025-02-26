package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ZonaDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ZonaMapper;
import com.elitsoft.servicampo.mapstruct.ZonaMapStruct;
import com.elitsoft.servicampo.service.core.ZonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Zona.
 */
@Component
public class ZonaMobileService {

    @Autowired
    private ZonaMapper zonaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ZonaService zonaService; //Logica de Negocio del Core Service

    @Autowired
    private ZonaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ZonaMobileService.class); //Logback

    /**
     * Agrega un nuevo Zona.
     * @param zonaDto el Zona DTO.
     * @return el Zona DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     * @throws RecursoDuplicadoException si el recurso Zona ya existe.
     */
    public ZonaDto agregar(ZonaDto zonaDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() zona");

        return zonaService.agregar(zonaDto);
    }

    /**
     * Agrega Lote nuevos Zona.
     * @param zonaLoteDto lista de Zona DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     * @throws RecursoDuplicadoException si el recurso Zona ya existe.
     */
    public void agregarLote(List<ZonaDto> zonaLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() zona");

        zonaService.agregarLote(zonaLoteDto);
    }

    /**
     * Actualiza un Zona existente.
     * @param id la Clave de Zona a actualizar.
     * @param zonaDto el Zona DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Zona no es encontrado.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     */
    public void actualizar(Long id, ZonaDto zonaDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() zona");

        zonaService.actualizar(id, zonaDto);
    }

    /**
     * Actualiza Lote de Zona existentes.
     * @param zonaLoteDto lista de Zona DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     */
    public void actualizarLote(List<ZonaDto> zonaLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() zona");

        zonaService.actualizarLote(zonaLoteDto);
    }

    /**
     * Elimina Zona por Clave.
     * @param id la clave de Zona a eliminar.
     * @throws RecursoNoEncontradoException si el Zona no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() zona: {}", id);
        zonaService.eliminar(id);
    }

    /**
     * Elimina Lote Zona por Clave.
     * @param idLote lista de claves de Zona a eliminar.
     * @throws EntradaInvalidadException si la lista  Zona esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        zonaService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Zona por Clave.
     * @param id la clave Zona a encontrar.
     * @return el Zona DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Zona no es encontrado.
     */
    public ZonaDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return zonaService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Zonas.
     * @return lista de todos Zona DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ZonaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return zonaService.obtenerTodos();
    }
}
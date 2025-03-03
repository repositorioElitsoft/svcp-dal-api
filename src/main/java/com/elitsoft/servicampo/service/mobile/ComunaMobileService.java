package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ComunaDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ComunaMapper;
import com.elitsoft.servicampo.mapstruct.ComunaMapStruct;
import com.elitsoft.servicampo.service.core.ComunaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Comuna.
 */
@Component
public class ComunaMobileService {

    @Autowired
    private ComunaMapper comunaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ComunaService comunaService; //Logica de Negocio del Core Service

    @Autowired
    private ComunaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ComunaMobileService.class); //Logback


    /**
     * Agrega un nuevo Comuna.
     * @param comunaDto el Comuna DTO.
     * @return el Comuna DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     * @throws RecursoDuplicadoException si el recurso Comuna ya existe.
     */
    public ComunaDto agregar(ComunaDto comunaDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() comuna");

        return comunaService.agregar(comunaDto);
    }

    /**
     * Agrega Lote nuevos Comuna.
     * @param comunaLoteDto lista de Comuna DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     * @throws RecursoDuplicadoException si el recurso Comuna ya existe.
     */
    public void agregarLote(List<ComunaDto> comunaLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() comuna");

        comunaService.agregarLote(comunaLoteDto);
    }

    /**
     * Actualiza un Comuna existente.
     * @param id la Clave de Comuna a actualizar.
     * @param comunaDto el Comuna DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Comuna no es encontrado.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     */
    public void actualizar(Long id, ComunaDto comunaDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() comuna");

        comunaService.actualizar(id, comunaDto);
    }

    /**
     * Actualiza Lote de Comuna existentes.
     * @param comunaLoteDto lista de Comuna DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     */
    public void actualizarLote(List<ComunaDto> comunaLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() comuna");

        comunaService.actualizarLote(comunaLoteDto);
    }

    /**
     * Elimina Comuna por Clave.
     * @param id la clave de Comuna a eliminar.
     * @throws RecursoNoEncontradoException si el Comuna no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() comuna: {}", id);
        comunaService.eliminar(id);
    }

    /**
     * Elimina Lote Comuna por Clave.
     * @param idLote lista de claves de Comuna a eliminar.
     * @throws EntradaInvalidadException si la lista  Comuna esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        comunaService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Comuna por Clave.
     * @param id la clave Comuna a encontrar.
     * @return el Comuna DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Comuna no es encontrado.
     */
    public ComunaDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return comunaService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Comunas.
     * @param provinciaId clave de Provincia a la que pertenecen las regiones
     * @return lista de todos Comuna DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ComunaDto> obtenerTodos(Long provinciaId) throws BaseDatosException {
        logeador.debug("obtenerTodos() {}",provinciaId);
        return comunaService.obtenerTodos(provinciaId);
    }
}
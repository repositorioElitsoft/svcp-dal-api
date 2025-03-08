package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ProvinciaDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ProvinciaMapper;
import com.elitsoft.servicampo.mapstruct.ProvinciaMapStruct;
import com.elitsoft.servicampo.service.core.ProvinciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Provincia.
 */
@Component
public class ProvinciaMobileService {

    @Autowired
    private ProvinciaMapper provinciaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ProvinciaService provinciaService; //Logica de Negocio del Core Service

    @Autowired
    private ProvinciaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ProvinciaMobileService.class); //Logback


    /**
     * Agrega un nuevo Provincia.
     * @param provinciaDTO el Provincia DTO.
     * @return el Provincia DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     * @throws RecursoDuplicadoException si el recurso Provincia ya existe.
     */
    public ProvinciaDTO agregar(ProvinciaDTO provinciaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() provincia");

        return provinciaService.agregar(provinciaDTO);
    }

    /**
     * Agrega Lote nuevos Provincia.
     * @param provinciaLoteDTO lista de Provincia DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     * @throws RecursoDuplicadoException si el recurso Provincia ya existe.
     */
    public void agregarLote(List<ProvinciaDTO> provinciaLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() provincia");

        provinciaService.agregarLote(provinciaLoteDTO);
    }

    /**
     * Actualiza un Provincia existente.
     * @param id la Clave de Provincia a actualizar.
     * @param provinciaDTO el Provincia DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Provincia no es encontrado.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     */
    public void actualizar(Long id, ProvinciaDTO provinciaDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() provincia");

        provinciaService.actualizar(id, provinciaDTO);
    }

    /**
     * Actualiza Lote de Provincia existentes.
     * @param provinciaLoteDTO lista de Provincia DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     */
    public void actualizarLote(List<ProvinciaDTO> provinciaLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() provincia");

        provinciaService.actualizarLote(provinciaLoteDTO);
    }

    /**
     * Elimina Provincia por Clave.
     * @param id la clave de Provincia a eliminar.
     * @throws RecursoNoEncontradoException si el Provincia no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() provincia: {}", id);
        provinciaService.eliminar(id);
    }

    /**
     * Elimina Lote Provincia por Clave.
     * @param idLote lista de claves de Provincia a eliminar.
     * @throws EntradaInvalidadException si la lista  Provincia esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        provinciaService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Provincia por Clave.
     * @param id la clave Provincia a encontrar.
     * @return el Provincia DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Provincia no es encontrado.
     */
    public ProvinciaDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return provinciaService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Provincias.
     * @param regionId  la clave de Region a encontrar.
     * @return lista de todos Provincia DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ProvinciaDTO> obtenerTodos(Long regionId) throws BaseDatosException {
        logeador.debug("obtenerTodos() {}",regionId);
        return provinciaService.obtenerTodos(regionId);
    }
}
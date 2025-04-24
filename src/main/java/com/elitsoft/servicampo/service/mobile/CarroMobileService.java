package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.CarroDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.CarroMapper;
import com.elitsoft.servicampo.mapstruct.CarroMapStruct;
import com.elitsoft.servicampo.service.core.CarroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Carro.
 */
@Component
public class CarroMobileService {

    @Autowired
    private CarroMapper carroMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private CarroService carroService; //Logica de Negocio del Core Service

    @Autowired
    private CarroMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(CarroMobileService.class); //Logback

    /**
     * Agrega un nuevo Carro.
     *
     * @param carroDTO el Carro DTO.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws EntradaInvalidadException    si la entrada Carro tiene errores.
     * @throws RecursoDuplicadoException    si el recurso Carro ya existe.
     * @throws RecursoNoEncontradoException si Componente no es encontrado.
     */
    public void agregar(CarroDTO carroDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() carro");

        carroService.agregar(carroDTO);
    }

    /**
     * Agrega Lote nuevos Carro.
     *
     * @param carroDTOLote lista de Carro DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Carro tiene errores.
     * @throws RecursoDuplicadoException si el recurso Carro ya existe.
     */
    public void agregarLote(List<CarroDTO> carroDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() carro");

        carroService.agregarLote(carroDTOLote);
    }

    /**
     * Actualiza un Carro existente.
     *
     * @param id       la Clave de Carro a actualizar.
     * @param carroDTO el Carro DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Carro no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Carro tiene errores.
     */
    public void actualizar(Long id, CarroDTO carroDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() carro");

        carroService.actualizar(id, carroDTO);
    }

    /**
     * Actualiza Lote de Carro existentes.
     *
     * @param carroDTOLote lista de Carro DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Carro tiene errores.
     */
    public void actualizarLote(List<CarroDTO> carroDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() carro");

        carroService.actualizarLote(carroDTOLote);
    }

    /**
     * Elimina Carro por Clave.
     *
     * @param id la clave de Carro a eliminar.
     * @throws RecursoNoEncontradoException si el Carro no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Carro esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() carro: {}", id);
        carroService.eliminar(id);
    }

    /**
     * Elimina Lote Carro por Clave.
     *
     * @param carroDTOLote lista de claves de Carro a eliminar.
     * @throws EntradaInvalidadException si la lista  Carro esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Carro esta asociado a otro recurso
     */
    public void eliminarLote(List<CarroDTO> carroDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        carroService.eliminarLote(carroDTOLote);
    }

    /**
     * Encuentra un Carro por Clave.
     *
     * @param id la clave Carro a encontrar.
     * @return el Carro DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Carro no es encontrado.
     */
    public CarroDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return carroService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Carros.
     *
     * @return lista de todos Carro DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<CarroDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return carroService.obtenerTodos();
    }
}
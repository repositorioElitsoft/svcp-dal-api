package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.CarroDTO;
import com.elitsoft.servicampo.domain.entity.Carro;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.CarroMapper;
import com.elitsoft.servicampo.mapstruct.CarroMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.CarroError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Carro.
 */
@Service
public class CarroService {

    @Autowired
    private CarroMapper carroMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private CarroMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(CarroService.class); //Logback

    /**
     * Agrega un nuevo Carro.
     * @param carroDTO el Carro DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Carro tiene errores.
     * @throws RecursoDuplicadoException si el recurso carro ya existe.
     */
    public void agregar(CarroDTO carroDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() carro");

        //  Valida Entrada
        if (carroDTO == null || carroDTO.getId() == null) {
            logeador.error(Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((carroDTO != null) ? carroDTO.toString() : null  ),
                           CarroError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(CarroError.REQUERIDO.getCodigoError(),
                                                Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Carro carro = mapper.toEntity(carroDTO);
            Long nuevoId = carroMapper.agregar(carro);
            logeador.info("Carro agregado exitosamente id: {}", nuevoId);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CARRO_DUPLICADO_MENSAGE + ": {}, codigoError:{}", carroDTO.getId(),
                           CarroError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(CarroError.DUPLICADO.getCodigoError(),
                                                Constantes.CARRO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CARRO_AGREGAR_MENSAJE + ": {}, codigoError:{}", carroDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CARRO_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Agrega Lote nuevos Carro.
     * @param carroDTOLote lista de Carro DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Carro tiene errores.
     * @throws RecursoDuplicadoException si el recurso carro ya existe.
     */
    public void agregarLote(List<CarroDTO> carroDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() carro");

        //  Valida Entrada
        if (carroDTOLote.isEmpty()) {
            logeador.error(Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          CarroError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(CarroError.REQUERIDO.getCodigoError(),
                                                Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Carro> carroLote = mapper.toEntityList(carroDTOLote);

            int registrosAgregados =  carroMapper.agregarLote(carroLote);
            logeador.info("Lote Carro agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.CARRO_DUPLICADO_MENSAGE + " codigoError:{}",
                          CarroError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(CarroError.DUPLICADO.getCodigoError(),
                                                Constantes.CARRO_DUPLICADO_MENSAGE);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.CARRO_VIOLACION_INTEGRIDAD_MENSAGE + " codigoError:{}",
                    CarroError.INTEGRIDAD_VIOLADA.getCodigoError());
            throw new EntradaInvalidadException(CarroError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.CARRO_VIOLACION_INTEGRIDAD_MENSAGE);
        }
        catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CARRO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CARRO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Carro existente.
     * @param id la clave de Carro a actualizar.
     * @param carroDTO el Carro DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Carro no es encontrado.
     * @throws EntradaInvalidadException si la entrada Carro tiene errores.
     */
    public void actualizar(Long id, CarroDTO carroDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() carro");

        //  Valida Entrada
        if (id == null || carroDTO == null || carroDTO.getId() == null) {
            logeador.error(Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((carroDTO != null) ? carroDTO.toString() : null  ),
                           CarroError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(CarroError.REQUERIDO.getCodigoError(),
                                                Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(carroDTO.getId())) {
            logeador.error(Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  carroDTO.toString(),
                           CarroError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(CarroError.ID_INVALIDO.getCodigoError(),
                                                Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            CarroDTO carroDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Carro carro = mapper.toEntity(carroDTO);
            carro.setId(id);
            int registrosActualizados = carroMapper.actualizar(carro);
            logeador.info("carro actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CARRO_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, carroDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CARRO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Carro existentes.
     * @param carroDTOLote lista de Carro DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Carro tiene errores.
     */
    public void actualizarLote(List<CarroDTO> carroDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() carro");

        //  Valida Entrada
        if (carroDTOLote.isEmpty()) {
            logeador.error(Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           CarroError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(CarroError.REQUERIDO.getCodigoError(),
                                                Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Carro> carroLote = mapper.toEntityList(carroDTOLote);
            int registrosActualizados = carroMapper.actualizarLote(carroLote);
            logeador.info("Lote carro actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CARRO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CARRO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Carro por Clave.
     * @param id la clave de Carro a eliminar.
     * @throws RecursoNoEncontradoException si el Carro no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() carro: {}", id);

        try {
            CarroDTO carroDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = carroMapper.eliminar(id);
            logeador.info("carro eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CARRO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Carro por Clave.
     * @param carroDTOLote lista de claves de Carro a eliminar.
     * @throws EntradaInvalidadException si la lista  Carro esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<CarroDTO> carroDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (carroDTOLote.isEmpty()) {
            logeador.error(Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           CarroError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(CarroError.REQUERIDO.getCodigoError(),
                                                Constantes.CARRO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = carroMapper.eliminarLote(mapper.toEntityList(carroDTOLote));
            logeador.info("Lote carro eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CARRO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CARRO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Carro por Clave.
     * @param id la clave Carro a encontrar.
     * @return el Carro DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Carro no es encontrado.
     */
    public CarroDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            CarroDTO carroDTO = mapper.toDTO(carroMapper.encontrarPorClave(id));

            if (carroDTO != null) {
                logeador.info("carro encontrado por clave : {}", id);
            } else {
                logeador.info("carro clave:{} no encontrado codigoError:{}", id,
                              CarroError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(CarroError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.CARRO_NO_ENCONTRADO_MENSAGE);
            }

            return carroDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CARRO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Carros.
     * @return una lista de todos Carro DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<CarroDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<CarroDTO> carroLista = mapper.toDTOList(carroMapper.obtenerTodos());
            logeador.info("carros obtenidos");
            return carroLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CARRO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CARRO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
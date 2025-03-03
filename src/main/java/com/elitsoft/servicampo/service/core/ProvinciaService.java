package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ProvinciaDto;
import com.elitsoft.servicampo.domain.entity.Provincia;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ProvinciaMapper;
import com.elitsoft.servicampo.mapstruct.ProvinciaMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Provincia.
 */
@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaMapper provinciaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ProvinciaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ProvinciaService.class); //Logback


    /**
     * Agrega un nuevo Provincia.
     * @param provinciaDto el Provincia DTO.
     * @return el Provincia DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     * @throws RecursoDuplicadoException si el recurso Provincia ya existe.
     */
    public ProvinciaDto agregar(ProvinciaDto provinciaDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Provincia");

        //  Valida Entrada
        if (provinciaDto == null) {
            logeador.error(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Provincia provincia = mapper.toEntity(provinciaDto);
            provincia = provinciaMapper.agregar(provincia);
            logeador.info("Provincia agregado exitosamente id: {}", provincia.getId());
            return mapper.toDto(provincia);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.PROVINCIA_DUPLICADO_MENSAGE + ": {}", provinciaDto.getId());
            throw new RecursoDuplicadoException(Constantes.PROVINCIA_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.PROVINCIA_AGREGAR_MENSAJE + ": {}", provinciaDto.toString(), e);
            throw new BaseDatosException(Constantes.PROVINCIA_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Provincia.
     * @param provinciaLoteDto lista de Provincia DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     * @throws RecursoDuplicadoException si el recurso provincia ya existe.
     */
    public void agregarLote(List<ProvinciaDto> provinciaLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() provincia");

        //  Valida Entrada
        if (provinciaLoteDto.isEmpty()) {
            logeador.error(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Provincia> provinciaLote = mapper.toEntityList(provinciaLoteDto);

            int registrosAgregados =  provinciaMapper.agregarLote(provinciaLote);
            logeador.info("Lote Provincia agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.PROVINCIA_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.PROVINCIA_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PROVINCIA_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.PROVINCIA_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Provincia existente.
     * @param id la clave de Provincia a actualizar.
     * @param provinciaDto el Provincia DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Provincia no es encontrado.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     */
    public void actualizar(Long id, ProvinciaDto provinciaDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() provincia");

        //  Valida Entrada
        if (id == null || provinciaDto == null || provinciaDto.getId() == null) {
            logeador.error(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE + ": {}", ((provinciaDto != null) ? provinciaDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ProvinciaDto provinciaDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Provincia provincia = mapper.toEntity(provinciaDto);
            provincia.setId(id);
            int registrosActualizados = provinciaMapper.actualizar(provincia);
            logeador.info("provincia actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PROVINCIA_ACTUALIZAR_MENSAJE + ": id={} {}", id, provinciaDto.toString(), e);
            throw new BaseDatosException(Constantes.PROVINCIA_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Provincia existentes.
     * @param provinciaLoteDto lista de Provincia DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Provincia tiene errores.
     */
    public void actualizarLote(List<ProvinciaDto> provinciaLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() provincia");

        //  Valida Entrada
        if (provinciaLoteDto.isEmpty()) {
            logeador.error(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Provincia> provinciaLote = mapper.toEntityList(provinciaLoteDto);
            int registrosActualizados = provinciaMapper.actualizarLote(provinciaLote);
            logeador.info("Lote provincia actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PROVINCIA_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.PROVINCIA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Provincia por Clave.
     * @param id la clave de Provincia a eliminar.
     * @throws RecursoNoEncontradoException si el Provincia no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() provincia: {}", id);

        try {
            ProvinciaDto provinciaDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = provinciaMapper.eliminar(id);
            logeador.info("provincia eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.PROVINCIA_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.PROVINCIA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Provincia por Clave.
     * @param idLote lista de claves de Provincia a eliminar.
     * @throws EntradaInvalidadException si la lista  Provincia esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PROVINCIA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = provinciaMapper.eliminarLote(idLote);
            logeador.info("Lote provincia eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PROVINCIA_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.PROVINCIA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Provincia por Clave.
     * @param id la clave Provincia a encontrar.
     * @return el Provincia DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Provincia no es encontrado.
     */
    public ProvinciaDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ProvinciaDto provinciaDto = mapper.toDto(provinciaMapper.encontrarPorClave(id));

            if (provinciaDto != null) {
                logeador.info("provincia encontrado por clave : {}", id);
            } else {
                logeador.info("provincia clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.PROVINCIA_NO_ENCONTRADO_MENSAGE);
            }

            return provinciaDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PROVINCIA_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.PROVINCIA_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Provincias.
     * @param regionId  la clave de Region a encontrar.
     * @return una lista de todos Provincia DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ProvinciaDto> obtenerTodos(Long regionId) throws BaseDatosException {
        logeador.debug("obtenerTodos() {}",regionId);

        try {
            List<ProvinciaDto> provinciaLista = mapper.toDtoList(provinciaMapper.obtenerTodos(regionId));
            logeador.info("provincias obtenidos");
            return provinciaLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PROVINCIA_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.PROVINCIA_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
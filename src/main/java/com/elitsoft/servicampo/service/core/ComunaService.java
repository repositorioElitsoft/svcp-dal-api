package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ComunaDTO;
import com.elitsoft.servicampo.domain.entity.Comuna;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ComunaMapper;
import com.elitsoft.servicampo.mapstruct.ComunaMapStruct;
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
 * Clase de Servicio para la entidad Comuna.
 */
@Service
public class ComunaService {

    @Autowired
    private ComunaMapper comunaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ComunaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ComunaService.class); //Logback


    /**
     * Agrega un nuevo Comuna.
     * @param comunaDTO el Comuna DTO.
     * @return el Comuna DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     * @throws RecursoDuplicadoException si el recurso Comuna ya existe.
     */
    public ComunaDTO agregar(ComunaDTO comunaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Comuna");

        //  Valida Entrada
        if (comunaDTO == null) {
            logeador.error(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Comuna comuna = mapper.toEntity(comunaDTO);
            comuna = comunaMapper.agregar(comuna);
            logeador.info("Comuna agregado exitosamente id: {}", comuna.getId());
            return mapper.toDto(comuna);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.COMUNA_DUPLICADO_MENSAGE + ": {}", comunaDTO.getId());
            throw new RecursoDuplicadoException(Constantes.COMUNA_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.COMUNA_AGREGAR_MENSAJE + ": {}", comunaDTO.toString(), e);
            throw new BaseDatosException(Constantes.COMUNA_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Comuna.
     * @param comunaLoteDTO lista de Comuna DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     * @throws RecursoDuplicadoException si el recurso comuna ya existe.
     */
    public void agregarLote(List<ComunaDTO> comunaLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() comuna");

        //  Valida Entrada
        if (comunaLoteDTO.isEmpty()) {
            logeador.error(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Comuna> comunaLote = mapper.toEntityList(comunaLoteDTO);

            int registrosAgregados =  comunaMapper.agregarLote(comunaLote);
            logeador.info("Lote Comuna agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.COMUNA_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.COMUNA_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMUNA_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.COMUNA_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Comuna existente.
     * @param id la clave de Comuna a actualizar.
     * @param comunaDTO el Comuna DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Comuna no es encontrado.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     */
    public void actualizar(Long id, ComunaDTO comunaDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() comuna");

        //  Valida Entrada
        if (id == null || comunaDTO == null || comunaDTO.getId() == null) {
            logeador.error(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE + ": {}", ((comunaDTO != null) ? comunaDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ComunaDTO comunaDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Comuna comuna = mapper.toEntity(comunaDTO);
            comuna.setId(id);
            int registrosActualizados = comunaMapper.actualizar(comuna);
            logeador.info("comuna actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMUNA_ACTUALIZAR_MENSAJE + ": id={} {}", id, comunaDTO.toString(), e);
            throw new BaseDatosException(Constantes.COMUNA_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Comuna existentes.
     * @param comunaLoteDTO lista de Comuna DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Comuna tiene errores.
     */
    public void actualizarLote(List<ComunaDTO> comunaLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() comuna");

        //  Valida Entrada
        if (comunaLoteDTO.isEmpty()) {
            logeador.error(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Comuna> comunaLote = mapper.toEntityList(comunaLoteDTO);
            int registrosActualizados = comunaMapper.actualizarLote(comunaLote);
            logeador.info("Lote comuna actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMUNA_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.COMUNA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Comuna por Clave.
     * @param id la clave de Comuna a eliminar.
     * @throws RecursoNoEncontradoException si el Comuna no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() comuna: {}", id);

        try {
            ComunaDTO comunaDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = comunaMapper.eliminar(id);
            logeador.info("comuna eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.COMUNA_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.COMUNA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Comuna por Clave.
     * @param idLote lista de claves de Comuna a eliminar.
     * @throws EntradaInvalidadException si la lista  Comuna esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.COMUNA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = comunaMapper.eliminarLote(idLote);
            logeador.info("Lote comuna eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.COMUNA_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.COMUNA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Comuna por Clave.
     * @param id la clave Comuna a encontrar.
     * @return el Comuna DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Comuna no es encontrado.
     */
    public ComunaDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ComunaDTO comunaDTO = mapper.toDto(comunaMapper.encontrarPorClave(id));

            if (comunaDTO != null) {
                logeador.info("comuna encontrado por clave : {}", id);
            } else {
                logeador.info("comuna clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.COMUNA_NO_ENCONTRADO_MENSAGE);
            }

            return comunaDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.COMUNA_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.COMUNA_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Comunas.
     * @param provinciaId clave de Provincia a la que pertenecen las regiones
     * @return una lista de todos Comuna DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ComunaDTO> obtenerTodos(Long provinciaId) throws BaseDatosException {
        logeador.debug("obtenerTodos() {}", provinciaId);

        try {
            List<ComunaDTO> comunaLista = mapper.toDtoList(comunaMapper.obtenerTodos(provinciaId));
            logeador.info("comunas obtenidos");
            return comunaLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.COMUNA_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.COMUNA_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
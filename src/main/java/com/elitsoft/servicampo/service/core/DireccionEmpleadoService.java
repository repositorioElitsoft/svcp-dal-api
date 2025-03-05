package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.DireccionEmpleadoDTO;
import com.elitsoft.servicampo.domain.entity.DireccionEmpleado;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.DireccionEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.DireccionEmpleadoMapStruct;
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
 * Clase de Servicio para la entidad DireccionEmpleado.
 */
@Service
public class DireccionEmpleadoService {

    @Autowired
    private DireccionEmpleadoMapper direccionempleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DireccionEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(DireccionEmpleadoService.class); //Logback



    /**
     * Agrega un nuevo DireccionEmpleado.
     * @param direccionempleadoDTO el DireccionEmpleado DTO.
     * @return el DireccionEmpleado DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso DireccionEmpleado ya existe.
     */
    public DireccionEmpleadoDTO agregar(DireccionEmpleadoDTO direccionempleadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() DireccionEmpleado");

        //  Valida Entrada
        if (direccionempleadoDTO == null) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            DireccionEmpleado direccionempleado = mapper.toEntity(direccionempleadoDTO);
            direccionempleado = direccionempleadoMapper.agregar(direccionempleado);
            logeador.info("DireccionEmpleado agregado exitosamente id: {}", direccionempleado.getId());
            return mapper.toDTO(direccionempleado);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_DUPLICADO_MENSAGE + ": {}", direccionempleadoDTO.getId());
            throw new RecursoDuplicadoException(Constantes.DIRECCIONEMPLEADO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_AGREGAR_MENSAJE + ": {}", direccionempleadoDTO.toString(), e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos DireccionEmpleado.
     * @param direccionempleadoLoteDTO lista de DireccionEmpleado DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso direccionempleado ya existe.
     */
    public void agregarLote(List<DireccionEmpleadoDTO> direccionempleadoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() direccionempleado");

        //  Valida Entrada
        if (direccionempleadoLoteDTO.isEmpty()) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<DireccionEmpleado> direccionempleadoLote = mapper.toEntityList(direccionempleadoLoteDTO);

            int registrosAgregados =  direccionempleadoMapper.agregarLote(direccionempleadoLote);
            logeador.info("Lote DireccionEmpleado agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.DIRECCIONEMPLEADO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un DireccionEmpleado existente.
     * @param id la clave de DireccionEmpleado a actualizar.
     * @param direccionempleadoDTO el DireccionEmpleado DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DireccionEmpleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     */
    public void actualizar(Long id, DireccionEmpleadoDTO direccionempleadoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() direccionempleado");

        //  Valida Entrada
        if (id == null || direccionempleadoDTO == null || direccionempleadoDTO.getId() == null) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((direccionempleadoDTO != null) ? direccionempleadoDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(direccionempleadoDTO.getId())) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}",  direccionempleadoDTO.toString());
            throw new EntradaInvalidadException(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            DireccionEmpleadoDTO direccionempleadoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            DireccionEmpleado direccionempleado = mapper.toEntity(direccionempleadoDTO);
            direccionempleado.setId(id);
            int registrosActualizados = direccionempleadoMapper.actualizar(direccionempleado);
            logeador.info("direccionempleado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ACTUALIZAR_MENSAJE + ": id={} {}", id, direccionempleadoDTO.toString(), e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de DireccionEmpleado existentes.
     * @param direccionempleadoLoteDTO lista de DireccionEmpleado DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     */
    public void actualizarLote(List<DireccionEmpleadoDTO> direccionempleadoLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() direccionempleado");

        //  Valida Entrada
        if (direccionempleadoLoteDTO.isEmpty()) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<DireccionEmpleado> direccionempleadoLote = mapper.toEntityList(direccionempleadoLoteDTO);
            int registrosActualizados = direccionempleadoMapper.actualizarLote(direccionempleadoLote);
            logeador.info("Lote direccionempleado actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina DireccionEmpleado por Clave.
     * @param id la clave de DireccionEmpleado a eliminar.
     * @throws RecursoNoEncontradoException si el DireccionEmpleado no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() direccionempleado: {}", id);

        try {
            DireccionEmpleadoDTO direccionempleadoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = direccionempleadoMapper.eliminar(id);
            logeador.info("direccionempleado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote DireccionEmpleado por Clave.
     * @param idLote lista de claves de DireccionEmpleado a eliminar.
     * @throws EntradaInvalidadException si la lista  DireccionEmpleado esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DIRECCIONEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = direccionempleadoMapper.eliminarLote(idLote);
            logeador.info("Lote direccionempleado eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un DireccionEmpleado por Clave.
     * @param id la clave DireccionEmpleado a encontrar.
     * @return el DireccionEmpleado DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DireccionEmpleado no es encontrado.
     */
    public DireccionEmpleadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            DireccionEmpleadoDTO direccionempleadoDTO = mapper.toDTO(direccionempleadoMapper.encontrarPorClave(id));

            if (direccionempleadoDTO != null) {
                logeador.info("direccionempleado encontrado por clave : {}", id);
            } else {
                logeador.info("direccionempleado clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.DIRECCIONEMPLEADO_NO_ENCONTRADO_MENSAGE);
            }

            return direccionempleadoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los DireccionEmpleados.
     * @return una lista de todos DireccionEmpleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DireccionEmpleadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<DireccionEmpleadoDTO> direccionempleadoLista = mapper.toDTOList(direccionempleadoMapper.obtenerTodos());
            logeador.info("direccionempleados obtenidos");
            return direccionempleadoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCIONEMPLEADO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoMapStruct;
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
 * Clase de Servicio para la entidad TipoProducto.
 */
@Service
public class TipoProductoService {

    @Autowired
    private TipoProductoMapper tipoproductoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoService.class); //Logback

    /**
     * Agrega un nuevo TipoProducto.
     * @param tipoproductoDto el TipoProducto DTO.
     * @return el TipoProducto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoProducto ya existe.
     */
    public TipoProductoDTO agregar(TipoProductoDTO tipoproductoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoProducto");

        //  Valida Entrada
        if (tipoproductoDto == null) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoProducto tipoproducto = mapper.toEntity(tipoproductoDto);
            tipoproducto = tipoproductoMapper.agregar(tipoproducto);
            logeador.info("TipoProducto agregado exitosamente id: {}", tipoproducto.getId());
            return mapper.toDto(tipoproducto);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE + ": {}", tipoproductoDto.getId());
            throw new RecursoDuplicadoException(Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_AGREGAR_MENSAJE + ": {}", tipoproductoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoProducto.
     * @param tipoproductoLoteDto lista de TipoProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipoproducto ya existe.
     */
    public void agregarLote(List<TipoProductoDTO> tipoproductoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoproducto");

        //  Valida Entrada
        if (tipoproductoLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoProducto> tipoproductoLote = mapper.toEntityList(tipoproductoLoteDto);

            int registrosAgregados =  tipoproductoMapper.agregarLote(tipoproductoLote);
            logeador.info("Lote TipoProducto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoProducto existente.
     * @param id la clave de TipoProducto a actualizar.
     * @param tipoproductoDto el TipoProducto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProducto no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     */
    public void actualizar(Long id, TipoProductoDTO tipoproductoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tipoproducto");

        //  Valida Entrada
        if (id == null || tipoproductoDto == null || tipoproductoDto.getId() == null) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoproductoDto != null) ? tipoproductoDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipoproductoDto.getId())) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  tipoproductoDto.toString());
            throw new EntradaInvalidadException(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoProductoDTO tipoproductoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoProducto tipoproducto = mapper.toEntity(tipoproductoDto);
            tipoproducto.setId(id);
            int registrosActualizados = tipoproductoMapper.actualizar(tipoproducto);
            logeador.info("tipoproducto actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoproductoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoProducto existentes.
     * @param tipoproductoLoteDto lista de TipoProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     */
    public void actualizarLote(List<TipoProductoDTO> tipoproductoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoproducto");

        //  Valida Entrada
        if (tipoproductoLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoProducto> tipoproductoLote = mapper.toEntityList(tipoproductoLoteDto);
            int registrosActualizados = tipoproductoMapper.actualizarLote(tipoproductoLote);
            logeador.info("Lote tipoproducto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoProducto por Clave.
     * @param id la clave de TipoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el TipoProducto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoproducto: {}", id);

        try {
            TipoProductoDTO tipoproductoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoproductoMapper.eliminar(id);
            logeador.info("tipoproducto eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoProducto por Clave.
     * @param idLote lista de claves de TipoProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tipoproductoMapper.eliminarLote(idLote);
            logeador.info("Lote tipoproducto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoProducto por Clave.
     * @param id la clave TipoProducto a encontrar.
     * @return el TipoProducto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProducto no es encontrado.
     */
    public TipoProductoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoProductoDTO tipoproductoDto = mapper.toDto(tipoproductoMapper.encontrarPorClave(id));

            if (tipoproductoDto != null) {
                logeador.info("tipoproducto encontrado por clave : {}", id);
            } else {
                logeador.info("tipoproducto clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            return tipoproductoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoProductos.
     * @return una lista de todos TipoProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoProductoDTO> tipoproductoList = mapper.toDtoList(tipoproductoMapper.obtenerTodos());
            logeador.info("tipoproductos obtenidos");
            return tipoproductoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
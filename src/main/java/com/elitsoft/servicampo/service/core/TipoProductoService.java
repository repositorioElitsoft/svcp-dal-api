package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import com.elitsoft.servicampo.domain.dto.core.TipoProductoTipoComponenteDTO;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TipoProductoError;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Servicio para la entidad TipoProducto.
 */
@Service
public class TipoProductoService {

    @Autowired
    private TipoProductoMapper tipoProductoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoService.class); //Logback

    /**
     * Agrega un nuevo TipoProducto.
     *
     * @param tipoproductoDto el TipoProducto DTO.
     * @return el TipoProducto DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoProducto ya existe.
     */
    public TipoProductoDTO agregar(TipoProductoDTO tipoproductoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoProducto");

        //  Valida Entrada
        if (tipoproductoDto == null) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoProductoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoProducto tipoProducto = mapper.toEntity(tipoproductoDto);
            tipoProducto = tipoProductoMapper.agregar(tipoProducto);
            logeador.info("TipoProducto agregado exitosamente id: {}", tipoProducto.getId());
            return mapper.toDTO(tipoProducto);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE + ": {}", tipoproductoDto.getId());
            throw new RecursoDuplicadoException(TipoProductoError.DUPLICADO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_AGREGAR_MENSAJE + ": {}", tipoproductoDto.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoProducto.
     *
     * @param tipoProductoLoteDTO lista de TipoProducto DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipoproducto ya existe.
     */
    public void agregarLote(List<TipoProductoDTO> tipoProductoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoproducto");

        //  Valida Entrada
        if (tipoProductoLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoProductoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoProducto> tipoProductoLote = mapper.toEntityList(tipoProductoLoteDTO);

            int registrosAgregados = tipoProductoMapper.agregarLote(tipoProductoLote);
            logeador.info("Lote TipoProducto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(TipoProductoError.DUPLICADO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoProducto existente.
     *
     * @param id              la clave de TipoProducto a actualizar.
     * @param tipoProductoDTO el TipoProducto DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProducto no es encontrado.
     * @throws EntradaInvalidadException    si la entrada TipoProducto tiene errores.
     */
    public void actualizar(Long id, TipoProductoDTO tipoProductoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() tipoproducto");

        //  Valida Entrada
        if (id == null || tipoProductoDTO == null || tipoProductoDTO.getId() == null) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoProductoDTO != null) ? tipoProductoDTO.toString() : null));
            throw new EntradaInvalidadException(TipoProductoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipoProductoDTO.getId())) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, tipoProductoDTO.toString());
            throw new EntradaInvalidadException(TipoProductoError.ID_INVALIDO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoProductoDTO tipoProductoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoProducto tipoproducto = mapper.toEntity(tipoProductoDTO);
            tipoproducto.setId(id);
            int registrosActualizados = tipoProductoMapper.actualizar(tipoproducto);
            logeador.info("tipoproducto actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoProductoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de TipoProducto existentes.
     *
     * @param tipoProductoLoteDTO lista de TipoProducto DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     */
    public void actualizarLote(List<TipoProductoDTO> tipoProductoLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoproducto");

        //  Valida Entrada
        if (tipoProductoLoteDTO.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoProductoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoProducto> tipoProductoLote = mapper.toEntityList(tipoProductoLoteDTO);
            int registrosActualizados = tipoProductoMapper.actualizarLote(tipoProductoLote);
            logeador.info("Lote tipoproducto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoProducto por Clave.
     *
     * @param id la clave de TipoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el TipoProducto no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si TipoProducto esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() tipoproducto: {}", id);

        try {
            TipoProductoDTO tipoProductoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoProductoMapper.eliminar(id);
            logeador.info("tipoproducto eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOPRODUCTO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TipoProductoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TIPOPRODUCTO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoProducto por Clave.
     *
     * @param idLote lista de claves de TipoProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoProducto esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TipoProducto esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(TipoProductoError.REQUERIDO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = tipoProductoMapper.eliminarLote(idLote);
            logeador.info("Lote tipoproducto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.TIPOPRODUCTO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(TipoProductoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.TIPOPRODUCTO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoProducto por Clave.
     *
     * @param id la clave TipoProducto a encontrar.
     * @return el TipoProducto DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProducto no es encontrado.
     */
    public TipoProductoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoProductoDTO tipoProductoDTO = mapper.toDTO(tipoProductoMapper.encontrarPorClave(id));

            if (tipoProductoDTO != null) {
                logeador.info("tipoproducto encontrado por clave : {}", id);
            } else {
                logeador.info("tipoproducto clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TipoProductoError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            return tipoProductoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoProductos.
     *
     * @return una lista de todos TipoProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoProductoDTO> tipoProductoDTOLista = mapper.toDTOList(tipoProductoMapper.obtenerTodos());
            logeador.info("tipoproductos obtenidos");
            return tipoProductoDTOLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
        }
    }

    /**
     * Obtiene lista de Tipos Componentes de un TipoProducto.
     *
     * @param id la clave TipoProducto a encontrar.
     * @return una lista de TipoProductoTipoComponenteDTO.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProducto no es encontrado.
     */
    public List<TipoProductoTipoComponenteDTO> obtenerTipoComponentesPorTipoProducto(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerTipoComponentesPorTipoProducto()");

        List<TipoProductoTipoComponenteDTO> tipoProductoTipoComponenteDTOLista = new ArrayList<>();

        try {
            TipoProductoDTO tipoProductoDTO = mapper.toDTO(tipoProductoMapper.obtenerTipoComponentesPorTipoProducto(id));

            if (tipoProductoDTO != null) {
                tipoProductoTipoComponenteDTOLista = tipoProductoDTO.getTipoProductoTipoComponentes();
                logeador.info("tipoproducto encontrado por clave : {}", id);
            } else {
                logeador.info("tipoproducto clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(TipoProductoError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            logeador.info("tipoproductos obtenidos");
            return tipoProductoTipoComponenteDTOLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
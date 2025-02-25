package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.PorotoDto;
import com.elitsoft.servicampo.domain.entity.Poroto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.PorotoMapper;
import com.elitsoft.servicampo.mapstruct.PorotoMapStruct;
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
 * Clase de Servicio para la entidad Poroto.
 */
@Service
public class PorotoService {

    @Autowired
    private PorotoMapper porotoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PorotoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PorotoService.class); //Logback


    /**
     * Agrega un nuevo Poroto.
     * @param porotoDto el Poroto DTO.
     * @return el Poroto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Poroto ya existe.
     */
    public PorotoDto agregar(PorotoDto porotoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Poroto");

        //  Valida Entrada
        if (porotoDto == null) {
            logeador.error(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Poroto poroto = mapper.toEntity(porotoDto);
            poroto = porotoMapper.agregar(poroto);
            logeador.info("Poroto agregado exitosamente id: {}", poroto.getPrtoId());
            return mapper.toDto(poroto);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.POROTO_DUPLICADO_MENSAGE + ": {}", porotoDto.getPrtoId());
            throw new RecursoDuplicadoException(Constantes.POROTO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.POROTO_AGREGAR_MENSAJE + ": {}", porotoDto.toString(), e);
            throw new BaseDatosException(Constantes.POROTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Poroto.
     * @param porotoLoteDto lista de Poroto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     * @throws RecursoDuplicadoException si el recurso poroto ya existe.
     */
    public void agregarLote(List<PorotoDto> porotoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() poroto");

        //  Valida Entrada
        if (porotoLoteDto.isEmpty()) {
            logeador.error(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Poroto> porotoLote = mapper.toEntityList(porotoLoteDto);

            int registrosAgregados =  porotoMapper.agregarLote(porotoLote);
            logeador.info("Lote Poroto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.POROTO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.POROTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.POROTO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.POROTO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Poroto existente.
     * @param id la clave de Poroto a actualizar.
     * @param porotoDto el Poroto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Poroto no es encontrado.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     */
    public void actualizar(Long id, PorotoDto porotoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() poroto");

        //  Valida Entrada
        if (id == null || porotoDto == null || porotoDto.getPrtoId() == null) {
            logeador.error(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((porotoDto != null) ? porotoDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            PorotoDto porotoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Poroto poroto = mapper.toEntity(porotoDto);
            poroto.setPrtoId(id);
            int registrosActualizados = porotoMapper.actualizar(poroto);
            logeador.info("poroto actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.POROTO_ACTUALIZAR_MENSAJE + ": id={} {}", id, porotoDto.toString(), e);
            throw new BaseDatosException(Constantes.POROTO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Poroto existentes.
     * @param porotoLoteDto lista de Poroto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Poroto tiene errores.
     */
    public void actualizarLote(List<PorotoDto> porotoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() poroto");

        //  Valida Entrada
        if (porotoLoteDto.isEmpty()) {
            logeador.error(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Poroto> porotoLote = mapper.toEntityList(porotoLoteDto);
            int registrosActualizados = porotoMapper.actualizarLote(porotoLote);
            logeador.info("Lote poroto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.POROTO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.POROTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Poroto por Clave.
     * @param id la clave de Poroto a eliminar.
     * @throws RecursoNoEncontradoException si el Poroto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() poroto: {}", id);

        try {
            PorotoDto porotoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = porotoMapper.eliminar(id);
            logeador.info("poroto eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.POROTO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.POROTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Poroto por Clave.
     * @param idLote lista de claves de Poroto a eliminar.
     * @throws EntradaInvalidadException si la lista  Poroto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.POROTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = porotoMapper.eliminarLote(idLote);
            logeador.info("Lote poroto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.POROTO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.POROTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Poroto por Clave.
     * @param id la clave Poroto a encontrar.
     * @return el Poroto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Poroto no es encontrado.
     */
    public PorotoDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            PorotoDto porotoDto = mapper.toDto(porotoMapper.encontrarPorClave(id));

            if (porotoDto != null) {
                logeador.info("poroto encontrado por clave : {}", id);
            } else {
                logeador.info("poroto clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.POROTO_NO_ENCONTRADO_MENSAGE);
            }

            return porotoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.POROTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.POROTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Porotos.
     * @return una lista de todos Poroto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<PorotoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<PorotoDto> porotoList = mapper.toDtoList(porotoMapper.obtenerTodos());
            logeador.info("porotos obtenidos");
            return porotoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.POROTO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.POROTO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
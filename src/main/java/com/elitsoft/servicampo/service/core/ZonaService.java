package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ZonaDto;
import com.elitsoft.servicampo.domain.entity.Zona;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ZonaMapper;
import com.elitsoft.servicampo.mapstruct.ZonaMapStruct;
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
 * Clase de Servicio para la entidad Zona.
 */
@Service
public class ZonaService {

    @Autowired
    private ZonaMapper zonaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ZonaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ZonaService.class); //Logback


    /**
     * Agrega un nuevo Zona.
     * @param zonaDto el Zona DTO.
     * @return el Zona DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     * @throws RecursoDuplicadoException si el recurso Zona ya existe.
     */
    public ZonaDto agregar(ZonaDto zonaDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Zona");

        //  Valida Entrada
        if (zonaDto == null) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Zona zona = mapper.toEntity(zonaDto);
            zona = zonaMapper.agregar(zona);
            logeador.info("Zona agregado exitosamente id: {}", zona.getId());
            return mapper.toDto(zona);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.ZONA_DUPLICADO_MENSAGE + ": {}", zonaDto.getId());
            throw new RecursoDuplicadoException(Constantes.ZONA_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_AGREGAR_MENSAJE + ": {}", zonaDto.toString(), e);
            throw new BaseDatosException(Constantes.ZONA_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Zona.
     * @param zonaLoteDto lista de Zona DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     * @throws RecursoDuplicadoException si el recurso zona ya existe.
     */
    public void agregarLote(List<ZonaDto> zonaLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() zona");

        //  Valida Entrada
        if (zonaLoteDto.isEmpty()) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Zona> zonaLote = mapper.toEntityList(zonaLoteDto);

            int registrosAgregados =  zonaMapper.agregarLote(zonaLote);
            logeador.info("Lote Zona agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ZONA_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.ZONA_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.ZONA_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Zona existente.
     * @param id la clave de Zona a actualizar.
     * @param zonaDto el Zona DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Zona no es encontrado.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     */
    public void actualizar(Long id, ZonaDto zonaDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() zona");

        //  Valida Entrada
        if (id == null || zonaDto == null || zonaDto.getId() == null) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE + ": {}", ((zonaDto != null) ? zonaDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ZonaDto zonaDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Zona zona = mapper.toEntity(zonaDto);
            zona.setId(id);
            int registrosActualizados = zonaMapper.actualizar(zona);
            logeador.info("zona actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_ACTUALIZAR_MENSAJE + ": id={} {}", id, zonaDto.toString(), e);
            throw new BaseDatosException(Constantes.ZONA_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Zona existentes.
     * @param zonaLoteDto lista de Zona DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     */
    public void actualizarLote(List<ZonaDto> zonaLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() zona");

        //  Valida Entrada
        if (zonaLoteDto.isEmpty()) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Zona> zonaLote = mapper.toEntityList(zonaLoteDto);
            int registrosActualizados = zonaMapper.actualizarLote(zonaLote);
            logeador.info("Lote zona actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.ZONA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Zona por Clave.
     * @param id la clave de Zona a eliminar.
     * @throws RecursoNoEncontradoException si el Zona no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() zona: {}", id);

        try {
            ZonaDto zonaDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = zonaMapper.eliminar(id);
            logeador.info("zona eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Zona por Clave.
     * @param idLote lista de claves de Zona a eliminar.
     * @throws EntradaInvalidadException si la lista  Zona esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = zonaMapper.eliminarLote(idLote);
            logeador.info("Lote zona eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.ZONA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Zona por Clave.
     * @param id la clave Zona a encontrar.
     * @return el Zona DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Zona no es encontrado.
     */
    public ZonaDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ZonaDto zonaDto = mapper.toDto(zonaMapper.encontrarPorClave(id));

            if (zonaDto != null) {
                logeador.info("zona encontrado por clave : {}", id);
            } else {
                logeador.info("zona clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.ZONA_NO_ENCONTRADO_MENSAGE);
            }

            return zonaDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Zonas.
     * @return una lista de todos Zona DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ZonaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ZonaDto> zonaList = mapper.toDtoList(zonaMapper.obtenerTodos());
            logeador.info("zonas obtenidos");
            return zonaList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.ZONA_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
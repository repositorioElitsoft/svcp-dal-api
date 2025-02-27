package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.AgrupacionComercialDto;
import com.elitsoft.servicampo.domain.entity.AgrupacionComercial;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.AgrupacionComercialMapper;
import com.elitsoft.servicampo.mapstruct.AgrupacionComercialMapStruct;
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
 * Clase de Servicio para la entidad AgrupacionComercial.
 */
@Service
public class AgrupacionComercialService {

    @Autowired
    private AgrupacionComercialMapper agrupacionComercialMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private AgrupacionComercialMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(AgrupacionComercialService.class); //Logback


    /**
     * Agrega un nuevo AgrupacionComercial.
     * @param agrupacionComercialDto el AgrupacionComercial DTO.
     * @return el AgrupacionComercial DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     * @throws RecursoDuplicadoException si el recurso AgrupacionComercial ya existe.
     */
    public AgrupacionComercialDto agregar(AgrupacionComercialDto agrupacionComercialDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() AgrupacionComercial");

        //  Valida Entrada
        if (agrupacionComercialDto == null) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            AgrupacionComercial agrupacionComercial = mapper.toEntity(agrupacionComercialDto);
            agrupacionComercial = agrupacionComercialMapper.agregar(agrupacionComercial);
            logeador.info("AgrupacionComercial agregado exitosamente id: {}", agrupacionComercial.getId());
            return mapper.toDto(agrupacionComercial);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE + ": {}", agrupacionComercialDto.getId());
            throw new RecursoDuplicadoException(Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_AGREGAR_MENSAJE + ": {}", agrupacionComercialDto.toString(), e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos AgrupacionComercial.
     * @param agrupacionComercialLoteDto lista de AgrupacionComercial DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     * @throws RecursoDuplicadoException si el recurso agrupacioncomercial ya existe.
     */
    public void agregarLote(List<AgrupacionComercialDto> agrupacionComercialLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() agrupacioncomercial");

        //  Valida Entrada
        if (agrupacionComercialLoteDto.isEmpty()) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<AgrupacionComercial> agrupacionComercialLote = mapper.toEntityList(agrupacionComercialLoteDto);

            int registrosAgregados =  agrupacionComercialMapper.agregarLote(agrupacionComercialLote);
            logeador.info("Lote AgrupacionComercial agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un AgrupacionComercial existente.
     * @param id la clave de AgrupacionComercial a actualizar.
     * @param agrupacionComercialDto el AgrupacionComercial DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si AgrupacionComercial no es encontrado.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     */
    public void actualizar(Long id, AgrupacionComercialDto agrupacionComercialDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() agrupacioncomercial");

        //  Valida Entrada
        if (id == null || agrupacionComercialDto == null || agrupacionComercialDto.getId() == null) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE + ": {}", ((agrupacionComercialDto != null) ? agrupacionComercialDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            AgrupacionComercialDto agrupacionComercialDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            AgrupacionComercial agrupacionComercial = mapper.toEntity(agrupacionComercialDto);
            agrupacionComercial.setId(id);
            int registrosActualizados = agrupacionComercialMapper.actualizar(agrupacionComercial);
            logeador.info("agrupacioncomercial actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE + ": id={} {}", id, agrupacionComercialDto.toString(), e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de AgrupacionComercial existentes.
     * @param agrupacioncomercialLoteDto lista de AgrupacionComercial DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     */
    public void actualizarLote(List<AgrupacionComercialDto> agrupacioncomercialLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() agrupacioncomercial");

        //  Valida Entrada
        if (agrupacioncomercialLoteDto.isEmpty()) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<AgrupacionComercial> agrupacionComercialLote = mapper.toEntityList(agrupacioncomercialLoteDto);
            int registrosActualizados = agrupacionComercialMapper.actualizarLote(agrupacionComercialLote);
            logeador.info("Lote agrupacioncomercial actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina AgrupacionComercial por Clave.
     * @param id la clave de AgrupacionComercial a eliminar.
     * @throws RecursoNoEncontradoException si el AgrupacionComercial no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() agrupacioncomercial: {}", id);

        try {
            AgrupacionComercialDto agrupacionComercialDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = agrupacionComercialMapper.eliminar(id);
            logeador.info("agrupacioncomercial eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote AgrupacionComercial por Clave.
     * @param idLote lista de claves de AgrupacionComercial a eliminar.
     * @throws EntradaInvalidadException si la lista  AgrupacionComercial esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = agrupacionComercialMapper.eliminarLote(idLote);
            logeador.info("Lote agrupacioncomercial eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un AgrupacionComercial por Clave.
     * @param id la clave AgrupacionComercial a encontrar.
     * @return el AgrupacionComercial DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si AgrupacionComercial no es encontrado.
     */
    public AgrupacionComercialDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            AgrupacionComercialDto agrupacionComercialDto = mapper.toDto(agrupacionComercialMapper.encontrarPorClave(id));

            if (agrupacionComercialDto != null) {
                logeador.info("agrupacioncomercial encontrado por clave : {}", id);
            } else {
                logeador.info("agrupacioncomercial clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.AGRUPACIONCOMERCIAL_NO_ENCONTRADO_MENSAGE);
            }

            return agrupacionComercialDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los AgrupacionComercials.
     * @return una lista de todos AgrupacionComercial DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<AgrupacionComercialDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<AgrupacionComercialDto> agrupacionComercialLista = mapper.toDtoList(agrupacionComercialMapper.obtenerTodos());
            logeador.info("agrupacioncomercials obtenidos");
            return agrupacionComercialLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
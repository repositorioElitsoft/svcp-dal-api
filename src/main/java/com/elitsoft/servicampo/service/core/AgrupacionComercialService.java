package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.AgrupacionComercialDTO;
import com.elitsoft.servicampo.domain.entity.AgrupacionComercial;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.AgrupacionComercialMapper;
import com.elitsoft.servicampo.mapstruct.AgrupacionComercialMapStruct;
import com.elitsoft.servicampo.service.error.AgrupacionComercialError;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.utils.Constantes;
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
     *
     * @param agrupacionComercialDTO el AgrupacionComercial DTO.
     * @return el AgrupacionComercial DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     * @throws RecursoDuplicadoException si el recurso AgrupacionComercial ya existe.
     */
    public AgrupacionComercialDTO agregar(AgrupacionComercialDTO agrupacionComercialDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() AgrupacionComercial");

        //  Valida Entrada
        if (agrupacionComercialDTO == null) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(AgrupacionComercialError.REQUERIDO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            AgrupacionComercial agrupacionComercial = mapper.toEntity(agrupacionComercialDTO);
            agrupacionComercial = agrupacionComercialMapper.agregar(agrupacionComercial);
            logeador.info("AgrupacionComercial agregado exitosamente id: {}", agrupacionComercial.getId());
            return mapper.toDto(agrupacionComercial);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE + ": {}", agrupacionComercialDTO.getId());
            throw new RecursoDuplicadoException(AgrupacionComercialError.DUPLICADO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_AGREGAR_MENSAJE + ": {}", agrupacionComercialDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos AgrupacionComercial.
     *
     * @param agrupacionComercialLoteDTO lista de AgrupacionComercial DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     * @throws RecursoDuplicadoException si el recurso agrupacioncomercial ya existe.
     */
    public void agregarLote(List<AgrupacionComercialDTO> agrupacionComercialLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() agrupacioncomercial");

        //  Valida Entrada
        if (agrupacionComercialLoteDTO.isEmpty()) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(AgrupacionComercialError.REQUERIDO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<AgrupacionComercial> agrupacionComercialLote = mapper.toEntityList(agrupacionComercialLoteDTO);

            int registrosAgregados = agrupacionComercialMapper.agregarLote(agrupacionComercialLote);
            logeador.info("Lote AgrupacionComercial agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(AgrupacionComercialError.DUPLICADO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un AgrupacionComercial existente.
     *
     * @param id                     la clave de AgrupacionComercial a actualizar.
     * @param agrupacionComercialDTO el AgrupacionComercial DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si AgrupacionComercial no es encontrado.
     * @throws EntradaInvalidadException    si la entrada AgrupacionComercial tiene errores.
     */
    public void actualizar(Long id, AgrupacionComercialDTO agrupacionComercialDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() agrupacioncomercial");

        //  Valida Entrada
        if (id == null || agrupacionComercialDTO == null || agrupacionComercialDTO.getId() == null) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE + ": {}", ((agrupacionComercialDTO != null) ? agrupacionComercialDTO.toString() : null));
            throw new EntradaInvalidadException(AgrupacionComercialError.REQUERIDO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(agrupacionComercialDTO.getId())) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, agrupacionComercialDTO.toString());
            throw new EntradaInvalidadException(AgrupacionComercialError.ID_INVALIDO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            AgrupacionComercialDTO agrupacionComercialDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            AgrupacionComercial agrupacionComercial = mapper.toEntity(agrupacionComercialDTO);
            agrupacionComercial.setId(id);
            int registrosActualizados = agrupacionComercialMapper.actualizar(agrupacionComercial);
            logeador.info("agrupacioncomercial actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE + ": id={} {}", id, agrupacionComercialDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de AgrupacionComercial existentes.
     *
     * @param agrupacioncomercialLoteDTO lista de AgrupacionComercial DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada AgrupacionComercial tiene errores.
     */
    public void actualizarLote(List<AgrupacionComercialDTO> agrupacioncomercialLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() agrupacioncomercial");

        //  Valida Entrada
        if (agrupacioncomercialLoteDTO.isEmpty()) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(AgrupacionComercialError.REQUERIDO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<AgrupacionComercial> agrupacionComercialLote = mapper.toEntityList(agrupacioncomercialLoteDTO);
            int registrosActualizados = agrupacionComercialMapper.actualizarLote(agrupacionComercialLote);
            logeador.info("Lote agrupacioncomercial actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina AgrupacionComercial por Clave.
     *
     * @param id la clave de AgrupacionComercial a eliminar.
     * @throws RecursoNoEncontradoException si el AgrupacionComercial no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si el AgrupacionComercial viola la integridad referencial.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() agrupacioncomercial: {}", id);

        try {
            AgrupacionComercialDTO agrupacionComercialDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = agrupacionComercialMapper.eliminar(id);
            logeador.info("agrupacioncomercial eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_VIOLACION_INTEGRIDAD_MENSAGE + ": {}", id, e);
            throw new RecursoEliminarException(AgrupacionComercialError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_VIOLACION_INTEGRIDAD_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote AgrupacionComercial por Clave.
     *
     * @param idLote lista de claves de AgrupacionComercial a eliminar.
     * @throws EntradaInvalidadException si la lista  AgrupacionComercial esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si el AgrupacionComercial viola la integridad referencial.
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(AgrupacionComercialError.REQUERIDO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = agrupacionComercialMapper.eliminarLote(idLote);
            logeador.info("Lote agrupacioncomercial eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_VIOLACION_INTEGRIDAD_MENSAGE, e);
            throw new RecursoEliminarException(AgrupacionComercialError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_VIOLACION_INTEGRIDAD_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un AgrupacionComercial por Clave.
     *
     * @param id la clave AgrupacionComercial a encontrar.
     * @return el AgrupacionComercial DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si AgrupacionComercial no es encontrado.
     */
    public AgrupacionComercialDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            AgrupacionComercialDTO agrupacionComercialDto = mapper.toDto(agrupacionComercialMapper.encontrarPorClave(id));

            if (agrupacionComercialDto != null) {
                logeador.info("agrupacioncomercial encontrado por clave : {}", id);
            } else {
                logeador.info("agrupacioncomercial clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(AgrupacionComercialError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.AGRUPACIONCOMERCIAL_NO_ENCONTRADO_MENSAGE);
            }

            return agrupacionComercialDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los AgrupacionComercials.
     *
     * @return una lista de todos AgrupacionComercial DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<AgrupacionComercialDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<AgrupacionComercialDTO> agrupacionComercialLista = mapper.toDtoList(agrupacionComercialMapper.obtenerTodos());
            logeador.info("agrupacioncomercials obtenidos");
            return agrupacionComercialLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.AGRUPACIONCOMERCIAL_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.AGRUPACIONCOMERCIAL_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
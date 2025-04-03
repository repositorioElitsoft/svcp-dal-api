package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.PermisoDTO;
import com.elitsoft.servicampo.domain.entity.Permiso;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.PermisoMapper;
import com.elitsoft.servicampo.mapstruct.PermisoMapStruct;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.PermisoError;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Permiso.
 */
@Service
@Transactional
public class PermisoService {

    @Autowired
    private PermisoMapper permisoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PermisoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PermisoService.class);

    /**
     * Agrega un nuevo permiso.
     *
     * @param permisoDTO El DTO del permiso a agregar
     * @throws BaseDatosException        Si ocurre un error de base de datos
     * @throws EntradaInvalidadException Si el permisoDTO es nulo o inválido
     */
    public void agregar(PermisoDTO permisoDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("agregar() permiso");

        if (permisoDTO == null) {
            logeador.error(Constantes.PERMISO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(PermisoError.REQUERIDO.getCodigoError(),
                    Constantes.PERMISO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Permiso permiso = mapper.toEntity(permisoDTO);
            Long nuevoId = permisoMapper.agregar(permiso);
            logeador.info("Permiso agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PERMISO_AGREGAR_MENSAJE + ": {}", permisoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.PERMISO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza un permiso existente.
     *
     * @param id         El ID del permiso a actualizar
     * @param permisoDTO El DTO con los datos actualizados
     * @throws RecursoNoEncontradoException Si el permiso no existe
     * @throws BaseDatosException           Si ocurre un error de base de datos
     * @throws EntradaInvalidadException    Si los datos son inválidos
     */
    public void actualizar(Long id, PermisoDTO permisoDTO) throws RecursoNoEncontradoException, BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizar() permiso");

        //  Valida Entrada
        if (id == null || permisoDTO == null || permisoDTO.getId() == null) {
            logeador.error(Constantes.PERMISO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((permisoDTO != null) ? permisoDTO.toString() : null));
            throw new EntradaInvalidadException(PermisoError.REQUERIDO.getCodigoError(),
                    Constantes.PERMISO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(permisoDTO.getId())) {
            logeador.error(Constantes.PERMISO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id, permisoDTO.toString());
            throw new EntradaInvalidadException(PermisoError.ID_INVALIDO.getCodigoError(),
                    Constantes.PERMISO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            PermisoDTO permisoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Permiso permiso = mapper.toEntity(permisoDTO);
            permiso.setId(id);
            int registrosActualizados = permisoMapper.actualizar(permiso);
            logeador.info("permiso actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PERMISO_ACTUALIZAR_MENSAJE + ": id={} {}", id, permisoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.PERMISO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina un permiso por su ID.
     *
     * @param id El ID del permiso a eliminar
     * @throws RecursoNoEncontradoException Si el permiso no existe
     * @throws BaseDatosException           Si ocurre un error de base de datos
     * @throws RecursoEliminarException     Si el permiso está siendo utilizado por otros registros
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() permiso: {}", id);

        try {
            PermisoDTO permisoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = permisoMapper.eliminar(id);
            logeador.info("permiso eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.PERMISO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(PermisoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.PERMISO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.PERMISO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.PERMISO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un permiso por su ID.
     *
     * @param id El ID del permiso a buscar
     * @return El DTO del permiso encontrado
     * @throws RecursoNoEncontradoException Si el permiso no existe
     * @throws BaseDatosException           Si ocurre un error de base de datos
     */
    public PermisoDTO encontrarPorClave(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            PermisoDTO permisoDTO = mapper.toDto(permisoMapper.encontrarPorClave(id));

            if (permisoDTO != null) {
                logeador.info("permiso encontrado por clave : {}", id);
            } else {
                logeador.info("permiso clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(PermisoError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.PERMISO_NO_ENCONTRADO_MENSAGE);
            }

            return permisoDTO;
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PERMISO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.PERMISO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los permisos.
     *
     * @return Lista de DTOs de permisos
     * @throws BaseDatosException Si ocurre un error de base de datos
     */
    public List<PermisoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<PermisoDTO> permisoLista = mapper.toDtoList(permisoMapper.obtenerTodos());
            logeador.info("permisos obtenidos");
            return permisoLista;
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PERMISO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.PERMISO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
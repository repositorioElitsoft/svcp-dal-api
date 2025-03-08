package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.EstructuraFormularioDTO;
import com.elitsoft.servicampo.domain.entity.EstructuraFormulario;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.EstructuraFormularioMapper;
import com.elitsoft.servicampo.mapstruct.EstructuraFormularioMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad EstructuraFormulario.
 */
@Service
@Transactional
public class EstructuraFormularioService {

    @Autowired
    private EstructuraFormularioMapper estructuraformularioMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstructuraFormularioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstructuraFormularioService.class);

    /**
     * Agrega un nuevo EstructuraFormulario.
     * @param estructuraFormularioDTO El EstructuraFormulario DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(EstructuraFormularioDTO estructuraFormularioDTO) throws BaseDatosException {
        logeador.debug("agregar() estructuraformulario");


        try {
            EstructuraFormulario estructuraformulario = mapper.toEntity(estructuraFormularioDTO);
            Long nuevoId = estructuraformularioMapper.agregar(estructuraformulario);
            logeador.info("EstructuraFormulario agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTRUCTURAFORMULARIO_AGREGAR_EXECPTION + ": {}", estructuraFormularioDTO.toString(), e);
            throw new BaseDatosException(Constantes.ESTRUCTURAFORMULARIO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un EstructuraFormulario existente.
     * @param id La Clave de EstructuraFormulario a actualizar.
     * @param estructuraFormularioDTO El EstructuraFormulario DTO con informacion actualizada.
     * @throws EstructuraFormularioNoEncontradoException Si EstructuraFormulario no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, EstructuraFormularioDTO estructuraFormularioDTO) throws EstructuraFormularioNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() estructuraformulario");

        try {
            EstructuraFormularioDTO estructuraformularioDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe

            EstructuraFormulario estructuraformulario = mapper.toEntity(estructuraFormularioDTO);
            estructuraformulario.setId(id);
            int registrosActualizados = estructuraformularioMapper.actualizar(estructuraformulario);
            logeador.info("estructuraformulario actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (EstructuraFormularioNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTRUCTURAFORMULARIO_ACTUALIZAR_EXECPTION + ": id={} {}", id, estructuraFormularioDTO.toString(), e);
            throw new BaseDatosException(Constantes.ESTRUCTURAFORMULARIO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina EstructuraFormulario por Clave.
     * @param id La Clave de EstructuraFormulario a eliminar.
     * @throws EstructuraFormularioNoEncontradoException Si el EstructuraFormulario no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws EstructuraFormularioNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() estructuraformulario: {}", id);

        try {
            EstructuraFormularioDTO estructuraFormularioDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = estructuraformularioMapper.eliminar(id);
            logeador.info("estructuraformulario eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (EstructuraFormularioNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTRUCTURAFORMULARIO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.ESTRUCTURAFORMULARIO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un EstructuraFormulario por Clave.
     * @param id La Clave EstructuraFormulario a encontrar.
     * @return El EstructuraFormulario DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws EstructuraFormularioNoEncontradoException Si EstructuraFormulario no es encontrado.
     */
    public EstructuraFormularioDTO encontrarPorClave(Long id) throws BaseDatosException, EstructuraFormularioNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            EstructuraFormularioDTO estructuraFormularioDTO = mapper.toDto(estructuraformularioMapper.encontrarPorClave(id));

            if (estructuraFormularioDTO != null) {
                logeador.info("estructuraformulario encontrado por clave : {}", id);
            } else {
                logeador.info("estructuraformulario clave:{} no encontrado", id);
                throw new EstructuraFormularioNoEncontradoException(Constantes.ESTRUCTURAFORMULARIO_NO_ENCONTRADO_MENSAGE);
            }

            return estructuraFormularioDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTRUCTURAFORMULARIO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.ESTRUCTURAFORMULARIO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los EstructuraFormularios.
     * @return Una lista de todos EstructuraFormulario DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<EstructuraFormularioDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<EstructuraFormularioDTO> estructuraformularioLista = mapper.toDtoList(estructuraformularioMapper.obtenerTodos());
            logeador.info("estructuraformularios obtenidos");
            return estructuraformularioLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTRUCTURAFORMULARIO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.ESTRUCTURAFORMULARIO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
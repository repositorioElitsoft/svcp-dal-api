package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EstructuraFormularioDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EstructuraFormularioNoEncontradoException;
import com.elitsoft.servicampo.mapper.EstructuraFormularioMapper;
import com.elitsoft.servicampo.mapstruct.EstructuraFormularioMapStruct;
import com.elitsoft.servicampo.service.core.EstructuraFormularioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  EstructuraFormulario.
 */
@Component
public class EstructuraFormularioMobileService {

    @Autowired
    private EstructuraFormularioMapper estructuraFormularioMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstructuraFormularioService estructuraFormularioService; //Logica de Negocio del Core Service

    @Autowired
    private EstructuraFormularioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstructuraFormularioMobileService.class);

    /**
     * Agrega un nuevo EstructuraFormulario.
     * @param estructuraFormularioDTO El EstructuraFormulario DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(EstructuraFormularioDTO estructuraFormularioDTO) throws BaseDatosException {
        logeador.debug("agregar() estructuraformulario");
        estructuraFormularioService.agregar(estructuraFormularioDTO);
    }

    /**
     * Actualiza un EstructuraFormulario existente.
     * @param id La Clave de EstructuraFormulario a actualizar.
     * @param estructuraFormularioDTO El EstructuraFormulario DTO con informacion actualizada.
     * @throws EstructuraFormularioNoEncontradoException Si EstructuraFormulario no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, EstructuraFormularioDTO estructuraFormularioDTO) throws BaseDatosException, EstructuraFormularioNoEncontradoException {
        logeador.debug("actualizar() estructuraformulario");
        estructuraFormularioService.actualizar(id, estructuraFormularioDTO);
    }

    /**
     * Elimina EstructuraFormulario por Clave.
     * @param id La Clave de EstructuraFormulario a eliminar.
     * @throws EstructuraFormularioNoEncontradoException Si el EstructuraFormulario no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, EstructuraFormularioNoEncontradoException {
        logeador.debug("eliminar() estructuraformulario: {}", id);
        estructuraFormularioService.eliminar(id);
    }

    /**
     * Encuentra un EstructuraFormulario por Clave.
     * @param id La Clave EstructuraFormulario a encontrar.
     * @return El EstructuraFormulario DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws EstructuraFormularioNoEncontradoException Si EstructuraFormulario no es encontrado.
     */
    public EstructuraFormularioDTO encontrarPorClave(Long id) throws BaseDatosException, EstructuraFormularioNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return estructuraFormularioService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los EstructuraFormularios.
     * @return Una lista de todos EstructuraFormulario DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<EstructuraFormularioDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return estructuraFormularioService.obtenerTodos();
    }
}
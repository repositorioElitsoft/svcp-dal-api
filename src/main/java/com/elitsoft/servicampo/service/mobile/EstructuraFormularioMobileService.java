package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EstructuraFormularioDto;
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
    private EstructuraFormularioMapper estructuraformularioMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstructuraFormularioService estructuraformularioService; //Logica de Negocio del Core Service

    @Autowired
    private EstructuraFormularioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstructuraFormularioMobileService.class);

    /**
     * Agrega un nuevo EstructuraFormulario.
     * @param estructuraformularioDto El EstructuraFormulario DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(EstructuraFormularioDto estructuraformularioDto) throws BaseDatosException {
        logeador.debug("agregar() estructuraformulario");
        estructuraformularioService.agregar(estructuraformularioDto);
    }

    /**
     * Actualiza un EstructuraFormulario existente.
     * @param id La Clave de EstructuraFormulario a actualizar.
     * @param estructuraformularioDto El EstructuraFormulario DTO con informacion actualizada.
     * @throws EstructuraFormularioNoEncontradoException Si EstructuraFormulario no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, EstructuraFormularioDto estructuraformularioDto) throws BaseDatosException, EstructuraFormularioNoEncontradoException {
        logeador.debug("actualizar() estructuraformulario");
        estructuraformularioService.actualizar(id, estructuraformularioDto);
    }

    /**
     * Elimina EstructuraFormulario por Clave.
     * @param id La Clave de EstructuraFormulario a eliminar.
     * @throws EstructuraFormularioNoEncontradoException Si el EstructuraFormulario no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, EstructuraFormularioNoEncontradoException {
        logeador.debug("eliminar() estructuraformulario: {}", id);
        estructuraformularioService.eliminar(id);
    }

    /**
     * Encuentra un EstructuraFormulario por Clave.
     * @param id La Clave EstructuraFormulario a encontrar.
     * @return El EstructuraFormulario DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws EstructuraFormularioNoEncontradoException Si EstructuraFormulario no es encontrado.
     */
    public EstructuraFormularioDto encontrarPorClave(Long id) throws BaseDatosException, EstructuraFormularioNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return estructuraformularioService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los EstructuraFormularios.
     * @return Una lista de todos EstructuraFormulario DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<EstructuraFormularioDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return estructuraformularioService.obtenerTodos();
    }
}
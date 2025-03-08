package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ModuloDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.ModuloNoEncontradoException;
import com.elitsoft.servicampo.mapper.ModuloMapper;
import com.elitsoft.servicampo.mapstruct.ModuloMapStruct;
import com.elitsoft.servicampo.service.core.ModuloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Modulo.
 */
@Component
public class ModuloMobileService {

    @Autowired
    private ModuloMapper moduloMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ModuloService moduloService; //Logica de Negocio del Core Service

    @Autowired
    private ModuloMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ModuloMobileService.class);

    /**
     * Agrega un nuevo Modulo.
     * @param moduloDTO El Modulo DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(ModuloDTO moduloDTO) throws BaseDatosException {
        logeador.debug("agregar() modulo");
        moduloService.agregar(moduloDTO);
    }

    /**
     * Actualiza un Modulo existente.
     * @param id La Clave de Modulo a actualizar.
     * @param moduloDTO El Modulo DTO con informacion actualizada.
     * @throws ModuloNoEncontradoException Si Modulo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, ModuloDTO moduloDTO) throws BaseDatosException, ModuloNoEncontradoException {
        logeador.debug("actualizar() modulo");
        moduloService.actualizar(id, moduloDTO);
    }

    /**
     * Elimina Modulo por Clave.
     * @param id La Clave de Modulo a eliminar.
     * @throws ModuloNoEncontradoException Si el Modulo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, ModuloNoEncontradoException {
        logeador.debug("eliminar() modulo: {}", id);
        moduloService.eliminar(id);
    }

    /**
     * Encuentra un Modulo por Clave.
     * @param id La Clave Modulo a encontrar.
     * @return El Modulo DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws ModuloNoEncontradoException Si Modulo no es encontrado.
     */
    public ModuloDTO encontrarPorClave(Long id) throws BaseDatosException, ModuloNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return moduloService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Modulos.
     * @return Una lista de todos Modulo DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<ModuloDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return moduloService.obtenerTodos();
    }
}
package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.SubMenuDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.SubMenuNoEncontradoException;
import com.elitsoft.servicampo.mapper.SubMenuMapper;
import com.elitsoft.servicampo.mapstruct.SubMenuMapStruct;
import com.elitsoft.servicampo.service.core.SubMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  SubMenu.
 */
@Component
public class SubMenuMobileService {

    @Autowired
    private SubMenuMapper submenuMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SubMenuService submenuService; //Logica de Negocio del Core Service

    @Autowired
    private SubMenuMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SubMenuMobileService.class);

    /**
     * Agrega un nuevo SubMenu.
     * @param subMenuDTO El SubMenu DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(SubMenuDTO subMenuDTO) throws BaseDatosException {
        logeador.debug("agregar() submenu");
        submenuService.agregar(subMenuDTO);
    }

    /**
     * Actualiza un SubMenu existente.
     * @param id La Clave de SubMenu a actualizar.
     * @param subMenuDTO El SubMenu DTO con informacion actualizada.
     * @throws SubMenuNoEncontradoException Si SubMenu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, SubMenuDTO subMenuDTO) throws BaseDatosException, SubMenuNoEncontradoException {
        logeador.debug("actualizar() submenu");
        submenuService.actualizar(id, subMenuDTO);
    }

    /**
     * Elimina SubMenu por Clave.
     * @param id La Clave de SubMenu a eliminar.
     * @throws SubMenuNoEncontradoException Si el SubMenu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, SubMenuNoEncontradoException {
        logeador.debug("eliminar() submenu: {}", id);
        submenuService.eliminar(id);
    }

    /**
     * Encuentra un SubMenu por Clave.
     * @param id La Clave SubMenu a encontrar.
     * @return El SubMenu DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws SubMenuNoEncontradoException Si SubMenu no es encontrado.
     */
    public SubMenuDTO encontrarPorClave(Long id) throws BaseDatosException, SubMenuNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return submenuService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los SubMenus.
     * @return Una lista de todos SubMenu DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<SubMenuDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return submenuService.obtenerTodos();
    }
}
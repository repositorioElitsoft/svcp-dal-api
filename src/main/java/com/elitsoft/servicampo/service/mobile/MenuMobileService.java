package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.MenuDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.MenuNoEncontradoException;
import com.elitsoft.servicampo.mapper.MenuMapper;
import com.elitsoft.servicampo.mapstruct.MenuMapStruct;
import com.elitsoft.servicampo.service.core.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Menu.
 */
@Component
public class MenuMobileService {

    @Autowired
    private MenuMapper menuMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private MenuService menuService; //Logica de Negocio del Core Service

    @Autowired
    private MenuMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(MenuMobileService.class);

    /**
     * Agrega un nuevo Menu.
     * @param menuDTO El Menu DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(MenuDTO menuDTO) throws BaseDatosException {
        logeador.debug("agregar() menu");
        menuService.agregar(menuDTO);
    }

    /**
     * Actualiza un Menu existente.
     * @param id La Clave de Menu a actualizar.
     * @param menuDTO El Menu DTO con informacion actualizada.
     * @throws MenuNoEncontradoException Si Menu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, MenuDTO menuDTO) throws BaseDatosException, MenuNoEncontradoException {
        logeador.debug("actualizar() menu");
        menuService.actualizar(id, menuDTO);
    }

    /**
     * Elimina Menu por Clave.
     * @param id La Clave de Menu a eliminar.
     * @throws MenuNoEncontradoException Si el Menu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, MenuNoEncontradoException {
        logeador.debug("eliminar() menu: {}", id);
        menuService.eliminar(id);
    }

    /**
     * Encuentra un Menu por Clave.
     * @param id La Clave Menu a encontrar.
     * @return El Menu DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws MenuNoEncontradoException Si Menu no es encontrado.
     */
    public MenuDTO encontrarPorClave(Long id) throws BaseDatosException, MenuNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return menuService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Menus.
     * @return Una lista de todos Menu DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<MenuDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return menuService.obtenerTodos();
    }
}
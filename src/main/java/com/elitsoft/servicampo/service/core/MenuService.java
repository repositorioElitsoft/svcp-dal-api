package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.MenuDTO;
import com.elitsoft.servicampo.domain.entity.Menu;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.MenuMapper;
import com.elitsoft.servicampo.mapstruct.MenuMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Menu.
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private MenuMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(MenuService.class);

    /**
     * Agrega un nuevo Menu.
     * @param menuDTO El Menu DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(MenuDTO menuDTO) throws BaseDatosException {
        logeador.debug("agregar() menu");


        try {
            Menu menu = mapper.toEntity(menuDTO);
            Long nuevoId = menuMapper.agregar(menu);
            logeador.info("Menu agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.MENU_AGREGAR_EXECPTION + ": {}", menuDTO.toString(), e);
            throw new BaseDatosException(Constantes.MENU_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Menu existente.
     * @param id La Clave de Menu a actualizar.
     * @param menuDTO El Menu DTO con informacion actualizada.
     * @throws MenuNoEncontradoException Si Menu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, MenuDTO menuDTO) throws MenuNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() menu");

        try {
            MenuDTO menuDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Menu menu = mapper.toEntity(menuDTO);
            menu.setId(id);
            int registrosActualizados = menuMapper.actualizar(menu);
            logeador.info("menu actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (MenuNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MENU_ACTUALIZAR_EXECPTION + ": id={} {}", id, menuDTO.toString(), e);
            throw new BaseDatosException(Constantes.MENU_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Menu por Clave.
     * @param id La Clave de Menu a eliminar.
     * @throws MenuNoEncontradoException Si el Menu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws MenuNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() menu: {}", id);

        try {
            MenuDTO menuDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = menuMapper.eliminar(id);
            logeador.info("menu eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (MenuNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MENU_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.MENU_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Menu por Clave.
     * @param id La Clave Menu a encontrar.
     * @return El Menu DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws MenuNoEncontradoException Si Menu no es encontrado.
     */
    public MenuDTO encontrarPorClave(Long id) throws BaseDatosException, MenuNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            MenuDTO menuDTO = mapper.toDto(menuMapper.encontrarPorClave(id));

            if (menuDTO != null) {
                logeador.info("menu encontrado por clave : {}", id);
            } else {
                logeador.info("menu clave:{} no encontrado", id);
                throw new MenuNoEncontradoException(Constantes.MENU_NO_ENCONTRADO_MENSAGE);
            }

            return menuDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MENU_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.MENU_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Menus.
     * @return Una lista de todos Menu DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<MenuDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<MenuDTO> menuLista = mapper.toDtoList(menuMapper.obtenerTodos());
            logeador.info("menus obtenidos");
            return menuLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.MENU_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.MENU_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
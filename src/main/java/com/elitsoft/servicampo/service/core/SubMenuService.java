package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.SubMenuDTO;
import com.elitsoft.servicampo.domain.entity.SubMenu;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.SubMenuMapper;
import com.elitsoft.servicampo.mapstruct.SubMenuMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad SubMenu.
 */
@Service
@Transactional
public class SubMenuService {

    @Autowired
    private SubMenuMapper submenuMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SubMenuMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SubMenuService.class);

    /**
     * Agrega un nuevo SubMenu.
     * @param subMenuDTO El SubMenu DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(SubMenuDTO subMenuDTO) throws BaseDatosException {
        logeador.debug("agregar() submenu");


        try {
            SubMenu submenu = mapper.toEntity(subMenuDTO);
            Long nuevoId = submenuMapper.agregar(submenu);
            logeador.info("SubMenu agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SUBMENU_AGREGAR_EXECPTION + ": {}", subMenuDTO.toString(), e);
            throw new BaseDatosException(Constantes.SUBMENU_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un SubMenu existente.
     * @param id La Clave de SubMenu a actualizar.
     * @param subMenuDTO El SubMenu DTO con informacion actualizada.
     * @throws SubMenuNoEncontradoException Si SubMenu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, SubMenuDTO subMenuDTO) throws SubMenuNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() submenu");

        try {
            SubMenuDTO submenuDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe

            SubMenu submenu = mapper.toEntity(subMenuDTO);
            submenu.setId(id);
            int registrosActualizados = submenuMapper.actualizar(submenu);
            logeador.info("submenu actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (SubMenuNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SUBMENU_ACTUALIZAR_EXECPTION + ": id={} {}", id, subMenuDTO.toString(), e);
            throw new BaseDatosException(Constantes.SUBMENU_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina SubMenu por Clave.
     * @param id La Clave de SubMenu a eliminar.
     * @throws SubMenuNoEncontradoException Si el SubMenu no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws SubMenuNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() submenu: {}", id);

        try {
            SubMenuDTO subMenuDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = submenuMapper.eliminar(id);
            logeador.info("submenu eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (SubMenuNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SUBMENU_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.SUBMENU_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un SubMenu por Clave.
     * @param id La Clave SubMenu a encontrar.
     * @return El SubMenu DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws SubMenuNoEncontradoException Si SubMenu no es encontrado.
     */
    public SubMenuDTO encontrarPorClave(Long id) throws BaseDatosException, SubMenuNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            SubMenuDTO subMenuDTO = mapper.toDto(submenuMapper.encontrarPorClave(id));

            if (subMenuDTO != null) {
                logeador.info("submenu encontrado por clave : {}", id);
            } else {
                logeador.info("submenu clave:{} no encontrado", id);
                throw new SubMenuNoEncontradoException(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE);
            }

            return subMenuDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SUBMENU_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.SUBMENU_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los SubMenus.
     * @return Una lista de todos SubMenu DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<SubMenuDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<SubMenuDTO> subMenuLista = mapper.toDtoList(submenuMapper.obtenerTodos());
            logeador.info("submenus obtenidos");
            return subMenuLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SUBMENU_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.SUBMENU_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.SubMenuDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.SubMenuService;
import com.elitsoft.servicampo.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Gestiona las peticiones y respuestas http relativas a SubMenu
 */
@RestController
@RequestMapping("/submenu")
public class SubMenuController {

    @Autowired
    private SubMenuService subMenuService;

    private static final Logger logeador = LoggerFactory.getLogger(SubMenuController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un submenu", description = "Agrega un nuevo submenu al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SubMenu agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody SubMenuDTO subMenuDTO) {
        logeador.debug("agregar() submenu");

        try {
            subMenuService.agregar(subMenuDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un submenu", description = "Actualiza un submenu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SubMenu actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "SubMenu no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody SubMenuDTO subMenuDTO) {
        logeador.debug("actualizar() submenu");

        try {
            subMenuService.actualizar(id, subMenuDTO);
            return ResponseEntity.noContent().build();
        } catch (SubMenuNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un submenu", description = "Elimina un submenu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SubMenu eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() submenu: {}", id);

        try {
            subMenuService.eliminar(id);
        } catch (SubMenuNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un submenu", description = "Encuentra un submenu por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SubMenu encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "SubMenu no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<SubMenuDTO> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            SubMenuDTO submenuDto = subMenuService.encontrarPorClave(id);
            return ResponseEntity.ok(submenuDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (SubMenuNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los submenu", description = "Obtiene todos los submenu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SubMenus obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<SubMenuDTO>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<SubMenuDTO> submenus = null;

        try {
            submenus = subMenuService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(submenus);
    }
}
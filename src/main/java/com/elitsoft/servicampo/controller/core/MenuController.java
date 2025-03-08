package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.MenuDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.MenuService;
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
 * Gestiona las peticiones y respuestas http relativas a Menu
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    private static final Logger logeador = LoggerFactory.getLogger(MenuController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un menu", description = "Agrega un nuevo menu al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Menu agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody MenuDTO menuDTO) {
        logeador.debug("agregar() menu");

        try {
            menuService.agregar(menuDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un menu", description = "Actualiza un menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Menu actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody MenuDTO menuDTO) {
        logeador.debug("actualizar() menu");

        try {
            menuService.actualizar(id, menuDTO);
            return ResponseEntity.noContent().build();
        } catch (MenuNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.MENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un menu", description = "Elimina un menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Menu eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() menu: {}", id);

        try {
            menuService.eliminar(id);
        } catch (MenuNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.MENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un menu", description = "Encuentra un menu por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<MenuDTO> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            MenuDTO menuDto = menuService.encontrarPorClave(id);
            return ResponseEntity.ok(menuDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (MenuNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los menu", description = "Obtiene todos los menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menus obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<MenuDTO>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<MenuDTO> menus = null;

        try {
            menus = menuService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(menus);
    }
}
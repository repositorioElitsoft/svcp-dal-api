package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.MenuDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.MenuMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a Menu para la version mobile
 */
@RestController
@RequestMapping("/mobile/menu")
public class MenuMobileController {

    @Autowired
    private MenuMobileService menuMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(MenuMobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un menu", description = "Agrega un nuevo menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Menu agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody MenuDto menuDto) {
        logeador.debug("agregar() menu");

        try {
            menuMobileService.agregar(menuDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
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
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody MenuDto menuDto) {
        logeador.debug("actualizar() menu");

        try {
            menuMobileService.actualizar(id, menuDto);
            return ResponseEntity.noContent().build();
        } catch (MenuNoEncontradoException e) {
            logeador.error(Constantes.MENU_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.MENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
            menuMobileService.eliminar(id);
        } catch (MenuNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.MENU_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.MENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
    public ResponseEntity<MenuDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            MenuDto menuDto = menuMobileService.encontrarPorClave(id);
            if (menuDto != null) {
                return ResponseEntity.ok(menuDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (MenuNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.MENU_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los menu", description = "Obtiene todos los menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menus obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<MenuDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<MenuDto> menus = null;

        try {
            menus = menuMobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(menus);
    }
}
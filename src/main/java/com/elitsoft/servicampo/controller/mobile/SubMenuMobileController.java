package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.SubMenuDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.SubMenuMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a SubMenu para la version mobile
 */
@RestController
@RequestMapping("/mobile/submenu")
public class SubMenuMobileController {

    @Autowired
    private SubMenuMobileService submenuMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(SubMenuMobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un submenu", description = "Agrega un nuevo submenu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SubMenu agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody SubMenuDTO submenuDto) {
        logeador.debug("agregar() submenu");

        try {
            submenuMobileService.agregar(submenuDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
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
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody SubMenuDTO submenuDto) {
        logeador.debug("actualizar() submenu");

        try {
            submenuMobileService.actualizar(id, submenuDto);
            return ResponseEntity.noContent().build();
        } catch (SubMenuNoEncontradoException e) {
            logeador.error(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
            submenuMobileService.eliminar(id);
        } catch (SubMenuNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
            SubMenuDTO submenuDto = submenuMobileService.encontrarPorClave(id);
            if (submenuDto != null) {
                return ResponseEntity.ok(submenuDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (SubMenuNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.SUBMENU_NO_ENCONTRADO_MENSAGE + ": {}", id);
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
            submenus = submenuMobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(submenus);
    }
}
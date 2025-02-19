package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.PermisoDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.PermisoService;
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
 * Gestiona las peticiones y respuestas http relativas a Permiso
 */
@RestController
@RequestMapping("/permiso")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    private static final Logger logeador = LoggerFactory.getLogger(PermisoController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un permiso", description = "Agrega un nuevo permiso al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permiso agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody PermisoDto permisoDto) {
        logeador.debug("agregar() permiso");

        try {
            permisoService.agregar(permisoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un permiso", description = "Actualiza un permiso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permiso actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody PermisoDto permisoDto) {
        logeador.debug("actualizar() permiso");

        try {
            permisoService.actualizar(id, permisoDto);
            return ResponseEntity.noContent().build();
        } catch (PermisoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.PERMISO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un permiso", description = "Elimina un permiso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permiso eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() permiso: {}", id);

        try {
            permisoService.eliminar(id);
        } catch (PermisoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.PERMISO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un permiso", description = "Encuentra un permiso por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PermisoDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            PermisoDto permisoDto = permisoService.encontrarPorClave(id);
            return ResponseEntity.ok(permisoDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (PermisoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los permiso", description = "Obtiene todos los permiso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permisos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<PermisoDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<PermisoDto> permisos = null;

        try {
            permisos = permisoService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(permisos);
    }
}
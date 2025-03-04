package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.RoleDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.RoleService;
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
 * Gestiona las peticiones y respuestas http relativas a Role
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private static final Logger logeador = LoggerFactory.getLogger(RoleController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un role", description = "Agrega un nuevo role al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody RoleDto roleDto) {
        logeador.debug("agregar() role");

        try {
            roleService.agregar(roleDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un role", description = "Actualiza un role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Role no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        logeador.debug("actualizar() role");

        try {
            roleService.actualizar(id, roleDto);
            return ResponseEntity.noContent().build();
        } catch (RoleNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.ROLE_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un role", description = "Elimina un role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() role: {}", id);

        try {
            roleService.eliminar(id);
        } catch (RoleNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.ROLE_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un role", description = "Encuentra un role por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Role no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<RoleDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            RoleDto roleDto = roleService.encontrarPorClave(id);
            return ResponseEntity.ok(roleDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (RoleNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los role", description = "Obtiene todos los role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<RoleDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<RoleDto> roles = null;

        try {
            roles = roleService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(roles);
    }
}
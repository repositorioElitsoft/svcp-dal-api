package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.ClasificacionClienteService;
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
 * Gestiona las peticiones y respuestas http relativas a ClasificacionCliente
 */
@RestController
@RequestMapping("/clasificacioncliente")
public class ClasificacionClienteController {

    @Autowired
    private ClasificacionClienteService clasificacionclienteService;

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un clasificacioncliente", description = "Agrega un nuevo clasificacioncliente al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ClasificacionCliente agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody ClasificacionClienteDto clasificacionclienteDto) {
        logeador.debug("agregar() clasificacioncliente");

        try {
            clasificacionclienteService.agregar(clasificacionclienteDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un clasificacioncliente", description = "Actualiza un clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ClasificacionCliente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "ClasificacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody ClasificacionClienteDto clasificacionclienteDto) {
        logeador.debug("actualizar() clasificacioncliente");

        try {
            clasificacionclienteService.actualizar(id, clasificacionclienteDto);
            return ResponseEntity.noContent().build();
        } catch (ClasificacionClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.CLASIFICACIONCLIENTE_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un clasificacioncliente", description = "Elimina un clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ClasificacionCliente eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() clasificacioncliente: {}", id);

        try {
            clasificacionclienteService.eliminar(id);
        } catch (ClasificacionClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.CLASIFICACIONCLIENTE_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un clasificacioncliente", description = "Encuentra un clasificacioncliente por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClasificacionCliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "ClasificacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ClasificacionClienteDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            ClasificacionClienteDto clasificacionclienteDto = clasificacionclienteService.encontrarPorClave(id);
            return ResponseEntity.ok(clasificacionclienteDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ClasificacionClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los clasificacioncliente", description = "Obtiene todos los clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClasificacionClientes obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<ClasificacionClienteDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<ClasificacionClienteDto> clasificacionclientes = null;

        try {
            clasificacionclientes = clasificacionclienteService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(clasificacionclientes);
    }
}
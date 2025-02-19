package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.EstructuraFormularioDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.EstructuraFormularioService;
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
 * Gestiona las peticiones y respuestas http relativas a EstructuraFormulario
 */
@RestController
@RequestMapping("/estructuraformulario")
public class EstructuraFormularioController {

    @Autowired
    private EstructuraFormularioService estructuraformularioService;

    private static final Logger logeador = LoggerFactory.getLogger(EstructuraFormularioController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un estructuraformulario", description = "Agrega un nuevo estructuraformulario al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "EstructuraFormulario agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody EstructuraFormularioDto estructuraformularioDto) {
        logeador.debug("agregar() estructuraformulario");

        try {
            estructuraformularioService.agregar(estructuraformularioDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un estructuraformulario", description = "Actualiza un estructuraformulario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "EstructuraFormulario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "EstructuraFormulario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody EstructuraFormularioDto estructuraformularioDto) {
        logeador.debug("actualizar() estructuraformulario");

        try {
            estructuraformularioService.actualizar(id, estructuraformularioDto);
            return ResponseEntity.noContent().build();
        } catch (EstructuraFormularioNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.ESTRUCTURAFORMULARIO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un estructuraformulario", description = "Elimina un estructuraformulario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "EstructuraFormulario eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() estructuraformulario: {}", id);

        try {
            estructuraformularioService.eliminar(id);
        } catch (EstructuraFormularioNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.ESTRUCTURAFORMULARIO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un estructuraformulario", description = "Encuentra un estructuraformulario por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EstructuraFormulario encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "EstructuraFormulario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<EstructuraFormularioDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            EstructuraFormularioDto estructuraformularioDto = estructuraformularioService.encontrarPorClave(id);
            return ResponseEntity.ok(estructuraformularioDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (EstructuraFormularioNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los estructuraformulario", description = "Obtiene todos los estructuraformulario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EstructuraFormularios obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<EstructuraFormularioDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<EstructuraFormularioDto> estructuraformularios = null;

        try {
            estructuraformularios = estructuraformularioService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(estructuraformularios);
    }
}
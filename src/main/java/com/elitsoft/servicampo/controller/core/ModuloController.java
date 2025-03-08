package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.ModuloDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.ModuloService;
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
 * Gestiona las peticiones y respuestas http relativas a Modulo
 */
@RestController
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    private static final Logger logeador = LoggerFactory.getLogger(ModuloController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un modulo", description = "Agrega un nuevo modulo al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Modulo agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody ModuloDTO moduloDTO) {
        logeador.debug("agregar() modulo");

        try {
            moduloService.agregar(moduloDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un modulo", description = "Actualiza un modulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modulo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Modulo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody ModuloDTO moduloDTO) {
        logeador.debug("actualizar() modulo");

        try {
            moduloService.actualizar(id, moduloDTO);
            return ResponseEntity.noContent().build();
        } catch (ModuloNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.MODULO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un modulo", description = "Elimina un modulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modulo eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() modulo: {}", id);

        try {
            moduloService.eliminar(id);
        } catch (ModuloNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.MODULO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un modulo", description = "Encuentra un modulo por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modulo encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Modulo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ModuloDTO> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            ModuloDTO moduloDto = moduloService.encontrarPorClave(id);
            return ResponseEntity.ok(moduloDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ModuloNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los modulo", description = "Obtiene todos los modulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modulos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<ModuloDTO>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<ModuloDTO> modulos = null;

        try {
            modulos = moduloService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(modulos);
    }
}
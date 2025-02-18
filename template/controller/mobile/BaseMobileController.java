package com.elitsoft.#app_name#.controller.mobile;

import com.elitsoft.#app_name#.domain.dto.core.#Base#Dto;
import com.elitsoft.#app_name#.exceptions.*;
import com.elitsoft.#app_name#.service.mobile.#Base#MobileService;
import com.elitsoft.#app_name#.utils.Constantes;
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
 * Gestiona las peticiones y respuestas http relativas a #Base# para la version mobile
 */
@RestController
@RequestMapping("/mobile/#base#")
public class #Base#MobileController {

    @Autowired
    private #Base#MobileService #base#MobileService;

    private static final Logger logeador = LoggerFactory.getLogger(#Base#MobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un #base#", description = "Agrega un nuevo #base#")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "#Base# agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody #Base#Dto #base#Dto) {
        logeador.debug("agregar() #base#");

        try {
            #base#MobileService.agregar(#base#Dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un #base#", description = "Actualiza un #base#")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "#Base# actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "#Base# no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody #Base#Dto #base#Dto) {
        logeador.debug("actualizar() #base#");

        try {
            #base#MobileService.actualizar(id, #base#Dto);
            return ResponseEntity.noContent().build();
        } catch (#Base#NoEncontradoException e) {
            logeador.error(Constantes.#BASE#_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.#BASE#_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un #base#", description = "Elimina un #base#")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "#Base# eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() #base#: {}", id);

        try {
            #base#MobileService.eliminar(id);
        } catch (#Base#NoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.#BASE#_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.#BASE#_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un #base#", description = "Encuentra un #base# por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#Base# encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "#Base# no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<#Base#Dto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            #Base#Dto #base#Dto = #base#MobileService.encontrarPorClave(id);
            if (#base#Dto != null) {
                return ResponseEntity.ok(#base#Dto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (#Base#NoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.#BASE#_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los #base#", description = "Obtiene todos los #base#")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#Base#s obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<#Base#Dto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<#Base#Dto> #base#s = null;

        try {
            #base#s = #base#MobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(#base#s);
    }
}
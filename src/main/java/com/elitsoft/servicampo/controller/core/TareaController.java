package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.TareaDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.TareaService;
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
 * Gestiona las peticiones y respuestas http relativas a Tarea
 */
@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    private static final Logger logeador = LoggerFactory.getLogger(TareaController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tarea", description = "Agrega un nuevo tarea al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TareaDto tareaDto) {
        logeador.debug("agregar() tarea");

        try {
            tareaService.agregar(tareaDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un tarea", description = "Actualiza un tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarea actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody TareaDto tareaDto) {
        logeador.debug("actualizar() tarea");

        try {
            tareaService.actualizar(id, tareaDto);
            return ResponseEntity.noContent().build();
        } catch (TareaNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TAREA_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un tarea", description = "Elimina un tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarea eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tarea: {}", id);

        try {
            tareaService.eliminar(id);
        } catch (TareaNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TAREA_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un tarea", description = "Encuentra un tarea por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TareaDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            TareaDto tareaDto = tareaService.encontrarPorClave(id);
            return ResponseEntity.ok(tareaDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (TareaNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tarea", description = "Obtiene todos los tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tareas obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<TareaDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<TareaDto> tareas = null;

        try {
            tareas = tareaService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(tareas);
    }
}
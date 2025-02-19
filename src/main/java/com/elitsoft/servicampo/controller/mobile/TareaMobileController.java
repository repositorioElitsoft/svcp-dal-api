package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.TareaDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.TareaMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a Tarea para la version mobile
 */
@RestController
@RequestMapping("/mobile/tarea")
public class TareaMobileController {

    @Autowired
    private TareaMobileService tareaMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(TareaMobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tarea", description = "Agrega un nuevo tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TareaDto tareaDto) {
        logeador.debug("agregar() tarea");

        try {
            tareaMobileService.agregar(tareaDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
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
            tareaMobileService.actualizar(id, tareaDto);
            return ResponseEntity.noContent().build();
        } catch (TareaNoEncontradoException e) {
            logeador.error(Constantes.TAREA_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TAREA_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
            tareaMobileService.eliminar(id);
        } catch (TareaNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TAREA_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TAREA_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
            TareaDto tareaDto = tareaMobileService.encontrarPorClave(id);
            if (tareaDto != null) {
                return ResponseEntity.ok(tareaDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (TareaNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TAREA_NO_ENCONTRADO_MENSAGE + ": {}", id);
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
            tareas = tareaMobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(tareas);
    }
}
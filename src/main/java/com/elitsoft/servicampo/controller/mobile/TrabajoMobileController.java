package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.TrabajoMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a Trabajo para la version mobile
 */
@RestController
@RequestMapping("/mobile/trabajo")
public class TrabajoMobileController {

    @Autowired
    private TrabajoMobileService trabajoMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoMobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un trabajo", description = "Agrega un nuevo trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trabajo agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TrabajoDto trabajoDto) {
        logeador.debug("agregar() trabajo");

        try {
            trabajoMobileService.agregar(trabajoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un trabajo", description = "Actualiza un trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trabajo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Trabajo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody TrabajoDto trabajoDto) {
        logeador.debug("actualizar() trabajo");

        try {
            trabajoMobileService.actualizar(id, trabajoDto);
            return ResponseEntity.noContent().build();
        } catch (TrabajoNoEncontradoException e) {
            logeador.error(Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un trabajo", description = "Elimina un trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trabajo eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() trabajo: {}", id);

        try {
            trabajoMobileService.eliminar(id);
        } catch (TrabajoNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un trabajo", description = "Encuentra un trabajo por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajo encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Trabajo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TrabajoDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            TrabajoDto trabajoDto = trabajoMobileService.encontrarPorClave(id);
            if (trabajoDto != null) {
                return ResponseEntity.ok(trabajoDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (TrabajoNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los trabajo", description = "Obtiene todos los trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<TrabajoDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<TrabajoDto> trabajos = null;

        try {
            trabajos = trabajoMobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(trabajos);
    }
}
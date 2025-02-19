package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.ZonaDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.ZonaMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a Zona para la version mobile
 */
@RestController
@RequestMapping("/mobile/zona")
public class ZonaMobileController {

    @Autowired
    private ZonaMobileService zonaMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(ZonaMobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un zona", description = "Agrega un nuevo zona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Zona agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody ZonaDto zonaDto) {
        logeador.debug("agregar() zona");

        try {
            zonaMobileService.agregar(zonaDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un zona", description = "Actualiza un zona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Zona actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Zona no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody ZonaDto zonaDto) {
        logeador.debug("actualizar() zona");

        try {
            zonaMobileService.actualizar(id, zonaDto);
            return ResponseEntity.noContent().build();
        } catch (ZonaNoEncontradoException e) {
            logeador.error(Constantes.ZONA_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.ZONA_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un zona", description = "Elimina un zona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Zona eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() zona: {}", id);

        try {
            zonaMobileService.eliminar(id);
        } catch (ZonaNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.ZONA_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.ZONA_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un zona", description = "Encuentra un zona por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zona encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Zona no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ZonaDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            ZonaDto zonaDto = zonaMobileService.encontrarPorClave(id);
            if (zonaDto != null) {
                return ResponseEntity.ok(zonaDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (ZonaNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.ZONA_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los zona", description = "Obtiene todos los zona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zonas obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<ZonaDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<ZonaDto> zonas = null;

        try {
            zonas = zonaMobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(zonas);
    }
}
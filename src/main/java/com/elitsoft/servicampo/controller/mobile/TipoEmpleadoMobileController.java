package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.TipoEmpleadoMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoEmpleado para la version mobile
 */
@RestController
@RequestMapping("/mobile/tipoempleado")
public class TipoEmpleadoMobileController {

    @Autowired
    private TipoEmpleadoMobileService tipoempleadoMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoMobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tipoempleado", description = "Agrega un nuevo tipoempleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoEmpleado agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TipoEmpleadoDto tipoempleadoDto) {
        logeador.debug("agregar() tipoempleado");

        try {
            tipoempleadoMobileService.agregar(tipoempleadoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un tipoempleado", description = "Actualiza un tipoempleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoEmpleado actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoEmpleado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody TipoEmpleadoDto tipoempleadoDto) {
        logeador.debug("actualizar() tipoempleado");

        try {
            tipoempleadoMobileService.actualizar(id, tipoempleadoDto);
            return ResponseEntity.noContent().build();
        } catch (TipoEmpleadoNoEncontradoException e) {
            logeador.error(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un tipoempleado", description = "Elimina un tipoempleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoEmpleado eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tipoempleado: {}", id);

        try {
            tipoempleadoMobileService.eliminar(id);
        } catch (TipoEmpleadoNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un tipoempleado", description = "Encuentra un tipoempleado por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoEmpleado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoEmpleado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TipoEmpleadoDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            TipoEmpleadoDto tipoempleadoDto = tipoempleadoMobileService.encontrarPorClave(id);
            if (tipoempleadoDto != null) {
                return ResponseEntity.ok(tipoempleadoDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (TipoEmpleadoNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tipoempleado", description = "Obtiene todos los tipoempleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoEmpleados obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<TipoEmpleadoDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<TipoEmpleadoDto> tipoempleados = null;

        try {
            tipoempleados = tipoempleadoMobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(tipoempleados);
    }
}
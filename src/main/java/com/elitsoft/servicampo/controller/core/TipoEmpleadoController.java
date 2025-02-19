package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.TipoEmpleadoService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoEmpleado
 */
@RestController
@RequestMapping("/tipoempleado")
public class TipoEmpleadoController {

    @Autowired
    private TipoEmpleadoService tipoempleadoService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tipoempleado", description = "Agrega un nuevo tipoempleado al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoEmpleado agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TipoEmpleadoDto tipoempleadoDto) {
        logeador.debug("agregar() tipoempleado");

        try {
            tipoempleadoService.agregar(tipoempleadoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
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
            tipoempleadoService.actualizar(id, tipoempleadoDto);
            return ResponseEntity.noContent().build();
        } catch (TipoEmpleadoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
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
            tipoempleadoService.eliminar(id);
        } catch (TipoEmpleadoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
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
            TipoEmpleadoDto tipoempleadoDto = tipoempleadoService.encontrarPorClave(id);
            return ResponseEntity.ok(tipoempleadoDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (TipoEmpleadoNoEncontradoException e) {
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
            tipoempleados = tipoempleadoService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(tipoempleados);
    }
}
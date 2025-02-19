package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.TipoProductoService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoProducto
 */
@RestController
@RequestMapping("/tipoproducto")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoproductoService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tipoproducto", description = "Agrega un nuevo tipoproducto al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoProducto agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TipoProductoDto tipoproductoDto) {
        logeador.debug("agregar() tipoproducto");

        try {
            tipoproductoService.agregar(tipoproductoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un tipoproducto", description = "Actualiza un tipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoProducto actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoProducto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody TipoProductoDto tipoproductoDto) {
        logeador.debug("actualizar() tipoproducto");

        try {
            tipoproductoService.actualizar(id, tipoproductoDto);
            return ResponseEntity.noContent().build();
        } catch (TipoProductoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un tipoproducto", description = "Elimina un tipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoProducto eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tipoproducto: {}", id);

        try {
            tipoproductoService.eliminar(id);
        } catch (TipoProductoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un tipoproducto", description = "Encuentra un tipoproducto por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoProducto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoProducto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TipoProductoDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            TipoProductoDto tipoproductoDto = tipoproductoService.encontrarPorClave(id);
            return ResponseEntity.ok(tipoproductoDto);
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        } catch (TipoProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tipoproducto", description = "Obtiene todos los tipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoProductos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<TipoProductoDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<TipoProductoDto> tipoproductos = null;

        try {
            tipoproductos = tipoproductoService.obtenerTodos();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(tipoproductos);
    }
}
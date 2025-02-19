package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.TipoProductoMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoProducto para la version mobile
 */
@RestController
@RequestMapping("/mobile/tipoproducto")
public class TipoProductoMobileController {

    @Autowired
    private TipoProductoMobileService tipoproductoMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoMobileController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tipoproducto", description = "Agrega un nuevo tipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoProducto agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TipoProductoDto tipoproductoDto) {
        logeador.debug("agregar() tipoproducto");

        try {
            tipoproductoMobileService.agregar(tipoproductoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
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
            tipoproductoMobileService.actualizar(id, tipoproductoDto);
            return ResponseEntity.noContent().build();
        } catch (TipoProductoNoEncontradoException e) {
            logeador.error(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
            tipoproductoMobileService.eliminar(id);
        } catch (TipoProductoNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE + ": {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
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
            TipoProductoDto tipoproductoDto = tipoproductoMobileService.encontrarPorClave(id);
            if (tipoproductoDto != null) {
                return ResponseEntity.ok(tipoproductoDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (BaseDatosException e) {
            logeador.error("id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (TipoProductoNoEncontradoException e) { // Corrected Exception Name
            logeador.error(Constantes.TIPOPRODUCTO_NO_ENCONTRADO_MENSAGE + ": {}", id);
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
            tipoproductos = tipoproductoMobileService.obtenerTodos();
        } catch (BaseDatosException e) {
            logeador.error("{}: ", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(tipoproductos);
    }
}
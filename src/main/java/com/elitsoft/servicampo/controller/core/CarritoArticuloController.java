package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.CarritoArticuloDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.CarritoArticuloService;
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
 * Gestiona las peticiones y respuestas http relativas a CarritoArticulo
 */
@RestController
@RequestMapping("/carrito")
public class CarritoArticuloController {

    @Autowired
    private CarritoArticuloService carritoArticuloService; //Logica de Negocio del Core Service

    private static final Logger logeador = LoggerFactory.getLogger(CarritoArticuloController.class);


    /**
     * Llama al servicio de Agregar una Articulo de Carrito
     *
     * @param carritoArticuloDto
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un articulo del carrito", description = "Agrega un nuevo articulo al carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Articulo de carrito agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody CarritoArticuloDto carritoArticuloDto) {
        logeador.info("agregar() articulo de carrito" );

        try{
            carritoArticuloService.agregar (carritoArticuloDto);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201 Bad Request Not Found
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constantes.CARRITO_ARTICULO_ENTRADA_INVALIDA);// Retorna  400 Bad Request
        }
        catch (CarritoArticuloLimiteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constantes.CARRITO_ARTICULO_EXEDE_LIMITE);// Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna 500 Internal Server Error
        }

    }


    /**
     * Llama al servicio de Actualizar un Articulo de Carrito
     * @param id
     * @param carritoArticuloDto
     * @return
     */
    @PutMapping( value = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un articulo del carrito", description = "Actualiza un articulo del carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Articulo de carrito actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Articulo de carrito no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody CarritoArticuloDto carritoArticuloDto) {
        logeador.info("actualizar() articulo de carrito");

        try {
            carritoArticuloService.actualizar(id, carritoArticuloDto);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (CarritoArticuloNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.CARRITO_ARTICULO_NO_ENCONTRADO_MENSAGE );// Retorna 404 Not Found
        }
        catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna 500 Internal Server Error
        }
    }

    /**
     * Llama al servicio de Eliminar un Articulo de Carrito
     *
     * @param id
     * @return
     */
    @DeleteMapping( value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un articulo del carrito", description = "Elimina un articulo del carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Articulo de carrito eliminado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.info("eliminar() articulo de carrito: {}", id);

        try {
            carritoArticuloService.eliminar (id);
        } catch (CarritoArticuloNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.CARRITO_ARTICULO_NO_ENCONTRADO_MENSAGE );// Retorna 404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna 500 Internal Server Error
        }
        return ResponseEntity.noContent().build();  // Retorna 204 No Content
    }

    /**
     * Llama al servicio de Encontrar un Articulo de Carrito
     * @param id
     * @return
     */
    @GetMapping( value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE )
    @Operation(summary = "Encuentra un articulo del carrito", description = "Encuentra un articulo del carrito de compras por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articulo de carrito encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Articulo de carrito no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<CarritoArticuloDto> encontrarPorClave(@PathVariable Long id) {
        logeador.info("encontrarPorClave(): {}", id);

        try {
            CarritoArticuloDto carritoArticuloDto = carritoArticuloService.encontrarPorClave(id);
            return ResponseEntity.ok(carritoArticuloDto); // Retorna 200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna 500 Internal Server Error
        } catch (CarritoArticuloNoEncontradoException e) {
            return ResponseEntity.notFound().build();// Retorna 404 Not Found
        }

    }

    /**
     * Llama al servicio de Obtener todos los Articulo de Carrito
     * @return
     */
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
    @Operation(summary = "Obtiene todos  los articulo de carrito", description = "Obtiene todos los articulo del carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articulos de carrito obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<CarritoArticuloDto>> obtenerTodos() {
        logeador.info("obtenerTodos()");

        List<CarritoArticuloDto> carritoArticulos = null;

        try {
            carritoArticulos = carritoArticuloService.obtenerTodos ();
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna 500 Internal Server Error
        }

        return ResponseEntity.ok(carritoArticulos); // Retorna 200 OK
    }

}
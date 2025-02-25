package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.ClasificacionClienteService;
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
 * Gestiona las peticiones y respuestas http relativas a ClasificacionCliente
 */
@RestController
@RequestMapping("/clasificacioncliente")
public class ClasificacionClienteController {

    @Autowired
    private ClasificacionClienteService clasificacionclienteService;

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un clasificacioncliente", description = "Agrega un nuevo clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ClasificacionCliente agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "ClasificacionCliente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ClasificacionClienteDto> agregar(@RequestBody ClasificacionClienteDto clasificacionclienteDto) {
        logeador.debug("agregar() clasificacioncliente");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(clasificacionclienteService.agregar(clasificacionclienteDto)); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna  400 Bad Request
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna  500 Internal Server Error
        }

    }


    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de clasificacioncliente", description = "Agrega una lista de nuevos clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista ClasificacionCliente agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "ClasificacionCliente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregarLote(@RequestBody List<ClasificacionClienteDto> clasificacionclientesDto) {
        logeador.debug("agregarLote() clasificacioncliente");

        try {
            clasificacionclienteService.agregarLote (clasificacionclientesDto);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Retorna  500 Internal Server Error
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un clasificacioncliente", description = "Actualiza un clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ClasificacionCliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "ClasificacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody ClasificacionClienteDto clasificacionclienteDto) {
        logeador.debug("actualizar() clasificacioncliente");

        try {
            clasificacionclienteService.actualizar(id, clasificacionclienteDto);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Retorna  404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza lista de clasificacioncliente", description = "Actualiza una lista de clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote ClasificacionCliente actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarLote(@RequestBody List<ClasificacionClienteDto> clasificacionclienteLoteDto) {
        logeador.debug("actualizarLote() clasificacioncliente");

        try {
            clasificacionclienteService.actualizarLote(clasificacionclienteLoteDto);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un clasificacioncliente", description = "Elimina un clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ClasificacionCliente eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "ClasificacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() clasificacioncliente: {}", id);

        try {
            clasificacionclienteService.eliminar(id);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Retorna  404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina Lista de clasificacioncliente", description = "Elimina una Lista de clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista ClasificacionCliente eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() clasificacioncliente");

        try {
            clasificacionclienteService.eliminarLote(idLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un clasificacioncliente", description = "Encuentra un clasificacioncliente por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClasificacionCliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "ClasificacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ClasificacionClienteDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            ClasificacionClienteDto clasificacionclienteDto = clasificacionclienteService.encontrarPorClave(id);
            return ResponseEntity.ok(clasificacionclienteDto); // Retorna  200
        }
        catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los clasificacioncliente", description = "Obtiene todos los clasificacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClasificacionClientes obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<ClasificacionClienteDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            List<ClasificacionClienteDto> clasificacionclientes = null;
            clasificacionclientes = clasificacionclienteService.obtenerTodos();
            return ResponseEntity.ok(clasificacionclientes);  // Retorna  200
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
    }
}
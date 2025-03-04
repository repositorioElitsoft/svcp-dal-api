package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.SegmentacionClienteDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.SegmentacionClienteService;
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
 * Gestiona las peticiones y respuestas http relativas a SegmentacionCliente
 */
@RestController
@RequestMapping("/segmentacion-cliente")
public class SegmentacionClienteController {

    @Autowired
    private SegmentacionClienteService segmentacionClienteService;

    private static final Logger logeador = LoggerFactory.getLogger(SegmentacionClienteController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un segmentacioncliente", description = "Agrega un nuevo segmentacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SegmentacionCliente agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "SegmentacionCliente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<SegmentacionClienteDto> agregar(@RequestBody SegmentacionClienteDto segmentacionClienteDto) {
        logeador.debug("agregar() segmentacioncliente");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(segmentacionClienteService.agregar(segmentacionClienteDto)); // Retorna  201 Created
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
    @Operation(summary = "Agrega lista de segmentacioncliente", description = "Agrega una lista de nuevos segmentacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista SegmentacionCliente agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "SegmentacionCliente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregarLote(@RequestBody List<SegmentacionClienteDto> segmentacionClienteLoteDto) {
        logeador.debug("agregarLote() segmentacioncliente");

        try {
            segmentacionClienteService.agregarLote (segmentacionClienteLoteDto);
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
    @Operation(summary = "Actualiza un segmentacioncliente", description = "Actualiza un segmentacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SegmentacionCliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "SegmentacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody SegmentacionClienteDto segmentacionClienteDto) {
        logeador.debug("actualizar() segmentacioncliente");

        try {
            segmentacionClienteService.actualizar(id, segmentacionClienteDto);
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
    @Operation(summary = "Actualiza lista de segmentacioncliente", description = "Actualiza una lista de segmentacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote SegmentacionCliente actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarLote(@RequestBody List<SegmentacionClienteDto> segmentacionClienteLoteDto) {
        logeador.debug("actualizarLote() segmentacioncliente");

        try {
            segmentacionClienteService.actualizarLote(segmentacionClienteLoteDto);
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
    @Operation(summary = "Elimina un segmentacioncliente", description = "Elimina un segmentacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SegmentacionCliente eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "SegmentacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() segmentacioncliente: {}", id);

        try {
            segmentacionClienteService.eliminar(id);
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
    @Operation(summary = "Elimina Lista de segmentacioncliente", description = "Elimina una Lista de segmentacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista SegmentacionCliente eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() segmentacioncliente");

        try {
            segmentacionClienteService.eliminarLote(idLote);
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
    @Operation(summary = "Encuentra un segmentacioncliente", description = "Encuentra un segmentacioncliente por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SegmentacionCliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "SegmentacionCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<SegmentacionClienteDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            SegmentacionClienteDto segmentacionClienteDto = segmentacionClienteService.encontrarPorClave(id);
            return ResponseEntity.ok(segmentacionClienteDto); // Retorna  200
        }
        catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los segmentacioncliente", description = "Obtiene todos los segmentacioncliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SegmentacionClientes obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<SegmentacionClienteDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            List<SegmentacionClienteDto> segmentacionClientes = null;
            segmentacionClientes = segmentacionClienteService.obtenerTodos();
            return ResponseEntity.ok(segmentacionClientes);  // Retorna  200
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
    }
}
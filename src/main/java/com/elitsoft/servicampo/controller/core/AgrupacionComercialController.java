package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.AgrupacionComercialDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.AgrupacionComercialService;
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
 * Gestiona las peticiones y respuestas http relativas a AgrupacionComercial
 */
@RestController
@RequestMapping("/agrupaciones-comerciales")
public class AgrupacionComercialController {

    @Autowired
    private AgrupacionComercialService agrupacionComercialService;

    private static final Logger logeador = LoggerFactory.getLogger(AgrupacionComercialController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un agrupacioncomercial", description = "Agrega un nuevo agrupacioncomercial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "AgrupacionComercial agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "AgrupacionComercial ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<AgrupacionComercialDto> agregar(@RequestBody AgrupacionComercialDto agrupacionComercialDto) {
        logeador.debug("agregar() agrupacioncomercial");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(agrupacionComercialService.agregar(agrupacionComercialDto)); // Retorna  201 Created
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
    @Operation(summary = "Agrega lista de agrupacioncomercial", description = "Agrega una lista de nuevos agrupacioncomercial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista AgrupacionComercial agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "AgrupacionComercial ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregarLote(@RequestBody List<AgrupacionComercialDto> agrupacionComercialDtos) {
        logeador.debug("agregarLote() agrupacioncomercial");

        try {
            agrupacionComercialService.agregarLote (agrupacionComercialDtos);
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
    @Operation(summary = "Actualiza un agrupacioncomercial", description = "Actualiza un agrupacioncomercial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "AgrupacionComercial actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "AgrupacionComercial no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody AgrupacionComercialDto agrupacionComercialDto) {
        logeador.debug("actualizar() agrupacioncomercial");

        try {
            agrupacionComercialService.actualizar(id, agrupacionComercialDto);
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
    @Operation(summary = "Actualiza lista de agrupacioncomercial", description = "Actualiza una lista de agrupacioncomercial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote AgrupacionComercial actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarLote(@RequestBody List<AgrupacionComercialDto> agrupacionComercialLoteDto) {
        logeador.debug("actualizarLote() agrupacioncomercial");

        try {
            agrupacionComercialService.actualizarLote(agrupacionComercialLoteDto);
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
    @Operation(summary = "Elimina un agrupacioncomercial", description = "Elimina un agrupacioncomercial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "AgrupacionComercial eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "AgrupacionComercial no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() agrupacioncomercial: {}", id);

        try {
            agrupacionComercialService.eliminar(id);
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
    @Operation(summary = "Elimina Lista de agrupacioncomercial", description = "Elimina una Lista de agrupacioncomercial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista AgrupacionComercial eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() agrupacioncomercial");

        try {
            agrupacionComercialService.eliminarLote(idLote);
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
    @Operation(summary = "Encuentra un agrupacioncomercial", description = "Encuentra un agrupacioncomercial por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AgrupacionComercial encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "AgrupacionComercial no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<AgrupacionComercialDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            AgrupacionComercialDto agrupacionComercialDto = agrupacionComercialService.encontrarPorClave(id);
            return ResponseEntity.ok(agrupacionComercialDto); // Retorna  200
        }
        catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los agrupacioncomercial", description = "Obtiene todos los agrupacioncomercial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AgrupacionComercials obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<AgrupacionComercialDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            List<AgrupacionComercialDto> agrupacionComercialLista = null;
            agrupacionComercialLista = agrupacionComercialService.obtenerTodos();
            return ResponseEntity.ok(agrupacionComercialLista);  // Retorna  200
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
    }
}
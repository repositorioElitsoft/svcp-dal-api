package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.domain.dto.core.TrabajoTareaDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.TrabajoTareaService;
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
 * Gestiona las peticiones y respuestas http relativas a TrabajoTarea
 */
@RestController
@RequestMapping("/trabajo-tarea")
public class TrabajoTareaController {

    @Autowired
    private TrabajoTareaService trabajoTareaService;

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoTareaController.class); //Logback

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un trabajotarea", description = "Agrega un nuevo trabajotarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TrabajoTarea agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TrabajoTarea ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregar(@RequestBody TrabajoTareaDto trabajoTareaDto) {
        logeador.debug("agregar() trabajotarea");

        try {
            trabajoTareaService.agregar(trabajoTareaDto);
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


    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de trabajotarea", description = "Agrega una lista de nuevos trabajotarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista TrabajoTarea agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TrabajoTarea ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregarLote(@RequestBody List<TrabajoTareaDto> trabajoTareaLoteDto) {
        logeador.debug("agregarLote() trabajotarea");

        try {
            trabajoTareaService.agregarLote (trabajoTareaLoteDto);
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

    @PutMapping(value = "/{trabajoId}/tarea/{tareaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un trabajotarea", description = "Actualiza un trabajotarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TrabajoTarea actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TrabajoTarea no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long trabajoId, @PathVariable Long tareaId,  @RequestBody TrabajoTareaDto trabajoTareaDto) {
        logeador.debug("actualizar() trabajotarea");

        try {
            trabajoTareaService.actualizar(trabajoId, tareaId, trabajoTareaDto);
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
    @Operation(summary = "Actualiza lista de trabajotarea", description = "Actualiza una lista de trabajotarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote TrabajoTarea actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarLote(@RequestBody List<TrabajoTareaDto> trabajoTareaLoteDto) {
        logeador.debug("actualizarLote() trabajotarea");

        try {
            trabajoTareaService.actualizarLote(trabajoTareaLoteDto);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{trabajoId}/tarea/{tareaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un trabajotarea", description = "Elimina un trabajotarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TrabajoTarea eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TrabajoTarea no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long trabajoId, @PathVariable Long tareaId ) {
        logeador.debug("eliminar() trabajotarea: {}, {}", trabajoId, tareaId);

        try {
            trabajoTareaService.eliminar(trabajoId, tareaId);
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
    @Operation(summary = "Elimina Lista de trabajotarea", description = "Elimina una Lista de trabajotarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista TrabajoTarea eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() trabajotarea");

        try {
            trabajoTareaService.eliminarLote(idLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{trabajoId}/tarea/{tareaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un trabajotarea", description = "Encuentra un trabajotarea por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TrabajoTarea encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TrabajoTarea no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TrabajoTareaDto> encontrarPorClave(@PathVariable Long trabajoId, @PathVariable Long tareaId ) {
        logeador.debug("encontrarPorClave(): {}, {}", trabajoId, tareaId);

        try {
            TrabajoTareaDto trabajotareaDto = trabajoTareaService.encontrarPorClave(trabajoId, tareaId);
            return ResponseEntity.ok(trabajotareaDto); // Retorna  200
        }
        catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los trabajotarea", description = "Obtiene todos los trabajotarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TrabajoTareas obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<TrabajoTareaDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            List<TrabajoTareaDto> trabajotareas = null;
            trabajotareas = trabajoTareaService.obtenerTodos();
            return ResponseEntity.ok(trabajotareas);  // Retorna  200
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
    }
}
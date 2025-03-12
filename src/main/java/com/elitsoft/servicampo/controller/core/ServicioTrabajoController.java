package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.ServicioTrabajoDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.ServicioTrabajoService;
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
 * Gestiona las peticiones y respuestas http relativas a ServicioTrabajo
 */
@RestController
@RequestMapping("/servicios-trabajos")
public class ServicioTrabajoController {

    @Autowired
    private ServicioTrabajoService serviciotrabajoService;

    private static final Logger logeador = LoggerFactory.getLogger(ServicioTrabajoController.class); //Logback

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un serviciotrabajo", description = "Agrega un nuevo serviciotrabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ServicioTrabajo agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "ServicioTrabajo ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregar(@RequestBody ServicioTrabajoDTO serviciotrabajoDTO) {
        logeador.debug("agregar() serviciotrabajo");

        try {
            serviciotrabajoService.agregar(serviciotrabajoDTO); // Retorna  201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(null)); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        //catch (RecursoNoEncontradoException e) {
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(serviciotrabajoDTO, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        //}
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @Operation(summary = "Agrega un serviciotrabajo", description = "Agrega un nuevo serviciotrabajo")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "ServicioTrabajo agregado exitosamente"),
//            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
//            @ApiResponse(responseCode = "409", description = "ServicioTrabajo ya Existe"),
//            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
//    })
//    public ResponseEntity<ApiEnityResponse<ServicioTrabajoDTO>> agregar(@RequestBody ServicioTrabajoDTO serviciotrabajoDTO) {
//        logeador.debug("agregar() serviciotrabajo");
//
//        try {
//            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(serviciotrabajoService.agregar(serviciotrabajoDTO))); // Retorna  201 Created
//        }
//        catch (EntradaInvalidadException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(serviciotrabajoDTO, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
//        }
//        //catch (RecursoNoEncontradoException e) {
//        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(serviciotrabajoDTO, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
//        //}
//        catch (RecursoDuplicadoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  409 Conflict
//        }
//        catch (BaseDatosException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
//        }
//
//    }

    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de serviciotrabajo", description = "Agrega una lista de nuevos serviciotrabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista ServicioTrabajo agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "ServicioTrabajo ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<ServicioTrabajoDTO> serviciotrabajoDTOLote) {
        logeador.debug("agregarLote() serviciotrabajo");

        try {
            serviciotrabajoService.agregarLote (serviciotrabajoDTOLote);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

    @PutMapping(value = "/{servicioId}/trabajos/{trabajoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un serviciotrabajo", description = "Actualiza un serviciotrabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ServicioTrabajo actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "ServicioTrabajo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long servicioId, @PathVariable Long trabajoId,  @RequestBody ServicioTrabajoDTO serviciotrabajoDTO) {
        logeador.debug("actualizar() serviciotrabajo");

        try {
            serviciotrabajoService.actualizar(servicioId, trabajoId, serviciotrabajoDTO);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  409 Conflict
        } 
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza lista de serviciotrabajo", description = "Actualiza una lista de serviciotrabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote ServicioTrabajo actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<ServicioTrabajoDTO> serviciotrabajoDTOLote) {
        logeador.debug("actualizarLote() serviciotrabajo");

        try {
            serviciotrabajoService.actualizarLote(serviciotrabajoDTOLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
//        catch (RecursoNoEncontradoException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
//        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{servicioId}/trabajos/{trabajoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un serviciotrabajo", description = "Elimina un serviciotrabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ServicioTrabajo eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "ServicioTrabajo no encontrado"),
            @ApiResponse(responseCode = "460", description = "ServicioTrabajo Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long servicioId, @PathVariable Long trabajoId) {
        logeador.debug("eliminar() serviciotrabajo: {}, {}", servicioId, trabajoId);

        try {
            serviciotrabajoService.eliminar(servicioId, trabajoId);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoEliminarException e) {
            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  460 Integridad Violada
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        } 
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina Lista de serviciotrabajo", description = "Elimina una Lista de serviciotrabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista ServicioTrabajo eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "ServicioTrabajo Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<ServicioTrabajoDTO> serviciotrabajoDTOLote) {
        logeador.debug("eliminarLote() serviciotrabajo");

        try {
            serviciotrabajoService.eliminarLote(serviciotrabajoDTOLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoEliminarException e) {
            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  460 Integridad Violada
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{servicioId}/trabajos/{trabajoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un serviciotrabajo", description = "Encuentra un serviciotrabajo por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ServicioTrabajo encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "ServicioTrabajo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<ServicioTrabajoDTO>> encontrarPorClave(@PathVariable Long servicioId, @PathVariable Long trabajoId) {
        logeador.debug("encontrarPorClave(): {}, {}", servicioId, trabajoId);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(serviciotrabajoService.encontrarPorClave(servicioId,trabajoId))); // Retorna  200 OK
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        } 
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los serviciotrabajo", description = "Obtiene todos los serviciotrabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ServicioTrabajos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<ServicioTrabajoDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(serviciotrabajoService.obtenerTodos())); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }
}
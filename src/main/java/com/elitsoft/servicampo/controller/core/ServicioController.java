package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.DireccionDTO;
import com.elitsoft.servicampo.domain.dto.core.ServicioDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.ServicioService;
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
 * Gestiona las peticiones y respuestas http relativas a Servicio
 */
@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    private static final Logger logeador = LoggerFactory.getLogger(ServicioController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un servicio", description = "Agrega un nuevo servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "Servicio ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<ServicioDTO>> agregar(@RequestBody ServicioDTO servicioDTO) {
        logeador.debug("agregar() servicio");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(servicioService.agregar(servicioDTO))); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(servicioDTO, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(servicioDTO, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de servicio", description = "Agrega una lista de nuevos servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista Servicio agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "Servicio ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<ServicioDTO> servicioDTOLote) {
        logeador.debug("agregarLote() servicio");

        try {
            servicioService.agregarLote (servicioDTOLote);
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

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un servicio", description = "Actualiza un servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servicio actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long id, @RequestBody ServicioDTO servicioDTO) {
        logeador.debug("actualizar() servicio");

        try {
            servicioService.actualizar(id, servicioDTO);
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
    @Operation(summary = "Actualiza lista de servicio", description = "Actualiza una lista de servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote Servicio actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<ServicioDTO> servicioDTOLote) {
        logeador.debug("actualizarLote() servicio");

        try {
            servicioService.actualizarLote(servicioDTOLote);
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

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un servicio", description = "Elimina un servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servicio eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado"),
            @ApiResponse(responseCode = "460", description = "Servicio Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() servicio: {}", id);

        try {
            servicioService.eliminar(id);
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
    @Operation(summary = "Elimina Lista de servicio", description = "Elimina una Lista de servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista Servicio eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "Servicio Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<ServicioDTO> servicioDTO) {
        logeador.debug("eliminarLote() servicio");

        try {
            servicioService.eliminarLote(servicioDTO);
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

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un servicio", description = "Encuentra un servicio por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<ServicioDTO>> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(servicioService.encontrarPorClave(id))); // Retorna  200 OK
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        } 
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los servicio", description = "Obtiene todos los servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicios obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<ServicioDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(servicioService.obtenerTodos())); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }
}
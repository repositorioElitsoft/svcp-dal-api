package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.TipoServicioDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.TipoServicioService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoServicio
 */
@RestController
@RequestMapping("/tipos-servicios")
public class TipoServicioController {

    @Autowired
    private TipoServicioService tipoServicioService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoServicioController.class); //Logback

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tiposervicio", description = "Agrega un nuevo tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoServicio agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoServicio ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<TipoServicioDTO>> agregar(@RequestBody TipoServicioDTO tipoServicioDTO) {
        logeador.debug("agregar() tiposervicio");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(tipoServicioService.agregar(tipoServicioDTO))); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de tiposervicio", description = "Agrega una lista de nuevos tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista TipoServicio agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoServicio ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<TipoServicioDTO> tipoServicioDTOLote) {
        logeador.debug("agregarLote() tiposervicio");

        try {
            tipoServicioService.agregarLote (tipoServicioDTOLote);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un tiposervicio", description = "Actualiza un tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoServicio actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoServicio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long id, @RequestBody TipoServicioDTO tipoServicioDTO) {
        logeador.debug("actualizar() tiposervicio");

        try {
            tipoServicioService.actualizar(id, tipoServicioDTO);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza lista de tiposervicio", description = "Actualiza una lista de tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote TipoServicio actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<TipoServicioDTO> tipoServicioDTOLote) {
        logeador.debug("actualizarLote() tiposervicio");

        try {
            tipoServicioService.actualizarLote(tipoServicioDTOLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un tiposervicio", description = "Elimina un tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoServicio eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoServicio no encontrado"),
            @ApiResponse(responseCode = "460", description = "TipoServicio Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tiposervicio: {}", id);

        try {
            tipoServicioService.eliminar(id);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoEliminarException e) {
            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  460 Integridad Violada
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina Lista de tiposervicio", description = "Elimina una Lista de tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista TipoServicio eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "TipoServicio Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() tiposervicio");

        try {
            tipoServicioService.eliminarLote(idLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoEliminarException e) {
            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  460 Integridad Violada
        }

        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un tiposervicio", description = "Encuentra un tiposervicio por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoServicio encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoServicio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<TipoServicioDTO>> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            return ResponseEntity.ok(new ApiEnityResponse<>(tipoServicioService.encontrarPorClave(id))); // Retorna  200
        }
        catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tiposervicio", description = "Obtiene todos los tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoServicios obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<TipoServicioDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.ok(new ApiEnityResponse<>(tipoServicioService.obtenerTodos()));  // Retorna  200
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }
}
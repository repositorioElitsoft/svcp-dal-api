package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.EstadoComponenteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.service.core.EstadoComponenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Gestiona las peticiones y respuestas http relativas a EstadoComponente
 */
@RestController
@RequestMapping("/estados-componentes")
public class EstadoComponenteController {

    @Autowired
    private EstadoComponenteService estadocomponenteService;

    private static final Logger logeador = LoggerFactory.getLogger(EstadoComponenteController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un estadocomponente", description = "Agrega un nuevo estadocomponente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "EstadoComponente agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "EstadoComponente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<EstadoComponenteDTO>> agregar(@RequestBody EstadoComponenteDTO estadocomponenteDTO) {
        logeador.debug("agregar() estadocomponente");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(estadocomponenteService.agregar(estadocomponenteDTO))); // Retorna  201 Created
        } catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(estadocomponenteDTO, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        //catch (RecursoNoEncontradoException e) {
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(estadocomponenteDTO, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        //}
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  409 Conflict
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

    @PostMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de estadocomponente", description = "Agrega una lista de nuevos estadocomponente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista EstadoComponente agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "EstadoComponente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<EstadoComponenteDTO> estadocomponenteDTOLote) {
        logeador.debug("agregarLote() estadocomponente");

        try {
            estadocomponenteService.agregarLote(estadocomponenteDTOLote);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna  201 Created
        } catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        } catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  409 Conflict
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un estadocomponente", description = "Actualiza un estadocomponente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "EstadoComponente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "EstadoComponente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long id, @RequestBody EstadoComponenteDTO estadocomponenteDTO) {
        logeador.debug("actualizar() estadocomponente");

        try {
            estadocomponenteService.actualizar(id, estadocomponenteDTO);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        } catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  404 Not Found
        } catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  409 Conflict
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza lista de estadocomponente", description = "Actualiza una lista de estadocomponente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote EstadoComponente actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<EstadoComponenteDTO> estadocomponenteDTOLote) {
        logeador.debug("actualizarLote() estadocomponente");

        try {
            estadocomponenteService.actualizarLote(estadocomponenteDTOLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        } catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        }
        //catch (RecursoNoEncontradoException e) {
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        //}
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un estadocomponente", description = "Elimina un estadocomponente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "EstadoComponente eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "EstadoComponente no encontrado"),
            @ApiResponse(responseCode = "460", description = "EstadoComponente Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() estadocomponente: {}", id);

        try {
            estadocomponenteService.eliminar(id);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        } catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        } catch (RecursoEliminarException e) {
            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  460 Integridad Violada
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina Lista de estadocomponente", description = "Elimina una Lista de estadocomponente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista EstadoComponente eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "EstadoComponente Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<EstadoComponenteDTO> estadocomponenteDTOLote) {
        logeador.debug("eliminarLote() estadocomponente");

        try {
            estadocomponenteService.eliminarLote(estadocomponenteDTOLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        } catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  400 Bad Request
        } catch (RecursoEliminarException e) {
            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  460 Integridad Violada
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un estadocomponente", description = "Encuentra un estadocomponente por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EstadoComponente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "EstadoComponente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<EstadoComponenteDTO>> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(estadocomponenteService.encontrarPorClave(id))); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los estadocomponente", description = "Obtiene todos los estadocomponente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EstadoComponentes obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<EstadoComponenteDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(estadocomponenteService.obtenerTodos())); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  500 Internal Server Error
        }
    }
}
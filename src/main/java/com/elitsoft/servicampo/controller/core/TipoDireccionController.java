package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.TipoDireccionDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.TipoDireccionService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoDireccion
 */
@RestController
@RequestMapping("/tipos-direcciones")
public class TipoDireccionController {

    @Autowired
    private TipoDireccionService tipodireccionService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoDireccionController.class); //Logback

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tipodireccion", description = "Agrega un nuevo tipodireccion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoDireccion agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoDireccion ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<TipoDireccionDTO>> agregar(@RequestBody TipoDireccionDTO tipodireccionDTO) {
        logeador.debug("agregar() tipodireccion");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(tipodireccionService.agregar(tipodireccionDTO))); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(tipodireccionDTO, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
//        catch (RecursoNoEncontradoException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(tipodireccionDTO, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
//        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de tipodireccion", description = "Agrega una lista de nuevos tipodireccion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista TipoDireccion agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoDireccion ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<TipoDireccionDTO> tipodireccionDTOLote) {
        logeador.debug("agregarLote() tipodireccion");

        try {
            tipodireccionService.agregarLote (tipodireccionDTOLote);
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
    @Operation(summary = "Actualiza un tipodireccion", description = "Actualiza un tipodireccion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoDireccion actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoDireccion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long id, @RequestBody TipoDireccionDTO tipodireccionDTO) {
        logeador.debug("actualizar() tipodireccion");

        try {
            tipodireccionService.actualizar(id, tipodireccionDTO);
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
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza lista de tipodireccion", description = "Actualiza una lista de tipodireccion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote TipoDireccion actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<TipoDireccionDTO> tipodireccionDTOLote) {
        logeador.debug("actualizarLote() tipodireccion");

        try {
            tipodireccionService.actualizarLote(tipodireccionDTOLote);
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
    @Operation(summary = "Elimina un tipodireccion", description = "Elimina un tipodireccion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoDireccion eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoDireccion no encontrado"),
            @ApiResponse(responseCode = "460", description = "TipoDireccion Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tipodireccion: {}", id);

        try {
            tipodireccionService.eliminar(id);
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
    @Operation(summary = "Elimina Lista de tipodireccion", description = "Elimina una Lista de tipodireccion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista TipoDireccion eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "TipoDireccion Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() tipodireccion");

        try {
            tipodireccionService.eliminarLote(idLote);
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
    @Operation(summary = "Encuentra un tipodireccion", description = "Encuentra un tipodireccion por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoDireccion encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoDireccion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<TipoDireccionDTO>> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(tipodireccionService.encontrarPorClave(id))); // Retorna  200 OK
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        } 
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tipodireccion", description = "Obtiene todos los tipodireccion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoDireccions obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<TipoDireccionDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(tipodireccionService.obtenerTodos())); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }
}
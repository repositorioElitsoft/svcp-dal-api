package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.TipoClienteDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.TipoClienteMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoCliente para la version mobile
 */
@RestController
@RequestMapping("/mobile/tipos-clientes")
public class TipoClienteMobileController {

    @Autowired
    private TipoClienteMobileService tipoClienteMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoClienteMobileController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tipocliente", description = "Agrega un nuevo tipocliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoCliente agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoCliente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<TipoClienteDTO>> agregar(@RequestBody TipoClienteDTO tipoClienteDTO) {
        logeador.debug("agregar() tipocliente");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(tipoClienteMobileService.agregar(tipoClienteDTO))); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(tipoClienteDTO, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de tipocliente", description = "Agrega una lista de nuevos tipocliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista TipoCliente agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoCliente ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<TipoClienteDTO> tipoClienteDTOLote) {
        logeador.debug("agregarLote() tipocliente");


        try {
            tipoClienteMobileService.agregarLote (tipoClienteDTOLote);
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
    @Operation(summary = "Actualiza un tipocliente", description = "Actualiza un tipocliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoCliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long id, @RequestBody TipoClienteDTO tipoClienteDTO) {
        logeador.debug("actualizar() tipocliente");

        try {
            tipoClienteMobileService.actualizar(id, tipoClienteDTO);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza lista de tipocliente", description = "Actualiza una lista de tipocliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote TipoCliente actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<TipoClienteDTO> tipoClienteDTOLote) {
        logeador.debug("actualizarLote() tipocliente");

        try {
            tipoClienteMobileService.actualizarLote(tipoClienteDTOLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un tipocliente", description = "Elimina un tipocliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoCliente eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoCliente no encontrado"),
            @ApiResponse(responseCode = "460", description = "TipoCliente Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tipocliente: {}", id);

        try {
            tipoClienteMobileService.eliminar(id);
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
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina Lista de tipocliente", description = "Elimina una Lista de tipocliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista TipoCliente eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "TipoCliente Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() tipocliente");

        try {
            tipoClienteMobileService.eliminarLote(idLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoEliminarException e) {
            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  460 Integridad Violada
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un tipocliente", description = "Encuentra un tipocliente por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoCliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoCliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<TipoClienteDTO>> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(tipoClienteMobileService.encontrarPorClave(id))); // Retorna  200 OK
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tipocliente", description = "Obtiene todos los tipocliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoClientes obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<TipoClienteDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(tipoClienteMobileService.obtenerTodos())); // Retorna  200 OK

        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
    }
}
package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleTipoProductoDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.core.ContratoDetalleTipoProductoService;
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
 * Gestiona las peticiones y respuestas http relativas a ContratoDetalleTipoProducto
 */
@RestController
@RequestMapping("/contratos-detalles-tipos-productos")
public class ContratoDetalleTipoProductoController {

    @Autowired
    private ContratoDetalleTipoProductoService contratodetalletipoproductoService;

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleTipoProductoController.class); //Logback

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un contratodetalletipoproducto", description = "Agrega un nuevo contratodetalletipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ContratoDetalleTipoProducto agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "ContratoDetalleTipoProducto ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregar(@RequestBody ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO) {
        logeador.debug("agregar() contratodetalletipoproducto");

        try {
            contratodetalletipoproductoService.agregar(contratodetalletipoproductoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(null)); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }

    }


    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de contratodetalletipoproducto", description = "Agrega una lista de nuevos contratodetalletipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista ContratoDetalleTipoProducto agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "ContratoDetalleTipoProducto ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) {
        logeador.debug("agregarLote() contratodetalletipoproducto");

        try {
            contratodetalletipoproductoService.agregarLote (contratodetalletipoproductoDTOLote);
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

    @PutMapping(value = "/{contratoDetalleId}/tipos-productos/{tipoProductoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un contratodetalletipoproducto", description = "Actualiza un contratodetalletipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ContratoDetalleTipoProducto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "ContratoDetalleTipoProducto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long contratoDetalleId, @PathVariable Long tipoProductoId,
                                                               @RequestBody ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO) {
        logeador.debug("actualizar() contratodetalletipoproducto");

        try {
            contratodetalletipoproductoService.actualizar(contratoDetalleId, tipoProductoId, contratodetalletipoproductoDTO);
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
    @Operation(summary = "Actualiza lista de contratodetalletipoproducto", description = "Actualiza una lista de contratodetalletipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote ContratoDetalleTipoProducto actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) {
        logeador.debug("actualizarLote() contratodetalletipoproducto");

        try {
            contratodetalletipoproductoService.actualizarLote(contratodetalletipoproductoDTOLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        //catch (RecursoNoEncontradoException e) {
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        //}
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage()));  // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{contratoDetalleId}/tipos-productos/{tipoProductoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un contratodetalletipoproducto", description = "Elimina un contratodetalletipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ContratoDetalleTipoProducto eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "ContratoDetalleTipoProducto no encontrado"),
            @ApiResponse(responseCode = "460", description = "ContratoDetalleTipoProducto Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long contratoDetalleId, @PathVariable Long tipoProductoId) {
        logeador.debug("eliminar() contratodetalletipoproducto: {}, {}", contratoDetalleId, tipoProductoId);

        try {
            contratodetalletipoproductoService.eliminar(contratoDetalleId, tipoProductoId);
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
    @Operation(summary = "Elimina Lista de contratodetalletipoproducto", description = "Elimina una Lista de contratodetalletipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista ContratoDetalleTipoProducto eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "ContratoDetalleTipoProducto Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) {
        logeador.debug("eliminarLote() contratodetalletipoproducto");

        try {
            contratodetalletipoproductoService.eliminarLote(contratodetalletipoproductoDTOLote);
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

    @GetMapping(value = "/{contratoDetalleId}/tipos-productos/{tipoProductoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un contratodetalletipoproducto", description = "Encuentra un contratodetalletipoproducto por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ContratoDetalleTipoProducto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "ContratoDetalleTipoProducto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<ContratoDetalleTipoProductoDTO>> encontrarPorClave(@PathVariable Long contratoDetalleId, @PathVariable Long tipoProductoId) {
        logeador.debug("encontrarPorClave(): {}, {}", contratoDetalleId, tipoProductoId);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(contratodetalletipoproductoService.encontrarPorClave(contratoDetalleId, tipoProductoId))); // Retorna  200 OK
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        } 
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los contratodetalletipoproducto", description = "Obtiene todos los contratodetalletipoproducto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ContratoDetalleTipoProductos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<ContratoDetalleTipoProductoDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(contratodetalletipoproductoService.obtenerTodos())); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }
}
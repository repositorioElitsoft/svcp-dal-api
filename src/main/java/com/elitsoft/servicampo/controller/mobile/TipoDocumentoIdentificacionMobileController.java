package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoDocumentoIdentificacionDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.TipoDocumentoIdentificacionMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoDocumentoIdentificacion para la version mobile
 */
@RestController
@RequestMapping("/mobile/tipos-documentos-identificaciones")
public class TipoDocumentoIdentificacionMobileController {

    @Autowired
    private TipoDocumentoIdentificacionMobileService tipodocumentoidentificacionMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoDocumentoIdentificacionMobileController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tipodocumentoidentificacion", description = "Agrega un nuevo tipodocumentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoDocumentoIdentificacion agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoDocumentoIdentificacion ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TipoDocumentoIdentificacionDTO> agregar(@RequestBody TipoDocumentoIdentificacionDTO tipodocumentoidentificacionDTO) {
        logeador.debug("agregar() tipodocumentoidentificacion");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipodocumentoidentificacionMobileService.agregar(tipodocumentoidentificacionDTO)); // Retorna  201 Created
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
    @Operation(summary = "Agrega lista de tipodocumentoidentificacion", description = "Agrega una lista de nuevos tipodocumentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista TipoDocumentoIdentificacion agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoDocumentoIdentificacion ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregarLote(@RequestBody List<TipoDocumentoIdentificacionDTO> tipodocumentoidentificacionLoteDTO) {
        logeador.debug("agregarLote() tipodocumentoidentificacion");

        try {
            tipodocumentoidentificacionMobileService.agregarLote(tipodocumentoidentificacionLoteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna  500 Internal Server Error
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un tipodocumentoidentificacion", description = "Actualiza un tipodocumentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoDocumentoIdentificacion actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoDocumentoIdentificacion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody TipoDocumentoIdentificacionDTO tipodocumentoidentificacionDTO) {
        logeador.debug("actualizar() tipodocumentoidentificacion");

        try {
            tipodocumentoidentificacionMobileService.actualizar(id, tipodocumentoidentificacionDTO);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Retorna  404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna  500 Internal Server Error
        }
    }

    @PutMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza lista de tipodocumentoidentificacion", description = "Actualiza una lista de tipodocumentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote TipoDocumentoIdentificacion actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarLote(@RequestBody List<TipoDocumentoIdentificacionDTO> tipodocumentoidentificacionLoteDTO) {
        logeador.debug("actualizarLote() tipodocumentoidentificacion");

        try {
            tipodocumentoidentificacionMobileService.actualizarLote(tipodocumentoidentificacionLoteDTO);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un tipodocumentoidentificacion", description = "Elimina un tipodocumentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoDocumentoIdentificacion eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoDocumentoIdentificacion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tipodocumentoidentificacion: {}", id);

        try {
            tipodocumentoidentificacionMobileService.eliminar(id);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Retorna  404 Not Found
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina Lista de tipodocumentoidentificacion", description = "Elimina una Lista de tipodocumentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista TipoDocumentoIdentificacion eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() tipodocumentoidentificacion");

        try {
            tipodocumentoidentificacionMobileService.eliminarLote(idLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna  400 Bad Request
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un tipodocumentoidentificacion", description = "Encuentra un tipodocumentoidentificacion por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoDocumentoIdentificacion encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoDocumentoIdentificacion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TipoDocumentoIdentificacionDTO> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            TipoDocumentoIdentificacionDTO tipodocumentoidentificacionDTO = tipodocumentoidentificacionMobileService.encontrarPorClave(id);
            return ResponseEntity.ok(tipodocumentoidentificacionDTO);  // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tipodocumentoidentificacion", description = "Obtiene todos los tipodocumentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoDocumentoIdentificacions obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<TipoDocumentoIdentificacionDTO>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<TipoDocumentoIdentificacionDTO> tipodocumentoidentificacionLista = null;

        try {
            tipodocumentoidentificacionLista = tipodocumentoidentificacionMobileService.obtenerTodos();
             return ResponseEntity.ok(tipodocumentoidentificacionLista); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
       
    }
}
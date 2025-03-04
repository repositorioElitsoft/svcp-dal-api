package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.DocumentoIdentificacionDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.DocumentoIdentificacionMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a DocumentoIdentificacion para la version mobile
 */
@RestController
@RequestMapping("/mobile/documento-identificacion")
public class DocumentoIdentificacionMobileController {

    @Autowired
    private DocumentoIdentificacionMobileService documentoidentificacionMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(DocumentoIdentificacionMobileController.class); //Logback

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un documentoidentificacion", description = "Agrega un nuevo documentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "DocumentoIdentificacion agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "DocumentoIdentificacion ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<DocumentoIdentificacionDTO> agregar(@RequestBody DocumentoIdentificacionDTO documentoidentificacionDTO) {
        logeador.debug("agregar() documentoidentificacion");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(documentoidentificacionMobileService.agregar(documentoidentificacionDTO)); // Retorna  201 Created
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
    @Operation(summary = "Agrega lista de documentoidentificacion", description = "Agrega una lista de nuevos documentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista DocumentoIdentificacion agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "DocumentoIdentificacion ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregarLote(@RequestBody List<DocumentoIdentificacionDTO> documentoidentificacionLoteDTO) {
        logeador.debug("agregarLote() documentoidentificacion");

        try {
            documentoidentificacionMobileService.agregarLote(documentoidentificacionLoteDTO);
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
    @Operation(summary = "Actualiza un documentoidentificacion", description = "Actualiza un documentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "DocumentoIdentificacion actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "DocumentoIdentificacion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody DocumentoIdentificacionDTO documentoidentificacionDTO) {
        logeador.debug("actualizar() documentoidentificacion");

        try {
            documentoidentificacionMobileService.actualizar(id, documentoidentificacionDTO);
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
    @Operation(summary = "Actualiza lista de documentoidentificacion", description = "Actualiza una lista de documentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote DocumentoIdentificacion actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarLote(@RequestBody List<DocumentoIdentificacionDTO> documentoidentificacionLoteDTO) {
        logeador.debug("actualizarLote() documentoidentificacion");

        try {
            documentoidentificacionMobileService.actualizarLote(documentoidentificacionLoteDTO);
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
    @Operation(summary = "Elimina un documentoidentificacion", description = "Elimina un documentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "DocumentoIdentificacion eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "DocumentoIdentificacion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() documentoidentificacion: {}", id);

        try {
            documentoidentificacionMobileService.eliminar(id);
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
    @Operation(summary = "Elimina Lista de documentoidentificacion", description = "Elimina una Lista de documentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista DocumentoIdentificacion eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() documentoidentificacion");

        try {
            documentoidentificacionMobileService.eliminarLote(idLote);
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
    @Operation(summary = "Encuentra un documentoidentificacion", description = "Encuentra un documentoidentificacion por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DocumentoIdentificacion encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "DocumentoIdentificacion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<DocumentoIdentificacionDTO> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            DocumentoIdentificacionDTO documentoidentificacionDTO = documentoidentificacionMobileService.encontrarPorClave(id);
            return ResponseEntity.ok(documentoidentificacionDTO);  // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los documentoidentificacion", description = "Obtiene todos los documentoidentificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DocumentoIdentificacions obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<DocumentoIdentificacionDTO>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<DocumentoIdentificacionDTO> documentoidentificacionLista = null;

        try {
            documentoidentificacionLista = documentoidentificacionMobileService.obtenerTodos();
             return ResponseEntity.ok(documentoidentificacionLista); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
       
    }
}
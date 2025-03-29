package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.ContactoMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a Contacto para la version mobile
 */
@RestController
@RequestMapping("/mobile/contactos")
public class ContactoMobileController {

    @Autowired
    private ContactoMobileService contactoMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(ContactoMobileController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un contacto", description = "Agrega un nuevo contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contacto agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "Contacto ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<ContactoDTO>> agregar(@RequestBody ContactoDTO contactoDTO) {
        logeador.debug("agregar() contacto");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(contactoMobileService.agregar(contactoDTO))); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(contactoDTO, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(contactoDTO, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }

    }

    @PostMapping(value = "/lote",  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega lista de contacto", description = "Agrega una lista de nuevos contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista Contacto agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "Contacto ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> agregarLote(@RequestBody List<ContactoDTO> contactoLoteDTO) {
        logeador.debug("agregarLote() contacto");

        try {
            contactoMobileService.agregarLote(contactoLoteDTO); 
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
    @Operation(summary = "Actualiza un contacto", description = "Actualiza un contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contacto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "Contacto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizar(@PathVariable Long id, @RequestBody ContactoDTO contactoDTO) {
        logeador.debug("actualizar() contacto");

        try {
            contactoMobileService.actualizar(id, contactoDTO);
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
    @Operation(summary = "Actualiza lista de contacto", description = "Actualiza una lista de contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote Contacto actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> actualizarLote(@RequestBody List<ContactoDTO> contactoLoteDTO) {
        logeador.debug("actualizarLote() contacto");

        try {
            contactoMobileService.actualizarLote(contactoLoteDTO);
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
    @Operation(summary = "Elimina un contacto", description = "Elimina un contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contacto eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "Contacto no encontrado"),
            @ApiResponse(responseCode = "460", description = "Contacto Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() contacto: {}", id);

        try {
            contactoMobileService.eliminar(id);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
//        catch (RecursoEliminarException e) {
//            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  460 Integridad Violada
//        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        } 
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @DeleteMapping(value = "/lote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina Lista de contacto", description = "Elimina una Lista de contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista Contacto eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "460", description = "Contacto Viola integridad referencial"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<String>> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() contacto");

        try {
            contactoMobileService.eliminarLote(idLote);
            return ResponseEntity.noContent().build(); // Retorna  204 No Content
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  400 Bad Request
        }
//        catch (RecursoEliminarException e) {
//            return ResponseEntity.status(460).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  460 Integridad Violada
//        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un contacto", description = "Encuentra un contacto por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contacto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Contacto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<ContactoDTO>> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(contactoMobileService.encontrarPorClave(id))); // Retorna  200 OK
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        } 
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los contacto", description = "Obtiene todos los contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contactos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<ApiEnityResponse<List<ContactoDTO>>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEnityResponse<>(contactoMobileService.obtenerTodos())); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiEnityResponse<>(null, e.getErrorCode(),  e.getMessage())); // Retorna  500 Internal Server Error
        }
       
    }
}
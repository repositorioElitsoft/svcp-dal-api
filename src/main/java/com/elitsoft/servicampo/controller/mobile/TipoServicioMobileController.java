package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoServicioDto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.TipoServicioMobileService;
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
 * Gestiona las peticiones y respuestas http relativas a TipoServicio para la version mobile
 */
@RestController
@RequestMapping("/mobile/tipo-servicio")
public class TipoServicioMobileController {

    @Autowired
    private TipoServicioMobileService tipoServicioMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(TipoServicioMobileController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un tiposervicio", description = "Agrega un nuevo tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TipoServicio agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoServicio ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TipoServicioDto> agregar(@RequestBody TipoServicioDto tipoServicioDto) {
        logeador.debug("agregar() tiposervicio");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoServicioMobileService.agregar(tipoServicioDto)); // Retorna  201 Created
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
    @Operation(summary = "Agrega lista de tiposervicio", description = "Agrega una lista de nuevos tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista TipoServicio agregados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "TipoServicio ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> agregarLote(@RequestBody List<TipoServicioDto> tipoServicioLoteDto) {
        logeador.debug("agregarLote() tiposervicio");

        try {
            tipoServicioMobileService.agregarLote(tipoServicioLoteDto);
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
    @Operation(summary = "Actualiza un tiposervicio", description = "Actualiza un tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoServicio actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoServicio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody TipoServicioDto tipoServicioDto) {
        logeador.debug("actualizar() tiposervicio");

        try {
            tipoServicioMobileService.actualizar(id, tipoServicioDto);
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
    @Operation(summary = "Actualiza lista de tiposervicio", description = "Actualiza una lista de tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote TipoServicio actualizados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarLote(@RequestBody List<TipoServicioDto> tipoServicioLoteDto) {
        logeador.debug("actualizarLote() tiposervicio");

        try {
            tipoServicioMobileService.actualizarLote(tipoServicioLoteDto);
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
    @Operation(summary = "Elimina un tiposervicio", description = "Elimina un tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "TipoServicio eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "TipoServicio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() tiposervicio: {}", id);

        try {
            tipoServicioMobileService.eliminar(id);
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
    @Operation(summary = "Elimina Lista de tiposervicio", description = "Elimina una Lista de tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista TipoServicio eliminados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminarLote(@RequestBody List<Long> idLote) {
        logeador.debug("eliminarLote() tiposervicio");

        try {
            tipoServicioMobileService.eliminarLote(idLote);
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
    @Operation(summary = "Encuentra un tiposervicio", description = "Encuentra un tiposervicio por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoServicio encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "TipoServicio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<TipoServicioDto> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            TipoServicioDto tiposervicioDto = tipoServicioMobileService.encontrarPorClave(id);
            return ResponseEntity.ok(tiposervicioDto);  // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los tiposervicio", description = "Obtiene todos los tiposervicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoServicios obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<TipoServicioDto>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<TipoServicioDto> tiposervicios = null;

        try {
            tiposervicios = tipoServicioMobileService.obtenerTodos();
             return ResponseEntity.ok(tiposervicios); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
       
    }
}
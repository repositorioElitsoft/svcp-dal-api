package com.elitsoft.servicampo.controller.mobile;

import com.elitsoft.servicampo.domain.dto.core.EmpleadoDTO;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.service.mobile.EmpleadoMobileService;
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
import java.util.Map;

/**
 * Gestiona las peticiones y respuestas http relativas a Empleado para la version mobile
 */
@RestController
@RequestMapping("/mobile/empleados")
public class EmpleadoMobileController {

    @Autowired
    private EmpleadoMobileService empleadoMobileService;

    private static final Logger logeador = LoggerFactory.getLogger(EmpleadoMobileController.class); //Logback


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agrega un empleado", description = "Agrega un nuevo empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "409", description = "Empleado ya Existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<EmpleadoDTO> agregar(@RequestBody EmpleadoDTO empleadoDTO) {
        logeador.debug("agregar() empleado");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoMobileService.agregar(empleadoDTO)); // Retorna  201 Created
        }
        catch (EntradaInvalidadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna  400 Bad Request
        }
        catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna  404 Not Found
        }
        catch (RecursoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Retorna  409 Conflict
        }
        catch (BaseDatosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna  500 Internal Server Error
        }

    }


    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza un empleado", description = "Actualiza un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empleado actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
        logeador.debug("actualizar() empleado");

        try {
            empleadoMobileService.actualizar(id, empleadoDTO);
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

    @PutMapping(value = "/{id}/clave", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualiza clave de un empleado", description = "Actualiza clave de un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empleado actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> actualizarClave(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        logeador.debug("actualizarClave() empleado");

        try {
            String contrasena = requestBody.get("contrasena");
            empleadoMobileService.actualizarClave(id, contrasena);
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

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Elimina un empleado", description = "Elimina un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Mala Peticion - Entrada datos Invalida"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        logeador.debug("eliminar() empleado: {}", id);

        try {
            empleadoMobileService.eliminar(id);
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


    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encuentra un empleado", description = "Encuentra un empleado por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<EmpleadoDTO> encontrarPorClave(@PathVariable Long id) {
        logeador.debug("encontrarPorClave(): {}", id);

        try {
            EmpleadoDTO empleadoDTO = empleadoMobileService.encontrarPorClave(id);
            return ResponseEntity.ok(empleadoDTO);  // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna  404 Not Found
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtiene todos  los empleado", description = "Obtiene todos los empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleados obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodos() {
        logeador.debug("obtenerTodos()");

        List<EmpleadoDTO> empleadoLista = null;

        try {
            empleadoLista = empleadoMobileService.obtenerTodos();
             return ResponseEntity.ok(empleadoLista); // Retorna  200 OK
        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }
       
    }
}
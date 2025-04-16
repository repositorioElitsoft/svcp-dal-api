package com.elitsoft.servicampo.controller.core.filter.entity;

import com.elitsoft.servicampo.common.api.response.PagedResponse;
import com.elitsoft.servicampo.domain.dto.core.ServicioDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.ServicioFiltro;
import com.elitsoft.servicampo.service.core.filter.entity.ServicioFiltroService;
import com.elitsoft.servicampo.utils.PaginationUtils;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core/filter")
public class ServicioFiltroController {

    @Autowired
    private ServicioFiltroService servicioFiltroService; //Logica de Negocio de Filtrado del Core Service

    private static final Logger logeador = LoggerFactory.getLogger(ServicioFiltroController.class); //Logback

    @GetMapping(value = "/servicios", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtra un servicio", description = "Filtra y hace paginado de servicio por atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<ServicioDTO>> filtrar(@ModelAttribute ServicioFiltro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrar()");

        try {
            List<ServicioDTO> servicioDTOLista = servicioFiltroService.filtrar(filtro, paginado);
            int totalFiltro = servicioFiltroService.contarFiltrar(filtro);

            PagedResponse<ServicioDTO> response = PaginationUtils.createPagedResponse(servicioDTOLista, totalFiltro, paginado);
            return ResponseEntity.ok(response); // Retorna  200 OK

        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }

    }

    @GetMapping(value = "/servicios-trabajos-asignados", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtra un servicio", description = "Filtra y hace paginado de servicio por atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<ServicioDTO>> filtrarAsignacion(@ModelAttribute ServicioFiltro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrarAsignacion()");

        try {
            List<ServicioDTO> servicioDTOLista = servicioFiltroService.filtrarAsignacion(filtro, paginado);
            int totalFiltro = servicioFiltroService.contarFiltrarAsignacion(filtro);

            PagedResponse<ServicioDTO> response = PaginationUtils.createPagedResponse(servicioDTOLista, totalFiltro, paginado);
            return ResponseEntity.ok(response); // Retorna  200 OK

        } catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }

    }
}
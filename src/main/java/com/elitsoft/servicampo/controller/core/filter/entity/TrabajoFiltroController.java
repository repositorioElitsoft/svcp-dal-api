package com.elitsoft.servicampo.controller.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.TrabajoFiltro;
import com.elitsoft.servicampo.service.core.filter.entity.TrabajoFiltroService;
import com.elitsoft.servicampo.utils.PagedResponse;
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
public class TrabajoFiltroController {

    @Autowired
    private TrabajoFiltroService trabajoFiltroService; //Logica de Negocio de Filtrado del Core Service

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoFiltroController.class); //Logback

    @GetMapping(value = "/trabajo", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtra un trabajo", description = "Filtra y hace paginado de trabajo por atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajo Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<TrabajoDto>> filtrar(@ModelAttribute TrabajoFiltro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrar()");

        try {
            List<TrabajoDto> trabajoLista = trabajoFiltroService.filtrar(filtro, paginado);
            int totalFiltro = trabajoFiltroService.contarFiltrar(filtro);

            PagedResponse<TrabajoDto> response = PaginationUtils.createPagedResponse(trabajoLista, totalFiltro, paginado);
            return ResponseEntity.ok(response); // Retorna  200 OK

        }  catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }

    }
}
package com.elitsoft.servicampo.controller.core.filter.entity;

import com.elitsoft.servicampo.controller.core.TareaController;
import com.elitsoft.servicampo.filtro.TareaFiltro;
import com.elitsoft.servicampo.domain.entity.Tarea;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.service.core.filter.entity.TareaFiltroService;
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
public class TareaFiltroController {

    @Autowired
    private TareaFiltroService tareaFiltroService;

    private static final Logger logeador = LoggerFactory.getLogger(TareaController.class);

    @GetMapping(value = "/tareas", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtrar un tarea", description = "Filtrar un tarea por su clave")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<Tarea>> filtrarTareas(@ModelAttribute TareaFiltro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrarTareas()");

        try {
            List<Tarea> tareas = tareaFiltroService.filtrarTareas(filtro, paginado);
            int totalTareas = tareaFiltroService.contarFiltroTareas(filtro);

            PagedResponse<Tarea> response = PaginationUtils.createPagedResponse(tareas, totalTareas, paginado);
            return ResponseEntity.ok(response);

        }  catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }
}

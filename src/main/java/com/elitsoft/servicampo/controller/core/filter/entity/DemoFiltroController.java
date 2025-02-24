package com.elitsoft.servicampo.controller.core.filter.entity;

import com.elitsoft.servicampo.domain.entity.Demo;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.DemoFiltro;
import com.elitsoft.servicampo.service.core.filter.entity.DemoFiltroService;
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
public class DemoFiltroController {

    @Autowired
    private DemoFiltroService demoFiltroService; //Logica de Negocio de Filtrado del Core Service

    private static final Logger logeador = LoggerFactory.getLogger(DemoFiltroController.class); //Logback

    @GetMapping(value = "/demo", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtrar un demo", description = "Filtrar y hace paginado un demo por atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demo Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<Demo>> filtrar(@ModelAttribute DemoFiltro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrar()");

        try {
            List<Demo> demoLista = demoFiltroService.filtrar(filtro, paginado);
            int totalFiltro = demoFiltroService.contarFiltrar(filtro);

            PagedResponse<Demo> response = PaginationUtils.createPagedResponse(demoLista, totalFiltro, paginado);
            return ResponseEntity.ok(response);

        }  catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build();
        }

    }
}

package com.elitsoft.#app_name#.controller.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.#Base#DTO;
import com.elitsoft.#app_name#.exceptions.BaseDatosException;
import com.elitsoft.#app_name#.filter.#Base#Filtro;
import com.elitsoft.#app_name#.service.core.filter.entity.#Base#FiltroService;
import com.elitsoft.servicampo.common.api.response.PagedResponse;
import com.elitsoft.#app_name#.utils.PaginationUtils;
import com.elitsoft.#app_name#.utils.PagingAndSorting;
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
public class #Base#FiltroController {

    @Autowired
    private #Base#FiltroService #base#FiltroService; //Logica de Negocio de Filtrado del Core Service

    private static final Logger logeador = LoggerFactory.getLogger(#Base#FiltroController.class); //Logback

    @GetMapping(value = "/#base#", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtra un #base#", description = "Filtra y hace paginado de #base# por atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#Base# Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<#Base#DTO>> filtrar(@ModelAttribute #Base#Filtro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrar()");

        try {
            List<#Base#DTO> #base#DTOLista = #base#FiltroService.filtrar(filtro, paginado);
            int totalFiltro = #base#FiltroService.contarFiltrar(filtro);

            PagedResponse<#Base#DTO> response = PaginationUtils.createPagedResponse(#base#DTOLista, totalFiltro, paginado);
            return ResponseEntity.ok(response); // Retorna  200 OK

        }  catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }

    }
}
package com.elitsoft.servicampo.controller.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.TipoEmpleadoFiltro;
import com.elitsoft.servicampo.service.core.filter.entity.TipoEmpleadoFiltroService;
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
public class TipoEmpleadoFiltroController {

    @Autowired
    private TipoEmpleadoFiltroService tipoempleadoFiltroService; //Logica de Negocio de Filtrado del Core Service

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoFiltroController.class); //Logback

    @GetMapping(value = "/tipo-empleado", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtra un tipoempleado", description = "Filtra y hace paginado de tipoempleado por atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TipoEmpleado Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<TipoEmpleadoDto>> filtrar(@ModelAttribute TipoEmpleadoFiltro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrar()");

        try {
            List<TipoEmpleadoDto> tipoEmpleadoLista = tipoempleadoFiltroService.filtrar(filtro, paginado);
            int totalFiltro = tipoempleadoFiltroService.contarFiltrar(filtro);

            PagedResponse<TipoEmpleadoDto> response = PaginationUtils.createPagedResponse(tipoEmpleadoLista, totalFiltro, paginado);
            return ResponseEntity.ok(response); // Retorna  200 OK

        }  catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }

    }
}
package com.elitsoft.servicampo.controller.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.ClienteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.ClienteFiltro;
import com.elitsoft.servicampo.service.core.filter.entity.ClienteFiltroService;
import com.elitsoft.servicampo.common.api.response.PagedResponse;
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
public class ClienteFiltroController {

    @Autowired
    private ClienteFiltroService clienteFiltroService; //Logica de Negocio de Filtrado del Core Service

    private static final Logger logeador = LoggerFactory.getLogger(ClienteFiltroController.class); //Logback

    @GetMapping(value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Filtra un cliente", description = "Filtra y hace paginado de cliente por atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente Filtrado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor ")
    })
    public ResponseEntity<PagedResponse<ClienteDTO>> filtrar(@ModelAttribute ClienteFiltro filtro, PagingAndSorting paginado) {
        logeador.debug("filtrar()");

        try {
            List<ClienteDTO> clienteDTOLista = clienteFiltroService.filtrar(filtro, paginado);
            int totalFiltro = clienteFiltroService.contarFiltrar(filtro);

            PagedResponse<ClienteDTO> response = PaginationUtils.createPagedResponse(clienteDTOLista, totalFiltro, paginado);
            return ResponseEntity.ok(response); // Retorna  200 OK

        }  catch (BaseDatosException e) {
            return ResponseEntity.internalServerError().build(); // Retorna  500 Internal Server Error
        }

    }
}
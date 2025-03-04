package com.elitsoft.#app_name#.service.core.filter.entity;

import com.elitsoft.#app_name#.domain.dto.core.#Base#DTO;
import com.elitsoft.#app_name#.exceptions.BaseDatosException;
import com.elitsoft.#app_name#.filter.#Base#Filtro;
import com.elitsoft.#app_name#.mapper.#Base#Mapper;
import com.elitsoft.servicampo.mapstruct.#Base#MapStruct;
import com.elitsoft.#app_name#.utils.Constantes;
import com.elitsoft.#app_name#.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class #Base#FiltroService {

    @Autowired
    private #Base#Mapper #base#Mapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private #Base#MapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(#Base#FiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para #Base#
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<#Base#DTO> lista de entidades #Base#
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<#Base#DTO> filtrar(#Base#Filtro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDTOList(#base#Mapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.#BASE#_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.#BASE#_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para #Base#
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(#Base#Filtro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return #base#Mapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.#BASE#_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.#BASE#_FILTRAR_MENSAJE, e);
        }
    }
}
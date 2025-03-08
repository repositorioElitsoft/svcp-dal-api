package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.AgrupacionComercialDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.AgrupacionComercialFiltro;
import com.elitsoft.servicampo.mapper.AgrupacionComercialMapper;
import com.elitsoft.servicampo.mapstruct.AgrupacionComercialMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgrupacionComercialFiltroService {

    @Autowired
    private AgrupacionComercialMapper agrupacionComercialMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private AgrupacionComercialMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(AgrupacionComercialFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para AgrupacionComercial
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<AgrupacionComercialDto> lista de entidades AgrupacionComercial
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<AgrupacionComercialDTO> filtrar(AgrupacionComercialFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDtoList(agrupacionComercialMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.AGRUPACIONCOMERCIAL_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para AgrupacionComercial
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(AgrupacionComercialFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return agrupacionComercialMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.AGRUPACIONCOMERCIAL_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.AGRUPACIONCOMERCIAL_FILTRAR_MENSAJE, e);
        }
    }
}
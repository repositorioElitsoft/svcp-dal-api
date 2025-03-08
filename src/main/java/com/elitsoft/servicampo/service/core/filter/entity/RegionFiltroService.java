package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.RegionDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.RegionFiltro;
import com.elitsoft.servicampo.mapper.RegionMapper;
import com.elitsoft.servicampo.mapstruct.RegionMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionFiltroService {

    @Autowired
    private RegionMapper regionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RegionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(RegionFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para Region
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<RegionDTO> lista de entidades Region
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<RegionDTO> filtrar(RegionFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDtoList(regionMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.REGION_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.REGION_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para Region
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(RegionFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return regionMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.REGION_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.REGION_FILTRAR_MENSAJE, e);
        }
    }
}
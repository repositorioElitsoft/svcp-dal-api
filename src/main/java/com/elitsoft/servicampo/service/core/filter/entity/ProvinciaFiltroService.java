package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.ProvinciaDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.ProvinciaFiltro;
import com.elitsoft.servicampo.mapper.ProvinciaMapper;
import com.elitsoft.servicampo.mapstruct.ProvinciaMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaFiltroService {

    @Autowired
    private ProvinciaMapper provinciaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ProvinciaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ProvinciaFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para Provincia
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<ProvinciaDTO> lista de entidades Provincia
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<ProvinciaDTO> filtrar(ProvinciaFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDtoList(provinciaMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.PROVINCIA_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.PROVINCIA_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para Provincia
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(ProvinciaFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return provinciaMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.PROVINCIA_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.PROVINCIA_FILTRAR_MENSAJE, e);
        }
    }
}
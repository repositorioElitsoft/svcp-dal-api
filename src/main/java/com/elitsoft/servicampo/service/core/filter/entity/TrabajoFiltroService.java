package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.TrabajoFiltro;
import com.elitsoft.servicampo.mapper.TrabajoMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajoFiltroService {

    @Autowired
    private TrabajoMapper trabajoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para Trabajo
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<TrabajoDTO> lista de entidades Trabajo
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<TrabajoDTO> filtrar(TrabajoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDtoList(trabajoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.TRABAJO_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.TRABAJO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para Trabajo
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(TrabajoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return trabajoMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.TRABAJO_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.TRABAJO_FILTRAR_MENSAJE, e);
        }
    }
}
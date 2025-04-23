package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.EstadoComponenteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.EstadoComponenteFiltro;
import com.elitsoft.servicampo.mapper.EstadoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.EstadoComponenteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoComponenteFiltroService {

    @Autowired
    private EstadoComponenteMapper estadocomponenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoComponenteFiltroService.class); //Logback

    /**
     * Ejecuta filtro dinamico y paginacion para EstadoComponente
     *
     * @param filtro   clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<EstadoComponenteDTO> lista de entidades EstadoComponente
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<EstadoComponenteDTO> filtrar(EstadoComponenteFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();

            return mapper.toDTOList(estadocomponenteMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        } catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}", Constantes.ESTADOCOMPONENTE_FILTRAR_MENSAJE, filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.ESTADOCOMPONENTE_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para EstadoComponente
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(EstadoComponenteFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return estadocomponenteMapper.contarFiltrar(filtro);
        } catch (DataAccessException e) {
            logeador.error("{}, {}, {}", Constantes.ESTADOCOMPONENTE_FILTRAR_MENSAJE, filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.ESTADOCOMPONENTE_FILTRAR_MENSAJE, e);
        }
    }
}
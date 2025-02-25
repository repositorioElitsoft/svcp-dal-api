package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.entity.Poroto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.PorotoFiltro;
import com.elitsoft.servicampo.mapper.PorotoMapper;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorotoFiltroService {

    @Autowired
    private PorotoMapper porotoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    private static final Logger logeador = LoggerFactory.getLogger(PorotoFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para Poroto
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<Poroto> lista de entidades Poroto
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<Poroto> filtrar(PorotoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            return porotoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.POROTO_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.POROTO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para Poroto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(PorotoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return porotoMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.POROTO_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.POROTO_FILTRAR_MENSAJE, e);
        }
    }
}
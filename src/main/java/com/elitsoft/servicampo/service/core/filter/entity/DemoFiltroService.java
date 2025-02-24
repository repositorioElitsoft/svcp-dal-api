package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.entity.Demo;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.DemoFiltro;
import com.elitsoft.servicampo.mapper.DemoMapper;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoFiltroService {

    @Autowired
    private DemoMapper demoMapper;

    private static final Logger logeador = LoggerFactory.getLogger(DemoFiltroService.class);

    public List<Demo> filtrar(DemoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            return demoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.DEMO_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.DEMO_FILTRAR_MENSAJE, e);
        }

    }

    public int contarFiltrar(DemoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar() estado");

        try {
            return demoMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.DEMO_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.DEMO_FILTRAR_MENSAJE, e);
        }


    }
}
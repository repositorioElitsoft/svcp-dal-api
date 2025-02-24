package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.filter.TareaFiltro;
import com.elitsoft.servicampo.domain.entity.Tarea;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.mapper.TareaMapper;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaFiltroService {

    @Autowired
    private TareaMapper tareaMapper;

    private static final Logger logeador = LoggerFactory.getLogger(TareaFiltroService.class);

    public List<Tarea> filtrarTareas(TareaFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {

        logeador.debug("getTareas() estado");

        try {
            int offset = paginado.getPageNumber() * paginado.getPageSize();
            return tareaMapper.filtrarTareas(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), offset);
        }  catch (DataAccessException e) {
            logeador.error("{} {} {}",Constantes.TAREA_FILTRAR_EXECPTION,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.TAREA_FILTRAR_EXECPTION, e);
        }

    }

    public int contarFiltroTareas(TareaFiltro filtro) throws BaseDatosException {
        logeador.debug("getTotalTareas() estado");

        try {
            return tareaMapper.contarFiltroTareas(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{} {} {}",Constantes.TAREA_FILTRAR_EXECPTION,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.TAREA_FILTRAR_EXECPTION, e);
        }


    }
}
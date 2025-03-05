package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.DireccionEmpleadoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.DireccionEmpleadoFiltro;
import com.elitsoft.servicampo.mapper.DireccionEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.DireccionEmpleadoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionEmpleadoFiltroService {

    @Autowired
    private DireccionEmpleadoMapper direccionempleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DireccionEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(DireccionEmpleadoFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para DireccionEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<DireccionEmpleadoDTO> lista de entidades DireccionEmpleado
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<DireccionEmpleadoDTO> filtrar(DireccionEmpleadoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDTOList(direccionempleadoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.DIRECCIONEMPLEADO_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para DireccionEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(DireccionEmpleadoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return direccionempleadoMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.DIRECCIONEMPLEADO_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.DIRECCIONEMPLEADO_FILTRAR_MENSAJE, e);
        }
    }
}
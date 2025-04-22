package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.TipoEmpleadoFiltro;
import com.elitsoft.servicampo.mapper.TipoEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.TipoEmpleadoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEmpleadoFiltroService {

    @Autowired
    private TipoEmpleadoMapper tipoempleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para TipoEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<TipoEmpleadoDTO> lista de entidades TipoEmpleado
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<TipoEmpleadoDTO> filtrar(TipoEmpleadoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDTOList(tipoempleadoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.TIPOEMPLEADO_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para TipoEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(TipoEmpleadoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return tipoempleadoMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.TIPOEMPLEADO_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_FILTRAR_MENSAJE, e);
        }
    }
}
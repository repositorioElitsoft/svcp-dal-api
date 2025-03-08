package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.ClasificacionClienteFiltro;
import com.elitsoft.servicampo.mapper.ClasificacionClienteMapper;
import com.elitsoft.servicampo.mapstruct. ClasificacionClienteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasificacionClienteFiltroService {

    @Autowired
    private ClasificacionClienteMapper clasificacionclienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClasificacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para ClasificacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<ClasificacionClienteDTO> lista de entidades ClasificacionCliente
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<ClasificacionClienteDTO> filtrar(ClasificacionClienteFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDtoList(clasificacionclienteMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.CLASIFICACIONCLIENTE_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para ClasificacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(ClasificacionClienteFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return clasificacionclienteMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.CLASIFICACIONCLIENTE_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_FILTRAR_MENSAJE, e);
        }
    }
}
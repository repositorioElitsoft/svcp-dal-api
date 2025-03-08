package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.SegmentacionClienteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.SegmentacionClienteFiltro;
import com.elitsoft.servicampo.mapper.SegmentacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.SegmentacionClienteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegmentacionClienteFiltroService {

    @Autowired
    private SegmentacionClienteMapper segmentacionClienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SegmentacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SegmentacionClienteFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para SegmentacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<SegmentacionClienteDTO> lista de entidades SegmentacionCliente
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<SegmentacionClienteDTO> filtrar(SegmentacionClienteFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDtoList(segmentacionClienteMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.SEGMENTACIONCLIENTE_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para SegmentacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(SegmentacionClienteFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return segmentacionClienteMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.SEGMENTACIONCLIENTE_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.SEGMENTACIONCLIENTE_FILTRAR_MENSAJE, e);
        }
    }
}
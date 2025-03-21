package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleTipoProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.ContratoDetalleTipoProductoFiltro;
import com.elitsoft.servicampo.mapper.ContratoDetalleTipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleTipoProductoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoDetalleTipoProductoFiltroService {

    @Autowired
    private ContratoDetalleTipoProductoMapper contratodetalletipoproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleTipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleTipoProductoFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para ContratoDetalleTipoProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<ContratoDetalleTipoProductoDTO> lista de entidades ContratoDetalleTipoProducto
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<ContratoDetalleTipoProductoDTO> filtrar(ContratoDetalleTipoProductoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDTOList(contratodetalletipoproductoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.CONTRATODETALLETIPOPRODUCTO_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.CONTRATODETALLETIPOPRODUCTO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para ContratoDetalleTipoProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(ContratoDetalleTipoProductoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return contratodetalletipoproductoMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.CONTRATODETALLETIPOPRODUCTO_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.CONTRATODETALLETIPOPRODUCTO_FILTRAR_MENSAJE, e);
        }
    }
}
package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.ContratoDetalleProductoFiltro;
import com.elitsoft.servicampo.mapper.ContratoDetalleProductoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleProductoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoDetalleProductoFiltroService {

    @Autowired
    private ContratoDetalleProductoMapper contratodetalleproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleProductoFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para ContratoDetalleProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<ContratoDetalleProductoDTO> lista de entidades ContratoDetalleProducto
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<ContratoDetalleProductoDTO> filtrar(ContratoDetalleProductoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDTOList(contratodetalleproductoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.CONTRATODETALLEPRODUCTO_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.CONTRATODETALLEPRODUCTO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para ContratoDetalleProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(ContratoDetalleProductoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return contratodetalleproductoMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.CONTRATODETALLEPRODUCTO_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.CONTRATODETALLEPRODUCTO_FILTRAR_MENSAJE, e);
        }
    }
}
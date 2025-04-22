package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.TipoProductoFiltro;
import com.elitsoft.servicampo.mapper.TipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoFiltroService {

    @Autowired
    private TipoProductoMapper tipoproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoFiltroService.class); //Logback

    /**
     * Ejecuta filtro dinamico y paginacion para TipoProducto
     *
     * @param filtro   clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<TipoProductoDto> lista de entidades TipoProducto
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<TipoProductoDTO> filtrar(TipoProductoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();

            return mapper.toDTOList(tipoproductoMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        } catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}", Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Ejecuta filtro dinamico y paginacion para TipoProducto y Asignacion de Tipos Componentes
     *
     * @param filtro   clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<TipoProductoDto> lista de entidades TipoProducto
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<TipoProductoDTO> filtrarAsignacion(TipoProductoFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrarAsignacion()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();

            return tipoproductoMapper.filtrarAsignacion(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento);
        } catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}", Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para TipoProducto
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(TipoProductoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return tipoproductoMapper.contarFiltrar(filtro);
        } catch (DataAccessException e) {
            logeador.error("{}, {}, {}", Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, e);
        }
    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para TipoProducto y Asignacion de Tipos Componentes
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrarAsignacion(TipoProductoFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return tipoproductoMapper.contarFiltrarAsignacion(filtro);
        } catch (DataAccessException e) {
            logeador.error("{}, {}, {}", Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.TIPOPRODUCTO_FILTRAR_MENSAJE, e);
        }
    }
}
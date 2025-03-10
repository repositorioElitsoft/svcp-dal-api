package com.elitsoft.servicampo.service.core.filter.entity;

import com.elitsoft.servicampo.domain.dto.core.ClienteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.filter.ClienteFiltro;
import com.elitsoft.servicampo.mapper.ClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClienteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.PagingAndSorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteFiltroService {

    @Autowired
    private ClienteMapper clienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ClienteFiltroService.class); //Logback

    /** Ejecuta filtro dinamico y paginacion para Cliente
     * @param filtro clase que tiene los atributos a filtrar
     * @param paginado clase que tiene los atributos de paginacion
     * @return List<ClienteDTO> lista de entidades Cliente
     * @throws BaseDatosException si la entrada LotePaginado tiene errores.
     */
    public List<ClienteDTO> filtrar(ClienteFiltro filtro, PagingAndSorting paginado) throws BaseDatosException {
        logeador.debug("filtrar()");

        try {
            int desplazamiento = paginado.getPageNumber() * paginado.getPageSize();
            
            return mapper.toDTOList(clienteMapper.filtrar(filtro, paginado.getSortField(), paginado.getSortDirection(), paginado.getPageSize(), desplazamiento));
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}, {}",Constantes.CLIENTE_FILTRAR_MENSAJE,  filtro.toString(), paginado.toString(), e, e);
            throw new BaseDatosException(Constantes.CLIENTE_FILTRAR_MENSAJE, e);
        }

    }

    /**
     * Cuenta los registros que coinciden con el filtro dinamico para Cliente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    public int contarFiltrar(ClienteFiltro filtro) throws BaseDatosException {
        logeador.debug("contarFiltrar()");

        try {
            return clienteMapper.contarFiltrar(filtro);
        }  catch (DataAccessException e) {
            logeador.error("{}, {}, {}",Constantes.CLIENTE_FILTRAR_MENSAJE,  filtro.toString(), e, e);
            throw new BaseDatosException(Constantes.CLIENTE_FILTRAR_MENSAJE, e);
        }
    }
}
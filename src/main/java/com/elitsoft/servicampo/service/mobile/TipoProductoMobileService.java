package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.TipoProductoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoMapStruct;
import com.elitsoft.servicampo.service.core.TipoProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoProducto.
 */
@Component
public class TipoProductoMobileService {

    @Autowired
    private TipoProductoMapper tipoproductoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoService tipoproductoService; //Logica de Negocio del Core Service

    @Autowired
    private TipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoMobileService.class);

    /**
     * Agrega un nuevo TipoProducto.
     * @param tipoproductoDto El TipoProducto DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TipoProductoDto tipoproductoDto) throws BaseDatosException {
        logeador.debug("agregar() tipoproducto");
        tipoproductoService.agregar(tipoproductoDto);
    }

    /**
     * Actualiza un TipoProducto existente.
     * @param id La Clave de TipoProducto a actualizar.
     * @param tipoproductoDto El TipoProducto DTO con informacion actualizada.
     * @throws TipoProductoNoEncontradoException Si TipoProducto no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TipoProductoDto tipoproductoDto) throws BaseDatosException, TipoProductoNoEncontradoException {
        logeador.debug("actualizar() tipoproducto");
        tipoproductoService.actualizar(id, tipoproductoDto);
    }

    /**
     * Elimina TipoProducto por Clave.
     * @param id La Clave de TipoProducto a eliminar.
     * @throws TipoProductoNoEncontradoException Si el TipoProducto no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, TipoProductoNoEncontradoException {
        logeador.debug("eliminar() tipoproducto: {}", id);
        tipoproductoService.eliminar(id);
    }

    /**
     * Encuentra un TipoProducto por Clave.
     * @param id La Clave TipoProducto a encontrar.
     * @return El TipoProducto DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TipoProductoNoEncontradoException Si TipoProducto no es encontrado.
     */
    public TipoProductoDto encontrarPorClave(Long id) throws BaseDatosException, TipoProductoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoproductoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoProductos.
     * @return Una lista de todos TipoProducto DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TipoProductoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoproductoService.obtenerTodos();
    }
}
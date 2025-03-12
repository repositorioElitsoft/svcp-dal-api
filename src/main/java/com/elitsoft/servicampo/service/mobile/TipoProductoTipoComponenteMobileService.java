package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoTipoComponenteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoProductoTipoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoTipoComponenteMapStruct;
import com.elitsoft.servicampo.service.core.TipoProductoTipoComponenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoProductoTipoComponente.
 */
@Component
public class TipoProductoTipoComponenteMobileService {

    @Autowired
    private TipoProductoTipoComponenteMapper tipoproductotipocomponenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoTipoComponenteService tipoproductotipocomponenteService; //Logica de Negocio del Core Service

    @Autowired
    private TipoProductoTipoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoTipoComponenteMobileService.class); //Logback

    /**
     * Agrega un nuevo TipoProductoTipoComponente.
     * @param tipoproductotipocomponenteDTO el TipoProductoTipoComponente DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoProductoTipoComponente ya existe.
     */
    public void agregar(TipoProductoTipoComponenteDTO tipoproductotipocomponenteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipoproductotipocomponente");

        tipoproductotipocomponenteService.agregar(tipoproductotipocomponenteDTO);
    }


    /**
     * Agrega Lote nuevos TipoProductoTipoComponente.
     * @param tipoproductotipocomponenteDTOLote lista de TipoProductoTipoComponente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoProductoTipoComponente ya existe.
     */
    public void agregarLote(List<TipoProductoTipoComponenteDTO> tipoproductotipocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoproductotipocomponente");

        tipoproductotipocomponenteService.agregarLote(tipoproductotipocomponenteDTOLote);
    }

    /**
     * Actualiza un TipoProductoTipoComponente existente.
     * @param tipoComponenteId La clave de TipoComponente a actualizar.
     * @param tipoProductoId La clave de TipoProducto a actualizar.
     * @param tipoproductotipocomponenteDTO el TipoProductoTipoComponente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProductoTipoComponente no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     */
    public void actualizar(Long tipoComponenteId, Long tipoProductoId, TipoProductoTipoComponenteDTO tipoproductotipocomponenteDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tipoproductotipocomponente");

        tipoproductotipocomponenteService.actualizar(tipoComponenteId, tipoProductoId, tipoproductotipocomponenteDTO);
    }

    /**
     * Actualiza Lote de TipoProductoTipoComponente existentes.
     * @param tipoproductotipocomponenteDTOLote lista de TipoProductoTipoComponente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     */
    public void actualizarLote(List<TipoProductoTipoComponenteDTO> tipoproductotipocomponenteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoproductotipocomponente");

        tipoproductotipocomponenteService.actualizarLote(tipoproductotipocomponenteDTOLote);
    }

    /**
     * Elimina TipoProductoTipoComponente por Clave.
     * @param tipoComponenteId La clave de TipoComponente a eliminar.
     * @param tipoProductoId La clave de TipoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el TipoProductoTipoComponente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long tipoComponenteId, Long tipoProductoId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoproductotipocomponente: {}, {}", tipoComponenteId, tipoProductoId );
        tipoproductotipocomponenteService.eliminar(tipoComponenteId, tipoProductoId);
    }

    /**
     * Elimina Lote TipoProductoTipoComponente por Clave.
     * @param tipoproductotipocomponenteDTOLote lista de claves de TipoProductoTipoComponente a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoProductoTipoComponente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<TipoProductoTipoComponenteDTO> tipoproductotipocomponenteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipoproductotipocomponenteService.eliminarLote(tipoproductotipocomponenteDTOLote);
    }

    /**
     * Encuentra un TipoProductoTipoComponente por Clave.
     * @param tipoComponenteId La clave de TipoComponente a encontrar.
     * @param tipoProductoId La clave de TipoProducto a encontrar.
     * @return el TipoProductoTipoComponente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProductoTipoComponente no es encontrado.
     */
    public TipoProductoTipoComponenteDTO encontrarPorClave(Long tipoComponenteId, Long tipoProductoId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}, {}", tipoComponenteId,tipoProductoId );
        return tipoproductotipocomponenteService.encontrarPorClave(tipoComponenteId, tipoProductoId);
    }

    /**
     * Obtiene todos los TipoProductoTipoComponentes.
     * @return lista de todos TipoProductoTipoComponente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoProductoTipoComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoproductotipocomponenteService.obtenerTodos();
    }
}
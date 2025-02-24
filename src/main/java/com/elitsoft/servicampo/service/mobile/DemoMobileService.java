package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.DemoDto;
import com.elitsoft.servicampo.domain.entity.Demo;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.DemoMapper;
import com.elitsoft.servicampo.mapstruct.DemoMapStruct;
import com.elitsoft.servicampo.service.core.DemoService;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Demo.
 */
@Service
public class DemoMobileService {

    @Autowired
    private DemoMapper demoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DemoService demoService; //Logica de Negocio del Core Service

    @Autowired
    private DemoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(DemoMobileService.class); //Logback

    /**
     * Agrega un nuevo Demo.
     * @param demoDto El Demo DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     * @throws EntradaInvalidadException Si la entrada Demo tiene errores.
     * @throws RecursoDuplicadoException Si el recurso demo ya existe.
     */
    public void agregar(DemoDto demoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() demo");

        demoService.agregar(demoDto);
    }

    /**
     * Agrega Lote nuevos Demo.
     * @param demoLoteDto Lista de Demo DTO a agregar.
     * @throws BaseDatosException  Si ocurre un error de base de datos.
     * @throws EntradaInvalidadException Si la entrada Demo tiene errores.
     * @throws RecursoDuplicadoException Si el recurso demo ya existe.
     */
    public void agregarLote(List<DemoDto> demoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() demo Todos");

        demoService.agregarLote(demoLoteDto);
    }

    /**
     * Actualiza un Demo existente.
     * @param id La Clave de Demo a actualizar.
     * @param demoDto El Demo DTO con informacion actualizada.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException Si Demo no es encontrado.
     * @throws EntradaInvalidadException Si la entrada Demo tiene errores.
     */
    public void actualizar(Long id, DemoDto demoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() demo");

        demoService.actualizar(id, demoDto);
    }

    /**
     * Actualiza Lote de Demo existentes.
     * @param demoLoteDto Lista de Demo DTO con datos a actualizar.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     * @throws EntradaInvalidadException Si la entrada Demo tiene errores.
     */
    public void actualizarLote(List<DemoDto> demoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() demo");

        demoService.actualizarLote(demoLoteDto);
    }

    /**
     * Elimina Demo por Clave.
     * @param id La Clave de Demo a eliminar.
     * @throws RecursoNoEncontradoException Si el Demo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() demo: {}", id);
        demoService.eliminar(id);
    }

    /**
     * Elimina Lote Demo por Clave.
     * @param idLote Lista de Claves de Demo a eliminar.
     * @throws EntradaInvalidadException Si la lista  Demo esta vacia.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        demoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Demo por Clave.
     * @param id La Clave Demo a encontrar.
     * @return El Demo DTO encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException Si Demo no es encontrado.
     */
    public DemoDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return demoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Demos.
     * @return Una lista de todos Demo DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<DemoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return demoService.obtenerTodos();
    }
}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.DemoDto;
import com.elitsoft.servicampo.domain.entity.Demo;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.DemoMapper;
import com.elitsoft.servicampo.mapstruct.DemoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Demo.
 */
@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DemoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(DemoService.class); //Logback

    /**
     * Agrega un nuevo Demo.
     * @param demoDto El Demo DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     * @throws EntradaInvalidadException Si la entrada Demo tiene errores.
     * @throws RecursoDuplicadoException Si el recurso demo ya existe.
     */
    public void agregar(DemoDto demoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() demo");

        //  Valida Entrada
        if (demoDto == null || demoDto.getDmoId()== null) {
            logeador.error(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((demoDto != null) ? demoDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Demo demo = mapper.toEntity(demoDto);
            Long nuevoId = demoMapper.agregar(demo);
            logeador.info("Demo agregado exitosamente id: {}", nuevoId);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.DEMO_DUPLICADO_MENSAGE + ": {}", demoDto.getDmoId());
            throw new RecursoDuplicadoException(Constantes.DEMO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.DEMO_AGREGAR_MENSAJE + ": {}", demoDto.toString(), e);
            throw new BaseDatosException(Constantes.DEMO_AGREGAR_MENSAJE, e);
        }
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

        //  Valida Entrada
        if (demoLoteDto.isEmpty()) {
            logeador.error(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Demo> demoLote = mapper.toEntityList(demoLoteDto);

            int registrosAgregados =  demoMapper.agregarLote(demoLote);
            logeador.info("Lote Demo agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.DEMO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.DEMO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DEMO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.DEMO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Demo existente.
     * @param id La Clave de Demo a actualizar.
     * @param demoDto El Demo DTO con informacion actualizada.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException Si Demo no es encontrado.
     * @throws EntradaInvalidadException Si la entrada Demo tiene errores.
     */
    public void actualizar(Long id, DemoDto demoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() demo");

        //  Valida Entrada
        if (id == null || demoDto == null || demoDto.getDmoId()== null) {
            logeador.error(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((demoDto != null) ? demoDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            DemoDto demoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Demo demo = mapper.toEntity(demoDto);
            demo.setDmoId(id);
            int registrosActualizados = demoMapper.actualizar(demo);
            logeador.info("demo actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DEMO_ACTUALIZAR_MENSAJE + ": id={} {}", id, demoDto.toString(), e);
            throw new BaseDatosException(Constantes.DEMO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de Demo existentes.
     * @param demoLoteDto Lista de Demo DTO con datos a actualizar.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     * @throws EntradaInvalidadException Si la entrada Demo tiene errores.
     */
    public void actualizarLote(List<DemoDto> demoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() demo");

        //  Valida Entrada
        if (demoLoteDto.isEmpty()) {
            logeador.error(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Demo> demoLote = mapper.toEntityList(demoLoteDto);
            int registrosActualizados = demoMapper.actualizarLote(demoLote);
            logeador.info("Lote demo actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DEMO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.DEMO_ACTUALIZAR_MENSAJE, e);
        }
    }


    /**
     * Elimina Demo por Clave.
     * @param id La Clave de Demo a eliminar.
     * @throws RecursoNoEncontradoException Si el Demo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() demo: {}", id);

        try {
            DemoDto demoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = demoMapper.eliminar(id);
            logeador.info("demo eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DEMO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.DEMO_ELIMINAR_MENSAJE, e);
        }

    }


    /**
     * Elimina Lote Demo por Clave.
     * @param idLote Lista de Claves de Demo a eliminar.
     * @throws EntradaInvalidadException Si la lista  Demo esta vacia.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DEMO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = demoMapper.eliminarLote(idLote);
            logeador.info("Lote demo eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DEMO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.DEMO_ELIMINAR_MENSAJE, e);
        }
    }


    /**
     * Encuentra un Demo por Clave.
     * @param id La Clave Demo a encontrar.
     * @return El Demo DTO encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException Si Demo no es encontrado.
     */
    public DemoDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            DemoDto demoDto = mapper.toDto(demoMapper.encontrarPorClave(id));

            if (demoDto != null) {
                logeador.info("demo encontrado por clave : {}", id);
            } else {
                logeador.info("demo clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.DEMO_NO_ENCONTRADO_MENSAGE);
            }

            return demoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DEMO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.DEMO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Demos.
     * @return Una lista de todos Demo DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<DemoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<DemoDto> demoList = mapper.toDtoList(demoMapper.obtenerTodos());
            logeador.info("demos obtenidos");
            return demoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DEMO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.DEMO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
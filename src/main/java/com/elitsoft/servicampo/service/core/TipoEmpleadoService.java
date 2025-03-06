package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.domain.entity.Empleado;
import com.elitsoft.servicampo.domain.entity.Sector;
import com.elitsoft.servicampo.domain.entity.TipoEmpleado;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.EmpleadoMapper;
import com.elitsoft.servicampo.mapper.TipoEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.TipoEmpleadoMapStruct;
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
 * Clase de Servicio para la entidad TipoEmpleado.
 */
@Service
public class TipoEmpleadoService {

    @Autowired
    private TipoEmpleadoMapper tipoempleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EmpleadoMapper empleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoService.class); //Logback


    /**
     * Agrega un nuevo TipoEmpleado.
     * @param tipoempleadoDto el TipoEmpleado DTO.
     * @return el TipoEmpleado DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoEmpleado ya existe.
     */
    public TipoEmpleadoDto agregar(TipoEmpleadoDto tipoempleadoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoEmpleado");

        //  Valida Entrada
        if (tipoempleadoDto == null) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoEmpleado tipoempleado = mapper.toEntity(tipoempleadoDto);
            tipoempleado = tipoempleadoMapper.agregar(tipoempleado);
            logeador.info("TipoEmpleado agregado exitosamente id: {}", tipoempleado.getId());
            return mapper.toDto(tipoempleado);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE + ": {}", tipoempleadoDto.getId());
            throw new RecursoDuplicadoException(Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_AGREGAR_MENSAJE + ": {}", tipoempleadoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoEmpleado.
     * @param tipoempleadoLoteDto lista de TipoEmpleado DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipoempleado ya existe.
     */
    public void agregarLote(List<TipoEmpleadoDto> tipoempleadoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoempleado");

        //  Valida Entrada
        if (tipoempleadoLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoEmpleado> tipoempleadoLote = mapper.toEntityList(tipoempleadoLoteDto);

            int registrosAgregados =  tipoempleadoMapper.agregarLote(tipoempleadoLote);
            logeador.info("Lote TipoEmpleado agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.TIPOEMPLEADO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoEmpleado existente.
     * @param id la clave de TipoEmpleado a actualizar.
     * @param tipoempleadoDto el TipoEmpleado DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     */
    public void actualizar(Long id, TipoEmpleadoDto tipoempleadoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tipoempleado");

        //  Valida Entrada
        if (id == null || tipoempleadoDto == null || tipoempleadoDto.getId() == null) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((tipoempleadoDto != null) ? tipoempleadoDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipoempleadoDto.getId())) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  tipoempleadoDto.toString());
            throw new EntradaInvalidadException(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoEmpleadoDto tipoempleadoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoEmpleado tipoempleado = mapper.toEntity(tipoempleadoDto);
            tipoempleado.setId(id);
            int registrosActualizados = tipoempleadoMapper.actualizar(tipoempleado);
            logeador.info("tipoempleado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE + ": id={} {}", id, tipoempleadoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoEmpleado existentes.
     * @param tipoempleadoLoteDto lista de TipoEmpleado DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     */
    public void actualizarLote(List<TipoEmpleadoDto> tipoempleadoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoempleado");

        //  Valida Entrada
        if (tipoempleadoLoteDto.isEmpty()) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoEmpleado> tipoempleadoLote = mapper.toEntityList(tipoempleadoLoteDto);
            int registrosActualizados = tipoempleadoMapper.actualizarLote(tipoempleadoLote);
            logeador.info("Lote tipoempleado actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoEmpleado por Clave.
     * @param id la clave de TipoEmpleado a eliminar.
     * @throws RecursoNoEncontradoException si el TipoEmpleado no es encontrado.
     * @throws RecursoEliminarException si el TipoEmpleado viola la integridad referencial.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, RecursoEliminarException, BaseDatosException {
        logeador.debug("eliminar() tipoempleado: {}", id);

        //Verifica integridad referencial
        this.verificarIntegridadEliminar(id);

        try {
            TipoEmpleadoDto tipoempleadoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoempleadoMapper.eliminar(id);
            logeador.info("tipoempleado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoEmpleado por Clave.
     * @param idLote lista de claves de TipoEmpleado a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoEmpleado esta vacia.
     * @throws RecursoEliminarException si el TipoEmpleado viola la integridad referencial.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.TIPOEMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
        for (Long id : idLote) {
            this.verificarIntegridadEliminar(id);
        }

        try {
            int registrosEliminados = tipoempleadoMapper.eliminarLote(idLote);
            logeador.info("Lote tipoempleado eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoEmpleado por Clave.
     * @param id la clave TipoEmpleado a encontrar.
     * @return el TipoEmpleado DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     */
    public TipoEmpleadoDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoEmpleadoDto tipoempleadoDto = mapper.toDto(tipoempleadoMapper.encontrarPorClave(id));

            if (tipoempleadoDto != null) {
                logeador.info("tipoempleado encontrado por clave : {}", id);
            } else {
                logeador.info("tipoempleado clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE);
            }

            return tipoempleadoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoEmpleados.
     * @return una lista de todos TipoEmpleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoEmpleadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoEmpleadoDto> tipoempleadoList = mapper.toDtoList(tipoempleadoMapper.obtenerTodos());
            logeador.info("tipoempleados obtenidos");
            return tipoempleadoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_OBTENER_TODOS_MENSAJE, e);
        }
    }

    /**
     * Verifica la violacion de integridad referencia de TipoEmpleados
     * @param id la clave TipoEmpleados a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
        logeador.debug("verificarIntegridadEliminar() zona: {}", id);

        boolean empleadosPortipoEmpleado = false;

        try {
            empleadosPortipoEmpleado = this.empleadosPorTipoEmpleado(id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ELIMINAR_MENSAJE, e);
        }

        //Verifca la integridad con sectores
        if (empleadosPortipoEmpleado) {
            throw new RecursoEliminarException(Constantes.TIPOEMPLEADO_VIOLACION_INTEGRIDAD_MENSAGE);
        }

    }

    /**
     * Buscar TipoEmpleados que tengan Empleados.
     * @param id la clave TipoEmpleados a encontrar.
     * @return boolean TipoEmpleados tiene o no registros asociados
     * @throws BaseDatosException
     */
    public boolean empleadosPorTipoEmpleado(Long id) throws  BaseDatosException {
        logeador.debug("sectoresPorZona() zona: {}", id);

        try {
            List<Empleado> empleados = empleadoMapper.encontrarPorTipoEmpleado(id,true); // Verifica si existe tipoempleados asociados
            if (!empleados.isEmpty()) {
                logeador.info("tipoempleados  tiene empleados asociados");
                return true;
            } else{
                logeador.info("tipoempleados no tiene empleados asociados");
                return false;
            }
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_EMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_EMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }
}
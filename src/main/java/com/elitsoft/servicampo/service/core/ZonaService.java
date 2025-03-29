package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ZonaDTO;
import com.elitsoft.servicampo.domain.entity.Sector;
import com.elitsoft.servicampo.domain.entity.Zona;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.SectorMapper;
import com.elitsoft.servicampo.mapper.ZonaMapper;
import com.elitsoft.servicampo.mapstruct.ZonaMapStruct;
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
 * Clase de Servicio para la entidad Zona.
 */
@Service
public class ZonaService {

    @Autowired
    private ZonaMapper zonaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SectorMapper sectorMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ZonaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ZonaService.class); //Logback


    /**
     * Agrega un nuevo Zona.
     * @param zonaDTO el Zona DTO.
     * @return el Zona DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     * @throws RecursoDuplicadoException si el recurso Zona ya existe.
     */
    public ZonaDTO agregar(ZonaDTO zonaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Zona");

        //  Valida Entrada
        if (zonaDTO == null) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Zona zona = mapper.toEntity(zonaDTO);
            zona = zonaMapper.agregar(zona);
            logeador.info("Zona agregado exitosamente id: {}", zona.getId());
            return mapper.toDto(zona);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.ZONA_DUPLICADO_MENSAGE + ": {}", zonaDTO.getId());
            throw new RecursoDuplicadoException(Constantes.ZONA_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_AGREGAR_MENSAJE + ": {}", zonaDTO.toString(), e);
            throw new BaseDatosException(Constantes.ZONA_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Zona.
     * @param zonaLoteDTO lista de Zona DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     * @throws RecursoDuplicadoException si el recurso zona ya existe.
     */
    public void agregarLote(List<ZonaDTO> zonaLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() zona");

        //  Valida Entrada
        if (zonaLoteDTO.isEmpty()) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Zona> zonaLote = mapper.toEntityList(zonaLoteDTO);

            int registrosAgregados =  zonaMapper.agregarLote(zonaLote);
            logeador.info("Lote Zona agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.ZONA_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.ZONA_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.ZONA_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Zona existente.
     * @param id la clave de Zona a actualizar.
     * @param zonaDTO el Zona DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Zona no es encontrado.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     */
    public void actualizar(Long id, ZonaDTO zonaDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() zona");

        //  Valida Entrada
        if (id == null || zonaDTO == null || zonaDTO.getId() == null) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE + ": {}", ((zonaDTO != null) ? zonaDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(zonaDTO.getId())) {
            logeador.error(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  zonaDTO.toString());
            throw new EntradaInvalidadException(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ZonaDTO zonaDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Zona zona = mapper.toEntity(zonaDTO);
            zona.setId(id);
            int registrosActualizados = zonaMapper.actualizar(zona);
            logeador.info("zona actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_ACTUALIZAR_MENSAJE + ": id={} {}", id, zonaDTO.toString(), e);
            throw new BaseDatosException(Constantes.ZONA_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Zona existentes.
     * @param zonaLoteDTO lista de Zona DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Zona tiene errores.
     */
    public void actualizarLote(List<ZonaDTO> zonaLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() zona");

        //  Valida Entrada
        if (zonaLoteDTO.isEmpty()) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Zona> zonaLote = mapper.toEntityList(zonaLoteDTO);
            int registrosActualizados = zonaMapper.actualizarLote(zonaLote);
            logeador.info("Lote zona actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.ZONA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Zona por Clave.
     * @param id la clave de Zona a eliminar.
     * @throws RecursoNoEncontradoException si el Zona no es encontrado.
     * @throws RecursoEliminarException si el Zona viola la integridad referencial.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() zona: {}", id);



        try {
            ZonaDTO zonaDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = zonaMapper.eliminar(id);
            logeador.info("zona eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Zona por Clave.
     * @param idLote lista de claves de Zona a eliminar.
     * @throws EntradaInvalidadException si la lista  Zona esta vacia.
     * @throws RecursoEliminarException si el Zona viola la integridad referencial.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException  {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.ZONA_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = zonaMapper.eliminarLote(idLote);
            logeador.info("Lote zona eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.ZONA_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.ZONA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Zona por Clave.
     * @param id la clave Zona a encontrar.
     * @return el Zona DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Zona no es encontrado.
     */
    public ZonaDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ZonaDTO zonaDTO = mapper.toDto(zonaMapper.encontrarPorClave(id));

            if (zonaDTO != null) {
                logeador.info("zona encontrado por clave : {}", id);
            } else {
                logeador.info("zona clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.ZONA_NO_ENCONTRADO_MENSAGE);
            }

            return zonaDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Zonas.
     * @return una lista de todos Zona DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ZonaDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ZonaDTO> zonaLista = mapper.toDtoList(zonaMapper.obtenerTodos());
            logeador.info("zonas obtenidos");
            return zonaLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.ZONA_OBTENER_TODOS_MENSAJE, e);
        }
    }


    /**
     * Verifica la violacion de integridad referencia de Zona
     * @param id la clave Zona a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
        logeador.debug("verificarIntegridadEliminar() zona: {}", id);

        boolean sectoresPorZona = false;

        try {
            sectoresPorZona = this.sectoresPorZona(id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_ELIMINAR_MENSAJE, e);
        }

        //Verifca la integridad con sectores
        if (sectoresPorZona) {
            throw new RecursoEliminarException(Constantes.ZONA_VIOLACION_INTEGRIDAD_MENSAGE);
        }

    }

    /**
     * Buscar Zonas que tengan Sectores.
     * @param id la clave Zona a encontrar.
     * @return boolean Zona tiene o no registros asociados
     * @throws BaseDatosException
     */
    public boolean sectoresPorZona(Long id) throws  BaseDatosException {
        logeador.debug("sectoresPorZona() zona: {}", id);

        try {
            List<Sector> sectores = sectorMapper.encontrarPorZona(id,true); // Verifica si existe Sectores asociados
            if (!sectores.isEmpty()) {
                logeador.info("zona  tiene sectores asociados");
                return true;
            } else{
                logeador.info("zona no tiene sectores asociados");
                return false;
            }
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ZonaDto;
import com.elitsoft.servicampo.domain.entity.Zona;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ZonaMapper;
import com.elitsoft.servicampo.mapstruct.ZonaMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Zona.
 */
@Service
@Transactional
public class ZonaService {

    @Autowired
    private ZonaMapper zonaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ZonaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ZonaService.class);

    /**
     * Agrega un nuevo Zona.
     * @param zonaDto El Zona DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(ZonaDto zonaDto) throws BaseDatosException {
        logeador.debug("agregar() zona");


        try {
            Zona zona = mapper.toEntity(zonaDto);
            Long nuevoId = zonaMapper.agregar(zona);
            logeador.info("Zona agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_AGREGAR_EXECPTION + ": {}", zonaDto.toString(), e);
            throw new BaseDatosException(Constantes.ZONA_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Zona existente.
     * @param id La Clave de Zona a actualizar.
     * @param zonaDto El Zona DTO con informacion actualizada.
     * @throws ZonaNoEncontradoException Si Zona no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, ZonaDto zonaDto) throws ZonaNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() zona");

        try {
            ZonaDto zonaDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Zona zona = mapper.toEntity(zonaDto);
            zona.setId(id);
            int registrosActualizados = zonaMapper.actualizar(zona);
            logeador.info("zona actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (ZonaNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ACTUALIZAR_EXECPTION + ": id={} {}", id, zonaDto.toString(), e);
            throw new BaseDatosException(Constantes.ZONA_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Zona por Clave.
     * @param id La Clave de Zona a eliminar.
     * @throws ZonaNoEncontradoException Si el Zona no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws ZonaNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() zona: {}", id);

        try {
            ZonaDto zonaDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = zonaMapper.eliminar(id);
            logeador.info("zona eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (ZonaNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Zona por Clave.
     * @param id La Clave Zona a encontrar.
     * @return El Zona DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws ZonaNoEncontradoException Si Zona no es encontrado.
     */
    public ZonaDto encontrarPorClave(Long id) throws BaseDatosException, ZonaNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ZonaDto zonaDto = mapper.toDto(zonaMapper.encontrarPorClave(id));

            if (zonaDto != null) {
                logeador.info("zona encontrado por clave : {}", id);
            } else {
                logeador.info("zona clave:{} no encontrado", id);
                throw new ZonaNoEncontradoException(Constantes.ZONA_NO_ENCONTRADO_MENSAGE);
            }

            return zonaDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.ZONA_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Zonas.
     * @return Una lista de todos Zona DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<ZonaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ZonaDto> zonaList = mapper.toDtoList(zonaMapper.obtenerTodos());
            logeador.info("zonas obtenidos");
            return zonaList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ZONA_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.ZONA_OBTENER_TODOS_EXECPTION, e);
        }
    }
}
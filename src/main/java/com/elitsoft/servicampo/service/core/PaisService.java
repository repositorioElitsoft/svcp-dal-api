package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.PaisDto;
import com.elitsoft.servicampo.domain.entity.Pais;
import com.elitsoft.servicampo.domain.entity.Provincia;
import com.elitsoft.servicampo.domain.entity.Region;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.PaisMapper;
import com.elitsoft.servicampo.mapstruct.PaisMapStruct;
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
 * Clase de Servicio para la entidad Pais.
 */
@Service
public class PaisService {

    @Autowired
    private PaisMapper paisMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PaisMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PaisService.class); //Logback

    /**
     * Agrega un nuevo Pais.
     * @param paisDto el Pais DTO.
     * @return el Pais DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     * @throws RecursoDuplicadoException si el recurso Pais ya existe.
     */
    public PaisDto agregar(PaisDto paisDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Pais");

        //  Valida Entrada
        if (paisDto == null) {
            logeador.error(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Pais pais = mapper.toEntity(paisDto);
            pais = paisMapper.agregar(pais);
            logeador.info("Pais agregado exitosamente id: {}", pais.getId());
            return mapper.toDto(pais);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.PAIS_DUPLICADO_MENSAGE + ": {}", paisDto.getId());
            throw new RecursoDuplicadoException(Constantes.PAIS_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.PAIS_AGREGAR_MENSAJE + ": {}", paisDto.toString(), e);
            throw new BaseDatosException(Constantes.PAIS_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Pais.
     * @param paisLoteDto lista de Pais DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     * @throws RecursoDuplicadoException si el recurso pais ya existe.
     */
    public void agregarLote(List<PaisDto> paisLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() pais");

        //  Valida Entrada
        if (paisLoteDto.isEmpty()) {
            logeador.error(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Pais> paisLote = mapper.toEntityList(paisLoteDto);

            int registrosAgregados =  paisMapper.agregarLote(paisLote);
            logeador.info("Lote Pais agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.PAIS_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.PAIS_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PAIS_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.PAIS_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Pais existente.
     * @param id la clave de Pais a actualizar.
     * @param paisDto el Pais DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Pais no es encontrado.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     */
    public void actualizar(Long id, PaisDto paisDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() pais");

        //  Valida Entrada
        if (id == null || paisDto == null || paisDto.getId() == null) {
            logeador.error(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE + ": {}", ((paisDto != null) ? paisDto.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            PaisDto paisDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Pais pais = mapper.toEntity(paisDto);
            pais.setId(id);
            int registrosActualizados = paisMapper.actualizar(pais);
            logeador.info("pais actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PAIS_ACTUALIZAR_MENSAJE + ": id={} {}", id, paisDto.toString(), e);
            throw new BaseDatosException(Constantes.PAIS_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Pais existentes.
     * @param paisLoteDto lista de Pais DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     */
    public void actualizarLote(List<PaisDto> paisLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() pais");

        //  Valida Entrada
        if (paisLoteDto.isEmpty()) {
            logeador.error(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Pais> paisLote = mapper.toEntityList(paisLoteDto);
            int registrosActualizados = paisMapper.actualizarLote(paisLote);
            logeador.info("Lote pais actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PAIS_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.PAIS_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Pais por Clave.
     * @param id la clave de Pais a eliminar.
     * @throws RecursoNoEncontradoException si el Pais no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() pais: {}", id);

        try {
            PaisDto paisDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = paisMapper.eliminar(id);
            logeador.info("pais eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.PAIS_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.PAIS_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Pais por Clave.
     * @param idLote lista de claves de Pais a eliminar.
     * @throws EntradaInvalidadException si la lista  Pais esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.PAIS_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = paisMapper.eliminarLote(idLote);
            logeador.info("Lote pais eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.PAIS_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.PAIS_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Pais por Clave.
     * @param id la clave Pais a encontrar.
     * @return el Pais DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Pais no es encontrado.
     */
    public PaisDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
           // Pais pais = paisMapper.encontrarPorClave(id);

            //List<Region> regiones =  pais.getRegiones();

            //Region regionX = regiones.get(2);

            //regionX.
            //regiones.forEach(region -> System.out.println(region.getProvincias().size()));


            System.out.println("test");
            PaisDto paisDto = mapper.toDto(paisMapper.encontrarPorClave(id));

            if (paisDto != null) {
                logeador.info("pais encontrado por clave : {}", id);
            } else {
                logeador.info("pais clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.PAIS_NO_ENCONTRADO_MENSAGE);
            }

            return paisDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PAIS_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.PAIS_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Paiss.
     * @return una lista de todos Pais DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<PaisDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<PaisDto> paisList = mapper.toDtoList(paisMapper.obtenerTodos());
            logeador.info("paiss obtenidos");
            return paisList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.PAIS_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.PAIS_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
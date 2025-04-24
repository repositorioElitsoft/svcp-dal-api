package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.BanoDTO;
import com.elitsoft.servicampo.domain.entity.Bano;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.BanoMapper;
import com.elitsoft.servicampo.mapstruct.BanoMapStruct;
import com.elitsoft.servicampo.service.error.BanoError;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Bano.
 */
@Service
public class BanoService {

    @Autowired
    private BanoMapper banoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private BanoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    @Autowired
    private ComponenteService componenteService;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    private static final Logger logeador = LoggerFactory.getLogger(BanoService.class); //Logback

    /**
     * Agrega un nuevo Bano.
     *
     * @param banoDTO el Bano DTO.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws EntradaInvalidadException    si la entrada Bano tiene errores.
     * @throws RecursoDuplicadoException    si el recurso bano ya existe.
     * @throws RecursoNoEncontradoException si Componente no es encontrado.
     */
    public void agregar(BanoDTO banoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() bano");

        //  Valida Entrada
        if (banoDTO == null || banoDTO.getComponente() == null || banoDTO.getComponente().getId() == null) {
            logeador.error(Constantes.BANO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((banoDTO != null) ? banoDTO.toString() : null),
                    BanoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(BanoError.REQUERIDO.getCodigoError(),
                    Constantes.BANO_ENTRADA_INVALIDA_MENSAGE);
        }

        componenteService.encontrarPorClave(banoDTO.getComponente().getId());

        try {
            Bano bano = mapper.toEntity(banoDTO);
            Long nuevoId = banoMapper.agregar(bano);
            logeador.info("Bano agregado exitosamente id: {}", nuevoId);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.BANO_DUPLICADO_MENSAGE + ": {}, codigoError:{}", banoDTO.getComponente().getId(),
                    BanoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(BanoError.DUPLICADO.getCodigoError(),
                    Constantes.BANO_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.BANO_AGREGAR_MENSAJE + ": {}, codigoError:{}", banoDTO.toString(),
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Agrega Lote nuevos Bano.
     *
     * @param banoDTOLote lista de Bano DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Bano tiene errores.
     * @throws RecursoDuplicadoException si el recurso bano ya existe.
     */
    public void agregarLote(List<BanoDTO> banoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() bano");

        //  Valida Entrada
        if (banoDTOLote.isEmpty()) {
            logeador.error(Constantes.BANO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                    BanoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(BanoError.REQUERIDO.getCodigoError(),
                    Constantes.BANO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Bano> banoLote = mapper.toEntityList(banoDTOLote);

            int registrosAgregados = banoMapper.agregarLote(banoLote);
            logeador.info("Lote Bano agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.BANO_DUPLICADO_MENSAGE + " codigoError:{}",
                    BanoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(BanoError.DUPLICADO.getCodigoError(),
                    Constantes.BANO_DUPLICADO_MENSAGE);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.BANO_VIOLACION_INTEGRIDAD_MENSAGE + " codigoError:{}",
                    BanoError.INTEGRIDAD_VIOLADA.getCodigoError());
            throw new EntradaInvalidadException(BanoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.BANO_VIOLACION_INTEGRIDAD_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.BANO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Bano existente.
     *
     * @param id      la clave de Bano a actualizar.
     * @param banoDTO el Bano DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Bano no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Bano tiene errores.
     */
    public void actualizar(Long id, BanoDTO banoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() bano");

        //  Valida Entrada
        if (id == null || banoDTO == null || banoDTO.getComponente() == null || banoDTO.getComponente().getId() == null) {
            logeador.error(Constantes.BANO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((banoDTO != null) ? banoDTO.toString() : null),
                    BanoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(BanoError.REQUERIDO.getCodigoError(),
                    Constantes.BANO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(banoDTO.getComponente().getId())) {
            logeador.error(Constantes.BANO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", banoDTO.toString(),
                    BanoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(BanoError.ID_INVALIDO.getCodigoError(),
                    Constantes.BANO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            this.encontrarPorClave(id); // Verifica si existe el recurso
            Bano bano = mapper.toEntity(banoDTO);
            int registrosActualizados = banoMapper.actualizar(bano);
            logeador.info("bano actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.BANO_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, banoDTO.toString(),
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza Lote de Bano existentes.
     *
     * @param banoDTOLote lista de Bano DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Bano tiene errores.
     */
    public void actualizarLote(List<BanoDTO> banoDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() bano");

        //  Valida Entrada
        if (banoDTOLote.isEmpty()) {
            logeador.error(Constantes.BANO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    BanoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(BanoError.REQUERIDO.getCodigoError(),
                    Constantes.BANO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Bano> banoLote = mapper.toEntityList(banoDTOLote);
            int registrosActualizados = banoMapper.actualizarLote(banoLote);
            logeador.info("Lote bano actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.BANO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Bano por Clave.
     *
     * @param id la clave de Bano a eliminar.
     * @throws RecursoNoEncontradoException si el Bano no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Bano esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() bano: {}", id);


        try {
            this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = banoMapper.eliminar(id);
            logeador.info("bano eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.BANO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(BanoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.BANO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException e) {
            logeador.error(Constantes.BANO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Bano por Clave.
     *
     * @param banoDTOLote lista de claves de Bano a eliminar.
     * @throws EntradaInvalidadException si la lista  Bano esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si Bano esta asociado a otro recurso
     */
    public void eliminarLote(List<BanoDTO> banoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (banoDTOLote.isEmpty()) {
            logeador.error(Constantes.BANO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                    BanoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(BanoError.REQUERIDO.getCodigoError(),
                    Constantes.BANO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = banoMapper.eliminarLote(mapper.toEntityList(banoDTOLote));
            logeador.info("Lote bano eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataIntegrityViolationException e) {
            logeador.error(Constantes.BANO_VIOLACION_INTEGRIDAD_MENSAGE);
            throw new RecursoEliminarException(BanoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                    Constantes.BANO_VIOLACION_INTEGRIDAD_MENSAGE, e);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.BANO_ELIMINAR_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Bano por Clave.
     *
     * @param id la clave Bano a encontrar.
     * @return el Bano DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Bano no es encontrado.
     */
    public BanoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            BanoDTO banoDTO = mapper.toDTO(banoMapper.encontrarPorClave(id));

            if (banoDTO != null) {
                logeador.info("bano encontrado por clave : {}", id);
            } else {
                logeador.info("bano clave:{} no encontrado codigoError:{}", id,
                        BanoError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(BanoError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.BANO_NO_ENCONTRADO_MENSAGE);
            }

            return banoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.BANO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Banos.
     *
     * @return una lista de todos Bano DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<BanoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<BanoDTO> banoDTOLista = mapper.toDTOList(banoMapper.obtenerTodos());
            logeador.info("banos obtenidos");
            return banoDTOLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.BANO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.BANO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}
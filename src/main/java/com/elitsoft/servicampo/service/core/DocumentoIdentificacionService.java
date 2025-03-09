package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.DocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.dto.core.TipoDocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.entity.DocumentoIdentificacion;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.DocumentoIdentificacionMapper;
import com.elitsoft.servicampo.mapstruct.DocumentoIdentificacionMapStruct;
import com.elitsoft.servicampo.service.error.DocumentoIdentificacionError;
import com.elitsoft.servicampo.service.error.TipoDocumentoIdentificacionError;
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
 * Clase de Servicio para la entidad DocumentoIdentificacion.
 */
@Service
public class DocumentoIdentificacionService {

    @Autowired
    private DocumentoIdentificacionMapper documentoIdentificacionMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoDocumentoIdentificacionService tipoDocumentoIdentificacionService;


    @Autowired
    private DocumentoIdentificacionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(DocumentoIdentificacionService.class); //Logback


    /**
     * Agrega un nuevo DocumentoIdentificacion.
     * @param documentoidentificacionDTO el DocumentoIdentificacion DTO.
     * @return el DocumentoIdentificacion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso DocumentoIdentificacion ya existe.
     */
    public DocumentoIdentificacionDTO agregar(DocumentoIdentificacionDTO documentoidentificacionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() DocumentoIdentificacion");

        //  Valida Entrada
        this.valiacionesEntrada(documentoidentificacionDTO);

        //Verifica Identificacion Duplicada.
        DocumentoIdentificacion documentoIdentificacionExiste =  encontrarPorIndentificacion(
                documentoidentificacionDTO.getNumero(),
                documentoidentificacionDTO.getDigitoVerificador());

        if (documentoIdentificacionExiste != null) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE + ": {} , {}",
                           documentoidentificacionDTO.getNumero(),
                           documentoidentificacionDTO.getDigitoVerificador());
            throw new RecursoDuplicadoException(DocumentoIdentificacionError.DUPLICADO.getCodigoError() ,
                                                Constantes.DOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
        }

        try {
            DocumentoIdentificacion documentoIdentificacion = mapper.toEntity(documentoidentificacionDTO);
            documentoIdentificacion = documentoIdentificacionMapper.agregar(documentoIdentificacion);
            documentoIdentificacion = documentoIdentificacionMapper.encontrarPorClave(documentoIdentificacion.getId());
            logeador.info("DocumentoIdentificacion agregado exitosamente id: {}", documentoIdentificacion.getId());
            return mapper.toDTO(documentoIdentificacion);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE + ": {}", documentoidentificacionDTO.getId());
            throw new RecursoDuplicadoException(Constantes.DOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_AGREGAR_MENSAJE + ": {}", documentoidentificacionDTO.toString(), e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos DocumentoIdentificacion.
     * @param documentoidentificacionLoteDTO lista de DocumentoIdentificacion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso documentoidentificacion ya existe.
     */
    public void agregarLote(List<DocumentoIdentificacionDTO> documentoidentificacionLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() documentoidentificacion");

        //  Valida Entrada
        if (documentoidentificacionLoteDTO.isEmpty()) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<DocumentoIdentificacion> documentoidentificacionLote = mapper.toEntityList(documentoidentificacionLoteDTO);

            int registrosAgregados =  documentoIdentificacionMapper.agregarLote(documentoidentificacionLote);
            logeador.info("Lote DocumentoIdentificacion agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(Constantes.DOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un DocumentoIdentificacion existente.
     * @param id la clave de DocumentoIdentificacion a actualizar.
     * @param documentoidentificacionDTO el DocumentoIdentificacion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DocumentoIdentificacion no es encontrado.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     */
    public void actualizar(Long id, DocumentoIdentificacionDTO documentoidentificacionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() documentoidentificacion");

        //  Valida Entrada
        if (id == null || documentoidentificacionDTO == null || documentoidentificacionDTO.getId() == null) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE + ": {}", ((documentoidentificacionDTO != null) ? documentoidentificacionDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(documentoidentificacionDTO.getId())) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  documentoidentificacionDTO.toString());
            throw new EntradaInvalidadException(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida Entrada
        this.valiacionesEntrada(documentoidentificacionDTO);

        /**
        DocumentoIdentificacion documentoIdentificacionExiste =  encontrarPorIndentificacion(
                documentoidentificacionDTO.getNumero(),
                documentoidentificacionDTO.getDigitoVerificador());

        if (documentoIdentificacionExiste != null) {
            throw new RecursoDuplicadoException(GeneralError.IDENTIFICACION_DUPLICADO.codigoError(),
                    Constantes.DOCUMENTOIDENTIFICACION_DUPLICADO_MENSAGE);
        }
         */

        try {
            DocumentoIdentificacionDTO documentoidentificacionDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            DocumentoIdentificacion documentoidentificacion = mapper.toEntity(documentoidentificacionDTO);
            documentoidentificacion.setId(id);
            int registrosActualizados = documentoIdentificacionMapper.actualizar(documentoidentificacion);
            logeador.info("documentoidentificacion actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE + ": id={} {}", id, documentoidentificacionDTO.toString(), e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de DocumentoIdentificacion existentes.
     * @param documentoidentificacionLoteDTO lista de DocumentoIdentificacion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     */
    public void actualizarLote(List<DocumentoIdentificacionDTO> documentoidentificacionLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() documentoidentificacion");

        //  Valida Entrada
        if (documentoidentificacionLoteDTO.isEmpty()) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<DocumentoIdentificacion> documentoidentificacionLote = mapper.toEntityList(documentoidentificacionLoteDTO);
            int registrosActualizados = documentoIdentificacionMapper.actualizarLote(documentoidentificacionLote);
            logeador.info("Lote documentoidentificacion actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina DocumentoIdentificacion por Clave.
     * @param id la clave de DocumentoIdentificacion a eliminar.
     * @throws RecursoNoEncontradoException si el DocumentoIdentificacion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() documentoidentificacion: {}", id);

        try {
            DocumentoIdentificacionDTO documentoidentificacionDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = documentoIdentificacionMapper.eliminar(id);
            logeador.info("documentoidentificacion eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote DocumentoIdentificacion por Clave.
     * @param idLote lista de claves de DocumentoIdentificacion a eliminar.
     * @throws EntradaInvalidadException si la lista  DocumentoIdentificacion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            int registrosEliminados = documentoIdentificacionMapper.eliminarLote(idLote);
            logeador.info("Lote documentoidentificacion eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un DocumentoIdentificacion por Clave.
     * @param id la clave DocumentoIdentificacion a encontrar.
     * @return el DocumentoIdentificacion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DocumentoIdentificacion no es encontrado.
     */
    public DocumentoIdentificacionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            DocumentoIdentificacionDTO documentoidentificacionDTO = mapper.toDTO(documentoIdentificacionMapper.encontrarPorClave(id));

            if (documentoidentificacionDTO != null) {
                logeador.info("documentoidentificacion encontrado por clave : {}", id);
            } else {
                logeador.info("documentoidentificacion clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.DOCUMENTOIDENTIFICACION_NO_ENCONTRADO_MENSAGE);
            }

            return documentoidentificacionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Encuentra un DocumentoIdentificacion por su numero y opcional digitoVerificador.
     * @param numero documento de identificacion.
     * @param digitoVerificador digito verificador.
     * @return el DocumentoIdentificacion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     */
    public DocumentoIdentificacion encontrarPorIndentificacion(String numero, Character digitoVerificador) throws BaseDatosException {
        logeador.debug("encontrarPorIndentificacion(): {} , {}", numero, digitoVerificador);

        try {
            return documentoIdentificacionMapper.encontrarPorIndentificacion(numero, digitoVerificador);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENCONTRAR_POR_IDENTIFICACION_MENSAGE + " {} , {}", numero, digitoVerificador, e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_ENCONTRAR_POR_IDENTIFICACION_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los DocumentoIdentificacions.
     * @return una lista de todos DocumentoIdentificacion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DocumentoIdentificacionDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<DocumentoIdentificacionDTO> documentoidentificacionLista = mapper.toDTOList(documentoIdentificacionMapper.obtenerTodos());
            logeador.info("documentoidentificacions obtenidos");
            return documentoidentificacionLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.DOCUMENTOIDENTIFICACION_OBTENER_TODOS_MENSAJE, e);
        }
    }

    /**
     * Verifica campoa requeridos
     * @param documentoIdentificacionDTO el DocumentoIdentificacion DTO.
     * @throws EntradaInvalidadException si el recurso Empleado ya existe.
     */
    public void valiacionesEntrada(DocumentoIdentificacionDTO documentoIdentificacionDTO) throws EntradaInvalidadException, RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("valiacionesEntrada()");


        //Valida Numero de identificacion
        if (documentoIdentificacionDTO == null || documentoIdentificacionDTO.getNumero() == null || documentoIdentificacionDTO.getNumero().isEmpty() ) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_CONTRASENA_MENSAGE );
            throw new EntradaInvalidadException(DocumentoIdentificacionError.NUMERO_REQUERIDO.getCodigoError(),
                                                Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_CONTRASENA_MENSAGE);
        }

        TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO = documentoIdentificacionDTO.getTipoDocumentoIdentificacion();


        //Valida Id Tipo Documento Identificacion
        if (tipoDocumentoIdentificacionDTO == null || tipoDocumentoIdentificacionDTO.getId() == null
                                                   || tipoDocumentoIdentificacionDTO.getId().toString().isEmpty()){
            logeador.error(Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_ID_MENSAGE );
            throw new EntradaInvalidadException(TipoDocumentoIdentificacionError.ID_REQUERIDO.getCodigoError(),
                    Constantes.TIPODOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_ID_MENSAGE);

        }

        //Verifica Existencia Tipo Documento Identificacion
        TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTOEncontrado =
                         tipoDocumentoIdentificacionService.encontrarPorClave(tipoDocumentoIdentificacionDTO.getId());

        //Valida Digito Verificador
        if (tipoDocumentoIdentificacionDTO.getId().equals(Constantes.TIPO_DOCUMENTO_INDENTIFICACION_RUT)
                                                && (documentoIdentificacionDTO.getDigitoVerificador () == null
                                                || documentoIdentificacionDTO.getDigitoVerificador() == ' ') ) {
            logeador.error(Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_CONTRASENA_MENSAGE );
            throw new EntradaInvalidadException(DocumentoIdentificacionError.DIGITO_VERIFICADOR_REQUERIDO.getCodigoError(),
                    Constantes.DOCUMENTOIDENTIFICACION_ENTRADA_INVALIDA_DIGITO_VERIFICADOR_MENSAGE);
        }

    }
}
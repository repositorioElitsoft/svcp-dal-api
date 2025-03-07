package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.DocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.entity.DocumentoIdentificacion;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.DocumentoIdentificacionMapper;
import com.elitsoft.servicampo.mapstruct.DocumentoIdentificacionMapStruct;
import com.elitsoft.servicampo.service.core.DocumentoIdentificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  DocumentoIdentificacion.
 */
@Component
public class DocumentoIdentificacionMobileService {

    @Autowired
    private DocumentoIdentificacionMapper documentoIdentificacionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DocumentoIdentificacionService documentoIdentificacionService; //Logica de Negocio del Core Service

    @Autowired
    private DocumentoIdentificacionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(DocumentoIdentificacionMobileService.class); //Logback


    /**
     * Agrega un nuevo DocumentoIdentificacion.
     * @param documentoidentificacionDTO el DocumentoIdentificacion DTO.
     * @return el DocumentoIdentificacion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso DocumentoIdentificacion ya existe.
     */
    public DocumentoIdentificacionDTO agregar(DocumentoIdentificacionDTO documentoidentificacionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() documentoidentificacion");

        return documentoIdentificacionService.agregar(documentoidentificacionDTO);
    }

    /**
     * Agrega Lote nuevos DocumentoIdentificacion.
     * @param documentoidentificacionLoteDTO lista de DocumentoIdentificacion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso DocumentoIdentificacion ya existe.
     */
    public void agregarLote(List<DocumentoIdentificacionDTO> documentoidentificacionLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() documentoidentificacion");

        documentoIdentificacionService.agregarLote(documentoidentificacionLoteDTO);
    }

    /**
     * Actualiza un DocumentoIdentificacion existente.
     * @param id la Clave de DocumentoIdentificacion a actualizar.
     * @param documentoidentificacionDTO el DocumentoIdentificacion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DocumentoIdentificacion no es encontrado.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     */
    public void actualizar(Long id, DocumentoIdentificacionDTO documentoidentificacionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() documentoidentificacion");

        documentoIdentificacionService.actualizar(id, documentoidentificacionDTO);
    }

    /**
     * Actualiza Lote de DocumentoIdentificacion existentes.
     * @param documentoidentificacionLoteDTO lista de DocumentoIdentificacion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DocumentoIdentificacion tiene errores.
     */
    public void actualizarLote(List<DocumentoIdentificacionDTO> documentoidentificacionLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() documentoidentificacion");

        documentoIdentificacionService.actualizarLote(documentoidentificacionLoteDTO);
    }

    /**
     * Elimina DocumentoIdentificacion por Clave.
     * @param id la clave de DocumentoIdentificacion a eliminar.
     * @throws RecursoNoEncontradoException si el DocumentoIdentificacion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() documentoidentificacion: {}", id);
        documentoIdentificacionService.eliminar(id);
    }

    /**
     * Elimina Lote DocumentoIdentificacion por Clave.
     * @param idLote lista de claves de DocumentoIdentificacion a eliminar.
     * @throws EntradaInvalidadException si la lista  DocumentoIdentificacion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        documentoIdentificacionService.eliminarLote(idLote);
    }

    /**
     * Encuentra un DocumentoIdentificacion por Clave.
     * @param id la clave DocumentoIdentificacion a encontrar.
     * @return el DocumentoIdentificacion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DocumentoIdentificacion no es encontrado.
     */
    public DocumentoIdentificacionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return documentoIdentificacionService.encontrarPorClave(id);
    }

    /**
     * Encuentra un DocumentoIdentificacion por su numero y opcional digitoVerificador.
     * @param numero documento de identificacion.
     * @param digitoVerificador digito verificador.
     * @return el DocumentoIdentificacion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws DocumentoIdentificacionNoEncontradoException si DocumentoIdentificacion no es encontrado.
     */
    public DocumentoIdentificacion encontrarPorIndentificacion(String numero, Character digitoVerificador) throws BaseDatosException, DocumentoIdentificacionNoEncontradoException {
        logeador.debug("encontrarPorIndentificacion(): {} , {}", numero, digitoVerificador);
        return documentoIdentificacionService.encontrarPorIndentificacion(numero,digitoVerificador );
    }

    /**
     * Obtiene todos los DocumentoIdentificacions.
     * @return lista de todos DocumentoIdentificacion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DocumentoIdentificacionDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return documentoIdentificacionService.obtenerTodos();
    }
}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.DocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.dto.core.EmpleadoDTO;
import com.elitsoft.servicampo.domain.entity.Empleado;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.DocumentoIdentificacionMapper;
import com.elitsoft.servicampo.mapper.EmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.DocumentoIdentificacionMapStruct;
import com.elitsoft.servicampo.mapstruct.EmpleadoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.utils.ErroresNegocio;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Empleado.
 */
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoMapper empleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DocumentoIdentificacionMapper documentoIdentificacionMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DocumentoIdentificacionService documentoIdentificacionService;


    @Autowired
    private EmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    @Autowired
    private DocumentoIdentificacionMapStruct documentoIdentificacionMapStruct; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EmpleadoService.class); //Logback


    /**
     * Agrega un nuevo Empleado.
     * @param empleadoDTO el Empleado DTO.
     * @return el Empleado DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Empleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso Empleado ya existe.
     */
    @Transactional
    public EmpleadoDTO agregar(EmpleadoDTO empleadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() Empleado");

        this.valiacionesEntrada(empleadoDTO, true); //  Valida Entrada

        // Agrega el Documento de Identificacion
        DocumentoIdentificacionDTO documentoIdentificacionDTO = documentoIdentificacionService.agregar(empleadoDTO.getDocumentoIdentificacion());

        Empleado empleadoCorreo =  this.encontrarPorCorreo(empleadoDTO.getEmail()); //Verifica Existencia de correo.

        this.valiacionesCorreo(empleadoDTO); //Valida existencia de correo

        try {

            // Asocia el documento de Identificacion creado al empleado
            empleadoDTO.setDocumentoIdentificacion(documentoIdentificacionDTO);

            //Encripta el la clave de usuario
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            empleadoDTO.setContrasena(passwordEncoder.encode(empleadoDTO.getContrasena()));

            Empleado empleado = mapper.toEntity(empleadoDTO);
            empleado = empleadoMapper.agregar(empleado);

            EmpleadoDTO empleadoDTOEncontrado = this.encontrarPorClave(empleado.getId()); // Busca el Empleado creado

             empleado = mapper.toEntity(empleadoDTOEncontrado);

            logeador.info("Empleado agregado exitosamente id: {}", empleado.getId());
            return mapper.toDTO(empleado);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.EMPLEADO_DUPLICADO_MENSAGE + ": {}", empleadoDTO.getId());
            throw new RecursoDuplicadoException(Constantes.EMPLEADO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException | RecursoNoEncontradoException e) {
            logeador.error(Constantes.EMPLEADO_AGREGAR_MENSAJE + ": {}", empleadoDTO.toString(), e);
            throw new BaseDatosException(Constantes.EMPLEADO_AGREGAR_MENSAJE, e);
    }
    }

    /**
     * Actualiza un Empleado existente.
     * @param id la clave de Empleado a actualizar.
     * @param empleadoDTO el Empleado DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Empleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada Empleado tiene errores.
     */
    public void actualizar(Long id, EmpleadoDTO empleadoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() empleado");

        //  Valida Entrada
        if (id == null || empleadoDTO == null || empleadoDTO.getId() == null) {
            logeador.error(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((empleadoDTO != null) ? empleadoDTO.toString() : null  ));
            throw new EntradaInvalidadException(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Valida Documento
        documentoIdentificacionService.valiacionesEntrada(empleadoDTO.getDocumentoIdentificacion());

        //Valida Entrada
        this.valiacionesEntrada(empleadoDTO, false);

        //  Valida id
        if (!id.equals(empleadoDTO.getId())) {
            logeador.error(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}, {}", id,  empleadoDTO.toString());
            throw new EntradaInvalidadException(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            EmpleadoDTO empleadoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Empleado empleado = mapper.toEntity(empleadoDTO);
            empleado.setId(id);
            int registrosActualizados = empleadoMapper.actualizar(empleado);
            logeador.info("empleado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.EMPLEADO_ACTUALIZAR_MENSAJE + ": id={} {}", id, empleadoDTO.toString(), e);
            throw new BaseDatosException(Constantes.EMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza la clave de Empleado existente.
     * @param id la clave de Empleado a actualizar.
     * @param contrasena La clave Empleado a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Empleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada Empleado tiene errores.
     */
    public void actualizarClave(Long id, String contrasena) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizarClave() empleado");

        //  Valida Entrada
        if (id == null || contrasena.isEmpty()) {
            logeador.error(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((id != null) ? id : null  ));
            throw new EntradaInvalidadException(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {

            EmpleadoDTO empleadoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso

            //Encripta el la clave de usuario
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            contrasena = passwordEncoder.encode(contrasena);

            int registrosActualizados = empleadoMapper.actualizarClave (id,contrasena);
            logeador.info("empleado clave actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.EMPLEADO_ACTUALIZAR_MENSAJE + ": id={}", id, e);
            throw new BaseDatosException(Constantes.EMPLEADO_ACTUALIZAR_MENSAJE, e);
        }
    }


    /**
     * Elimina Empleado por Clave.
     * @param id la clave de Empleado a eliminar.
     * @throws RecursoNoEncontradoException si el Empleado no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() empleado: {}", id);

        try {
            EmpleadoDTO empleadoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = empleadoMapper.eliminar(id);
            logeador.info("empleado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(Constantes.EMPLEADO_ELIMINAR_MENSAJE, e);
        }
    }


    /**
     * Encuentra un Empleado por Clave.
     * @param id la clave Empleado a encontrar.
     * @return el Empleado DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Empleado no es encontrado.
     */
    public EmpleadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {

            EmpleadoDTO empleadoDTO = mapper.toDTO(empleadoMapper.encontrarPorClave(id));
            if (empleadoDTO != null) {
                logeador.info("empleado encontrado por clave : {}", id);
            } else {
                logeador.info("empleado clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(Constantes.EMPLEADO_NO_ENCONTRADO_MENSAGE);
            }

            return empleadoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(Constantes.EMPLEADO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }


    /**
     * Obtiene todos los Empleados.
     * @return una lista de todos Empleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EmpleadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<EmpleadoDTO> empleadoLista = mapper.toDTOList(empleadoMapper.obtenerTodos());
            logeador.info("empleados obtenidos");
            return empleadoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(Constantes.EMPLEADO_OBTENER_TODOS_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Empleado por correo.
     * @param email correo de Empleado a encontrar.
     * @return el Empleado  encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     */
    public Empleado encontrarPorCorreo(String email) throws BaseDatosException {
        logeador.debug("encontrarPorCorreo(): {}", email);

        try {
            return empleadoMapper.encontrarPorCorreo (email);
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_ENCONTRAR_POR_CORREO_MENSAGE + " {}", email, e);
            throw new BaseDatosException(Constantes.EMPLEADO_ENCONTRAR_POR_CORREO_MENSAGE, e);
        }
    }


    /**
     * Verifica la existencia de un correo
     * @param empleadoDTO el Empleado DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoDuplicadoException si el recurso Empleado ya existe.
     */
    public void valiacionesCorreo(EmpleadoDTO empleadoDTO) throws BaseDatosException, RecursoDuplicadoException {
        logeador.debug("valiacionesCorreo() " + empleadoDTO.getEmail());

        Empleado empleado =  this.encontrarPorCorreo(empleadoDTO.getEmail()); //Verifica Existencia de correo.

        if (empleado != null){
            logeador.error(Constantes.EMPLEADO_CORREO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(ErroresNegocio.EMPLEADO_CORREO_DUPLICADO.getCodigoError(),
                    Constantes.EMPLEADO_CORREO_DUPLICADO_MENSAGE);
        }
    }

    /**
     * Verifica campoa requeridos
     * @param empleadoDTO el Empleado DTO.
     * @param  contrasena indica si debe evaluar o entradas de contransena
     * @throws EntradaInvalidadException si el recurso Empleado ya existe.
     */
    public void valiacionesEntrada(EmpleadoDTO empleadoDTO, boolean contrasena) throws  EntradaInvalidadException {
        logeador.debug("valiacionesEntrada()");

        //  Valida Entrada
        if (empleadoDTO == null ) {
            logeador.error(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Solo para agregar clave
        if (contrasena) {
            if (empleadoDTO.getContrasena() == null || empleadoDTO.getContrasena().isEmpty()) {
                logeador.error(Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
                throw new EntradaInvalidadException(ErroresNegocio.EMPLEADO_CONTRASENA_REQUERIDO.getCodigoError(),
                        Constantes.EMPLEADO_ENTRADA_INVALIDA_MENSAGE);
            }
        }

        if (empleadoDTO.getEmail() == null || empleadoDTO.getEmail().isEmpty()  ) {
            logeador.error(Constantes.EMPLEADO_ENTRADA_INVALIDA_CORRE0_MENSAGE);
            throw new EntradaInvalidadException(ErroresNegocio.EMPLEADO_CORREO_REQUERIDO.getCodigoError(),
                                                Constantes.EMPLEADO_ENTRADA_INVALIDA_CORRE0_MENSAGE);
        }
    }


}
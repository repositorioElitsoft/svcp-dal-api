package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ServicioTrabajoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ServicioTrabajoMapper;
import com.elitsoft.servicampo.mapstruct.ServicioTrabajoMapStruct;
import com.elitsoft.servicampo.service.core.ServicioTrabajoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  ServicioTrabajo.
 */
@Component
public class ServicioTrabajoMobileService {

    @Autowired
    private ServicioTrabajoMapper serviciotrabajoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ServicioTrabajoService serviciotrabajoService; //Logica de Negocio del Core Service

    @Autowired
    private ServicioTrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ServicioTrabajoMobileService.class); //Logback

    /**
     * Agrega un nuevo ServicioTrabajo.
     * @param serviciotrabajoDTO el ServicioTrabajo DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso ServicioTrabajo ya existe.
     */
    public void agregar(ServicioTrabajoDTO serviciotrabajoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() serviciotrabajo");

        serviciotrabajoService.agregar(serviciotrabajoDTO);
    }

    /**
     * Agrega un nuevo ServicioTrabajo.
     * @param serviciotrabajoDTO el ServicioTrabajo DTO.
     * @return el ServicioTrabajo DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso ServicioTrabajo ya existe.
     */
//    public ServicioTrabajoDTO agregar(ServicioTrabajoDTO serviciotrabajoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
//        logeador.debug("agregar() serviciotrabajo");
//
//        return serviciotrabajoService.agregar(serviciotrabajoDTO);
//    }

    /**
     * Agrega Lote nuevos ServicioTrabajo.
     * @param serviciotrabajoDTOLote lista de ServicioTrabajo DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso ServicioTrabajo ya existe.
     */
    public void agregarLote(List<ServicioTrabajoDTO> serviciotrabajoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() serviciotrabajo");

        serviciotrabajoService.agregarLote(serviciotrabajoDTOLote);
    }

    /**
     * Actualiza un ServicioTrabajo existente.
     * @param servicioId La clave de Servicio a eliminar.
     * @param trabajoId La clave de Trabajo a eliminar.
     * @param serviciotrabajoDTO el ServicioTrabajo DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ServicioTrabajo no es encontrado.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     */
    public void actualizar(Long servicioId, Long trabajoId, ServicioTrabajoDTO serviciotrabajoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() serviciotrabajo");

        serviciotrabajoService.actualizar(servicioId, trabajoId, serviciotrabajoDTO);
    }

    /**
     * Actualiza Lote de ServicioTrabajo existentes.
     * @param serviciotrabajoDTOLote lista de ServicioTrabajo DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     */
    public void actualizarLote(List<ServicioTrabajoDTO> serviciotrabajoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() serviciotrabajo");

        serviciotrabajoService.actualizarLote(serviciotrabajoDTOLote);
    }

    /**
     * Elimina ServicioTrabajo por Clave.
     * @param servicioId La clave de Servicio a eliminar.
     * @param trabajoId La clave de Trabajo a eliminar.
     * @throws RecursoNoEncontradoException si el ServicioTrabajo no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long servicioId, Long trabajoId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() serviciotrabajo: {}, {}", servicioId, trabajoId);
        serviciotrabajoService.eliminar(servicioId,trabajoId);
    }

    /**
     * Elimina Lote ServicioTrabajo por Clave.
     * @param serviciotrabajoDTOLote lista de claves de ServicioTrabajo a eliminar.
     * @throws EntradaInvalidadException si la lista  ServicioTrabajo esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ServicioTrabajoDTO> serviciotrabajoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        serviciotrabajoService.eliminarLote(serviciotrabajoDTOLote);
    }

    /**
     * Encuentra un ServicioTrabajo por Clave.
     * @param servicioId La clave de Servicio a eliminar.
     * @param trabajoId La clave de Trabajo a eliminar.
     * @return el ServicioTrabajo DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ServicioTrabajo no es encontrado.
     */
    public ServicioTrabajoDTO encontrarPorClave(Long servicioId, Long trabajoId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}, {}", servicioId, trabajoId);
        return serviciotrabajoService.encontrarPorClave(servicioId,trabajoId);
    }

    /**
     * Obtiene todos los ServicioTrabajos.
     * @return lista de todos ServicioTrabajo DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ServicioTrabajoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return serviciotrabajoService.obtenerTodos();
    }
}
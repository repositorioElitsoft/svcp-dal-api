package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.domain.dto.core.DireccionDTO;
import com.elitsoft.servicampo.domain.entity.Cliente;
import com.elitsoft.servicampo.domain.entity.Contacto;
import com.elitsoft.servicampo.domain.entity.ContactoDireccion;
import com.elitsoft.servicampo.domain.entity.Direccion;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ContactoDireccionMapper;
import com.elitsoft.servicampo.mapper.DireccionMapper;
import com.elitsoft.servicampo.mapstruct.ClienteMapStruct;
import com.elitsoft.servicampo.mapstruct.ContactoMapStruct;
import com.elitsoft.servicampo.mapstruct.DireccionMapStruct;
import com.elitsoft.servicampo.service.error.ClienteError;
import com.elitsoft.servicampo.service.error.ContactoError;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.DireccionError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Direccion.
 */
@Service
public class DireccionService {

    @Autowired
    private DireccionMapper direccionMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContactoDireccionMapper contactoDireccionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContactoService contactoService;

    @Autowired
    private DireccionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    @Autowired
    private ContactoMapStruct contactoMapStruct; // MapStruct Mapper (ToEntity(), ToDTO())

    @Autowired
    private ClienteMapStruct clienteMapStruct; // MapStruct Mapper (ToEntity(), ToDTO())


    private static final Logger logeador = LoggerFactory.getLogger(DireccionService.class); //Logback


    /**
     * Agrega un nuevo Direccion.
     * @param direccionDTO el Direccion DTO.
     * @return el Direccion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    @Transactional
    public DireccionDTO agregar(DireccionDTO direccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Direccion");

        //  Valida Entrada
        if (direccionDTO == null) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Direccion direccion = mapper.toEntity(direccionDTO);
            Direccion direccionNuevo = direccionMapper.agregar(direccion);
            Direccion direccionEncontrado = direccionMapper.encontrarPorClave(direccion.getCliente().getId(), direccionNuevo.getId());
            logeador.info("Direccion agregado exitosamente cliente: {}, id: {}", direccion.getCliente().getId(), direccionNuevo.getId());
            return mapper.toDTO(direccionEncontrado);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCION_DUPLICADO_MENSAGE + ": {}, codigoError:{}", direccionDTO.getId(),
                           DireccionError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(DireccionError.DUPLICADO.getCodigoError(),
                                                Constantes.DIRECCION_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_AGREGAR_MENSAJE + ": {}, codigoError:{}", direccionDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega un nuevo Contacto a una Direccion existente.
     * @param  id clave de Direccion a eliminar.
     * @param clienteId La clave de Cliente a eliminar.
     * @param contactoDTO el Contacto DTO.
     * @return el Direccion DTO existente con Contacto DTO agregado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    @Transactional
    public ContactoDTO agregarContacto(Long id, Long clienteId, ContactoDTO contactoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregarContacto() Direccion Contacto");

        //  Valida Entrada Direccion
        if (id == null ) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida Entrada Cliente
        if (clienteId == null ) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    ClienteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ClienteError.REQUERIDO.getCodigoError(),
                    Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }


        try {

            Cliente cliente = new Cliente();
            cliente.setId(clienteId);

            Direccion direccion = new Direccion();
            direccion.setId(id);

            //1. Agregar Contacto
            ContactoDTO contactoDTONuevo =  contactoService.agregar(contactoDTO);
            ContactoDireccion contactoDireccion = ContactoDireccion.builder()
                                                                .contacto(contactoMapStruct.toEntity(contactoDTONuevo))
                                                                .cliente(cliente)
                                                                .direccion(direccion)
                                                                .build();
            //2. Agregar relacion Contacto<-->Direccion
            contactoDireccionMapper.agregar(contactoDireccion);
            logeador.info("Contacto agregado exitosamente a Direccion y Cliente, contacto:{}, cliente: {}, direccion: {}", contactoDTONuevo.getId(),
                                            contactoDireccion.getCliente().getId(), contactoDireccion.getDireccion().getId());
            return contactoDTONuevo;
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCION_DUPLICADO_MENSAGE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{}", contactoDTO.getNombre(),
                        clienteId, id, DireccionError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(DireccionError.DUPLICADO.getCodigoError(),
                    Constantes.DIRECCION_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_AGREGAR_MENSAJE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{}", contactoDTO.getNombre(),
                    clienteId, id, GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Direccion.
     * @param direccionDTOLote lista de Direccion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso direccion ya existe.
     */
    public void agregarLote(List<DireccionDTO> direccionDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() direccion");

        //  Valida Entrada
        if (direccionDTOLote.isEmpty()) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Direccion> direccionLote = mapper.toEntityList(direccionDTOLote);

            int registrosAgregados =  direccionMapper.agregarLote(direccionLote);
            logeador.info("Lote Direccion agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCION_DUPLICADO_MENSAGE + " codigoError:{}",
                           DireccionError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(DireccionError.DUPLICADO.getCodigoError(),
                                                Constantes.DIRECCION_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.DIRECCION_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Direccion existente.
     * @param clienteId La clave de Cliente a actualizar.
     * @param id la clave de Direccion a actualizar.
     * @param direccionDTO el Direccion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizar(Long clienteId, Long id, DireccionDTO direccionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() direccion");

        //  Valida Entrada
        if (id == null || direccionDTO == null || direccionDTO.getId() == null || direccionDTO.getId() .toString().isEmpty() || direccionDTO.getCliente().getId().toString().isEmpty() ) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((direccionDTO != null) ? direccionDTO.toString() : null  ),
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(direccionDTO.getId())) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {} , codigoError:{}",  direccionDTO.toString(),
                           DireccionError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.ID_INVALIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida clienteId
        if (!clienteId.equals(direccionDTO.getCliente().getId())) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {} , codigoError:{}",  direccionDTO.toString(),
                    ClienteError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ClienteError.ID_INVALIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            DireccionDTO direccionDTOEncontrado = this.encontrarPorClave(direccionDTO.getCliente().getId(), id); // Verifica si existe el recurso
            Direccion direccion = mapper.toEntity(direccionDTO);
            direccion.setId(id);
            int registrosActualizados = direccionMapper.actualizar(direccion);
            logeador.info("direccion actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ACTUALIZAR_MENSAJE + ": id={}, {}, codigoError:{} ", id, direccionDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError() , e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Contact existente asociado a Direccion
     * @param clienteId La clave de Cliente a actualizar.
     * @param id la clave de Direccion a actualizar.
     * @param contactoId la clave de Contacto a actualizar.
     * @param contactoDTO el Contacto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    @Transactional
    public void actualizarContacto(Long clienteId, Long id, Long contactoId, ContactoDTO contactoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizarContacto() direccion");

        //  Valida Entrada
        if (id == null || id.toString().isEmpty()  || clienteId == null || clienteId.toString().isEmpty() || contactoDTO == null ) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((contactoDTO != null) ? contactoDTO.toString() : null  ),
                    DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }


        try {

            DireccionDTO direccionDTOEncontrado = this.encontrarPorClave(clienteId, id); // Verifica si existe la direccione

            Cliente cliente = new Cliente();
            cliente.setId(clienteId);

            Direccion direccion = new Direccion();
            direccion.setId(id);

            //1. Actualiza Contacto
            contactoService.actualizar(contactoId,contactoDTO);
            ContactoDireccion contactoDireccion = ContactoDireccion.builder()
                    .contacto(contactoMapStruct.toEntity(contactoDTO))
                    .cliente(cliente)
                    .direccion(direccion)
                    .build();

            //2. Actualiza relacion Contacto<-->Direccion
            contactoDireccionMapper.actualizar(contactoDireccion);

            logeador.info("direccion actualizado exitosamente: contacto:{}, cliente: {}, direccion: {}", contactoDTO.getId(),
                          clienteId, id);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ACTUALIZAR_MENSAJE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{} ", contactoDTO.getId(),
                    clienteId, id, GeneralError.ERROR_INTERNO.getCodigoError() , e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }


   /**
     * Actualiza Lote de Direccion existentes.
     * @param direccionDTOLote lista de Direccion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizarLote(List<DireccionDTO> direccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() direccion");

        //  Valida Entrada
        if (direccionDTOLote.isEmpty()) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Direccion> direccionLote = mapper.toEntityList(direccionDTOLote);
            int registrosActualizados = direccionMapper.actualizarLote(direccionLote);
            logeador.info("Lote direccion actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Direccion por Clave.
     * @param  id clave de Direccion a eliminar.
     * @param clienteId La clave de Cliente a eliminar.
     * @throws RecursoNoEncontradoException si el Direccion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id, Long clienteId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() direccion: {}, {}", id, clienteId  );

        //Verifica integridad referencial
//        this.verificarIntegridadEliminar(id);

        try {
            DireccionDTO direccionDTO = this.encontrarPorClave(clienteId, id); // Verifica si existe
            int registrosEliminados = direccionMapper.eliminar(clienteId, id);
            logeador.info("direccion eliminado: {}, {} registros eliminados: {}", clienteId, id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ELIMINAR_MENSAJE + ": cliente: {}, id: {}, codigoError:{}", clienteId, id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Contacto asociado a Direccion por Clave.
     * @param  id clave de Direccion a eliminar.
     * @param clienteId La clave de Cliente a eliminar.
     * @param contactoId La clave de Contacto a eliminar.
     * @throws RecursoNoEncontradoException si el Direccion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    @Transactional
    public void eliminarContacto(Long id, Long clienteId, Long contactoId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminarContacto() direccion contacto: {}, {}, {}}", id, clienteId,  contactoId );

        //Verifica integridad referencial
//        this.verificarIntegridadEliminar(id);

        //  Valida Entrada
        if (id == null || id.toString().isEmpty()  || clienteId == null || clienteId.toString().isEmpty() || contactoId == null ) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": codigoError:{}",
                    DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            Contacto contacto = new Contacto();
            contacto.setId(contactoId);

            Direccion direccion = new Direccion();
            direccion.setId(id);

            Cliente cliente = new Cliente();
            cliente.setId(clienteId);

            ContactoDireccion contactoDireccion = ContactoDireccion.builder()
                                                                    .contacto(contacto)
                                                                    .cliente(cliente)
                                                                    .direccion(direccion).build();
            //1. Elimina relacion Contacto<-->Direccion
            contactoDireccionMapper.eliminar(contactoDireccion);

            //2. Elimina Contacto
            contactoService.eliminar(contactoId);
            logeador.info("contacto / direccion eliminado: contacto:{}, cliente: {}, direccion: {}", contactoId, clienteId, id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ELIMINAR_MENSAJE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{}",
                    contactoId, clienteId, id, GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Direccion por Clave.
     * @param direccionDTOLote lista de claves de Direccion a eliminar.
     * @throws EntradaInvalidadException si la lista  Direccion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<DireccionDTO> direccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (direccionDTOLote.isEmpty()) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
//        for (Long id : idLote) {
//            this.verificarIntegridadEliminar(id);
//        }

        try {
            int registrosEliminados = direccionMapper.eliminarLote(mapper.toEntityList(direccionDTOLote));
            logeador.info("Lote direccion eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ELIMINAR_MENSAJE + " codigoError:{}", GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.DIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Direccion por Clave.
     * @param clientId La clave de Cliente a encontrar.
     * @param id La clave de Direccion a encontrar.
     * @return el Direccion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     */
    public DireccionDTO encontrarPorClave(Long clientId, Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}, {}", clientId, id);

        try {
            DireccionDTO direccionDTO = mapper.toDTO(direccionMapper.encontrarPorClave(clientId,id));

            if (direccionDTO != null) {
                logeador.info("direccion encontrado por clave cliente: {}, id: {}", clientId, id );
            } else {
                logeador.info("direccion clave:{}, {} no encontrado codigoError:{}", clientId, id,
                              DireccionError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(DireccionError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.DIRECCION_NO_ENCONTRADO_MENSAGE);
            }

            return direccionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE + " cliente: {}, id: {}, codigoError:{} ", clientId, id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Encuentra un Direccion en la base de datos por su clave con Lista de Contacto
     * @param clientId La clave de Cliente a encontrar.
     * @param id La clave de Direccion a encontrar.
     * @return el Direccion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     */
    public DireccionDTO encontrarPorClaveConContactos(Long clientId, Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClaveConContactos(): {}, {}", clientId, id);

        try {
            DireccionDTO direccionDTO = mapper.toDTO(direccionMapper.encontrarPorClaveConContactos(clientId,id));

            if (direccionDTO != null) {
                logeador.info("direccion encontrado por clave cliente: {}, id: {}", clientId, id );
            } else {
                logeador.info("direccion clave:{}, {} no encontrado codigoError:{}", clientId, id,
                        DireccionError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(DireccionError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.DIRECCION_NO_ENCONTRADO_MENSAGE);
            }

            return direccionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE + " cliente: {}, id: {}, codigoError:{} ", clientId, id,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }


    /**
     * Obtiene todos los Direccion desde la base de datos filtrado por Cliente
     * @param clientId clave Cliente a filtrar
     * @return una lista de todos Direccion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DireccionDTO> obtenerTodosPorCliente(Long clientId) throws BaseDatosException {
        logeador.debug("obtenerTodosPorCliente() {}",clientId);

        try {
            List<DireccionDTO> direccionLista = mapper.toDTOList(direccionMapper.obtenerTodosPorCliente(clientId));
            logeador.info("direccions por cliente obtenidos");
            return direccionLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_OBTENER_TODOS_MENSAJE, e);
        }
    }

    /**
     * Obtiene todos los Direccion desde la base de datos filtrado por Cliente con Lista de Contacto
     * @param clientId clave Cliente a filtrar
     * @return una lista de todos Direccion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DireccionDTO> obtenerTodosPorClienteConContactos(Long clientId) throws BaseDatosException {
        logeador.debug("obtenerTodosPorClienteConContactos()");

        try {
            List<DireccionDTO> direccionLista = mapper.toDTOList(direccionMapper.obtenerTodosPorClienteConContactos(clientId));
            logeador.info("direccions por cliente con contactos obtenidos");
            return direccionLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_OBTENER_TODOS_MENSAJE, e);
        }
    }

}
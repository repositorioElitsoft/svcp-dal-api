package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ClienteDTO;
import com.elitsoft.servicampo.domain.entity.Cliente;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClienteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ClienteError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Cliente.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteMapper clienteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ClienteService.class); //Logback


    /**
     * Agrega un nuevo Cliente.
     * @param clienteDTO el Cliente DTO.
     * @return el Cliente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso Cliente ya existe.
     */
    public ClienteDTO agregar(ClienteDTO clienteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() Cliente");

        //  Valida Entrada
        if (clienteDTO == null) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ClienteError.REQUERIDO.getCodigoError(),
                                                Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Cliente cliente = mapper.toEntity(clienteDTO);
            cliente = clienteMapper.agregar(cliente);
            logeador.info("Cliente agregado exitosamente id: {}", cliente.getId());
            return this.encontrarPorClave(cliente.getId());
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CLIENTE_DUPLICADO_MENSAGE + ": {}", clienteDTO.getId());
            throw new RecursoDuplicadoException(ClienteError.DUPLICADO.getCodigoError(),
                                                Constantes.CLIENTE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CLIENTE_AGREGAR_MENSAJE + ": {}", clienteDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CLIENTE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Cliente.
     * @param clienteDTOLote lista de Cliente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso cliente ya existe.
     */
    public void agregarLote(List<ClienteDTO> clienteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() cliente");

        //  Valida Entrada
        if (clienteDTOLote.isEmpty()) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ClienteError.REQUERIDO.getCodigoError(),
                                                Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Cliente> clienteLote = mapper.toEntityList(clienteDTOLote);

            int registrosAgregados =  clienteMapper.agregarLote(clienteLote);
            logeador.info("Lote Cliente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.CLIENTE_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(ClienteError.DUPLICADO.getCodigoError(),
                                                Constantes.CLIENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLIENTE_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CLIENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Cliente existente.
     * @param id la clave de Cliente a actualizar.
     * @param clienteDTO el Cliente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Cliente no es encontrado.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     */
    public void actualizar(Long id, ClienteDTO clienteDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() cliente");

        //  Valida Entrada
        if (id == null || clienteDTO == null || clienteDTO.getId() == null) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}", ((clienteDTO != null) ? clienteDTO.toString() : null  ));
            throw new EntradaInvalidadException(ClienteError.REQUERIDO.getCodigoError(),
                                                Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(clienteDTO.getId())) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE + ": {}",  clienteDTO.toString());
            throw new EntradaInvalidadException(ClienteError.ID_INVALIDO.getCodigoError(),
                                                Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ClienteDTO clienteDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Cliente cliente = mapper.toEntity(clienteDTO);
            cliente.setId(id);
            int registrosActualizados = clienteMapper.actualizar(cliente);
            logeador.info("cliente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLIENTE_ACTUALIZAR_MENSAJE + ": id={} {}", id, clienteDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Cliente existentes.
     * @param clienteDTOLote lista de Cliente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     */
    public void actualizarLote(List<ClienteDTO> clienteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() cliente");

        //  Valida Entrada
        if (clienteDTOLote.isEmpty()) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ClienteError.REQUERIDO.getCodigoError(),
                                                Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Cliente> clienteLote = mapper.toEntityList(clienteDTOLote);
            int registrosActualizados = clienteMapper.actualizarLote(clienteLote);
            logeador.info("Lote cliente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLIENTE_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CLIENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Cliente por Clave.
     * @param id la clave de Cliente a eliminar.
     * @throws RecursoNoEncontradoException si el Cliente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() cliente: {}", id);

        //Verifica integridad referencial
//        this.verificarIntegridadEliminar(id);

        try {
            ClienteDTO clienteDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = clienteMapper.eliminar(id);
            logeador.info("cliente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLIENTE_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Cliente por Clave.
     * @param idLote lista de claves de Cliente a eliminar.
     * @throws EntradaInvalidadException si la lista  Cliente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ClienteError.REQUERIDO.getCodigoError(),
                                                Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
//        for (Long id : idLote) {
//            this.verificarIntegridadEliminar(id);
//        }

        try {
            int registrosEliminados = clienteMapper.eliminarLote(idLote);
            logeador.info("Lote cliente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CLIENTE_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CLIENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Cliente por Clave.
     * @param id la clave Cliente a encontrar.
     * @return el Cliente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Cliente no es encontrado.
     */
    public ClienteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ClienteDTO clienteDTO = mapper.toDTO(clienteMapper.encontrarPorClave(id));

            if (clienteDTO != null) {
                logeador.info("cliente encontrado por clave : {}", id);
            } else {
                logeador.info("cliente clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(ClienteError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.CLIENTE_NO_ENCONTRADO_MENSAGE);
            }

            return clienteDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CLIENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Clientes.
     * @return una lista de todos Cliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ClienteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ClienteDTO> clienteLista = mapper.toDTOList(clienteMapper.obtenerTodos());
            logeador.info("clientes obtenidos");
            return clienteLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLIENTE_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CLIENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de Cliente
     * @param id la clave Cliente a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
//    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
//        logeador.debug("verificarIntegridadEliminar() cliente: {}", id);
//
//        boolean entityRelacionadoPorCliente = false;
//
//        try {
//            entityRelacionadoPorCliente = this.entityRelacionadoPorCliente(id);
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.CLIENTE_ELIMINAR_MENSAJE + ": {}", id, e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.CLIENTE_ELIMINAR_MENSAJE, e);
//        }
//
//        //Verifca la integridad con sectores
//        if (entityRelacionadoPorCliente) {
//            throw new RecursoEliminarException(ClienteError.INTEGRIDAD_VIOLADA.getCodigoError(),
//                                               Constantes.CLIENTE_VIOLACION_INTEGRIDAD_MENSAGE);
//        }
//
//    }

    /**
     * Buscar Cliente que tengan EntityRelacionado.
     * @param id la clave Cliente a encontrar.
     * @return boolean Cliente tiene o no registros asociados
     * @throws BaseDatosException
     */
//    public boolean entityRelacionadoPorCliente(Long id) throws  BaseDatosException {
//        logeador.debug("entityRelacionadoPorCliente() cliente: {}", id);
//
//        try {
//            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorCliente(id); // Verifica si tiene EntityRelacionado  asociados
//            if (!entitys.isEmpty()) {
//                logeador.info("cliente  tiene #EntityRelacionado# asociados");
//                return true;
//            } else{
//                logeador.info("cliente no tiene #EntityRelacionado# asociados");
//                return false;
//            }
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.CLIENTE_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}", id, e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.CLIENTE_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
//        }
//    }
}
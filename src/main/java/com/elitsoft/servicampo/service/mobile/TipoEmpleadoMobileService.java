package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.TipoEmpleadoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.TipoEmpleadoMapStruct;
import com.elitsoft.servicampo.service.core.TipoEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoEmpleado.
 */
@Component
public class TipoEmpleadoMobileService {

    @Autowired
    private TipoEmpleadoMapper tipoempleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoEmpleadoService tipoempleadoService; //Logica de Negocio del Core Service

    @Autowired
    private TipoEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoMobileService.class);

    /**
     * Agrega un nuevo TipoEmpleado.
     * @param tipoempleadoDto El TipoEmpleado DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TipoEmpleadoDto tipoempleadoDto) throws BaseDatosException {
        logeador.debug("agregar() tipoempleado");
        tipoempleadoService.agregar(tipoempleadoDto);
    }

    /**
     * Actualiza un TipoEmpleado existente.
     * @param id La Clave de TipoEmpleado a actualizar.
     * @param tipoempleadoDto El TipoEmpleado DTO con informacion actualizada.
     * @throws TipoEmpleadoNoEncontradoException Si TipoEmpleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TipoEmpleadoDto tipoempleadoDto) throws BaseDatosException, TipoEmpleadoNoEncontradoException {
        logeador.debug("actualizar() tipoempleado");
        tipoempleadoService.actualizar(id, tipoempleadoDto);
    }

    /**
     * Elimina TipoEmpleado por Clave.
     * @param id La Clave de TipoEmpleado a eliminar.
     * @throws TipoEmpleadoNoEncontradoException Si el TipoEmpleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, TipoEmpleadoNoEncontradoException {
        logeador.debug("eliminar() tipoempleado: {}", id);
        tipoempleadoService.eliminar(id);
    }

    /**
     * Encuentra un TipoEmpleado por Clave.
     * @param id La Clave TipoEmpleado a encontrar.
     * @return El TipoEmpleado DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TipoEmpleadoNoEncontradoException Si TipoEmpleado no es encontrado.
     */
    public TipoEmpleadoDto encontrarPorClave(Long id) throws BaseDatosException, TipoEmpleadoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoempleadoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoEmpleados.
     * @return Una lista de todos TipoEmpleado DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TipoEmpleadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoempleadoService.obtenerTodos();
    }
}
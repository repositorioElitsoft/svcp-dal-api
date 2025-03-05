package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.DireccionEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.DireccionEmpleadoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionEmpleadoFiltroMobileService {

    @Autowired
    private DireccionEmpleadoMapper direccionempleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DireccionEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(DireccionEmpleadoFiltroMobileService.class); //Logback

}
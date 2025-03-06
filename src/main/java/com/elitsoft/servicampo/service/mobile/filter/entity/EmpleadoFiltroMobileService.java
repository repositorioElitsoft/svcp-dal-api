package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.EmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.EmpleadoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoFiltroMobileService {

    @Autowired
    private EmpleadoMapper empleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EmpleadoFiltroMobileService.class); //Logback

}
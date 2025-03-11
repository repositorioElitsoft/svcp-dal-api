package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ServicioMapper;
import com.elitsoft.servicampo.mapstruct.ServicioMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioFiltroMobileService {

    @Autowired
    private ServicioMapper servicioMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ServicioFiltroMobileService.class); //Logback

}
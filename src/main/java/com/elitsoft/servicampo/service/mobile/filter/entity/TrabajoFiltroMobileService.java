package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.TrabajoMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajoFiltroMobileService {

    @Autowired
    private TrabajoMapper trabajoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoFiltroMobileService.class); //Logback

}
package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ClasificacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClasificacionClienteMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClasificacionClienteFiltroMobileService {

    @Autowired
    private ClasificacionClienteMapper clasificacionclienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClasificacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteFiltroMobileService.class); //Logback

}
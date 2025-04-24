package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ComponenteMapper;
import com.elitsoft.servicampo.mapstruct.ComponenteMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponenteFiltroMobileService {

    @Autowired
    private ComponenteMapper componenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ComponenteFiltroMobileService.class); //Logback

}
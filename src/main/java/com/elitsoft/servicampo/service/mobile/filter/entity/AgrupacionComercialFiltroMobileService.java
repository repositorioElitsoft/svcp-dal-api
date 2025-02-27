package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.AgrupacionComercialMapper;
import com.elitsoft.servicampo.mapstruct.AgrupacionComercialMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgrupacionComercialFiltroMobileService {

    @Autowired
    private AgrupacionComercialMapper agrupacionComercialMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private AgrupacionComercialMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(AgrupacionComercialFiltroMobileService.class); //Logback

}
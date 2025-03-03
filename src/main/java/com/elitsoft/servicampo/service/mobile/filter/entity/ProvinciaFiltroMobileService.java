package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ProvinciaMapper;
import com.elitsoft.servicampo.mapstruct.ProvinciaMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaFiltroMobileService {

    @Autowired
    private ProvinciaMapper provinciaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ProvinciaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ProvinciaFiltroMobileService.class); //Logback

}
package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ZonaMapper;
import com.elitsoft.servicampo.mapstruct.ZonaMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaFiltroMobileService {

    @Autowired
    private ZonaMapper zonaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ZonaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ZonaFiltroMobileService.class); //Logback

}
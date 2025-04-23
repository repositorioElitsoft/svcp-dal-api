package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.EstadoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.EstadoComponenteMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoComponenteFiltroMobileService {

    @Autowired
    private EstadoComponenteMapper estadocomponenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoComponenteFiltroMobileService.class); //Logback

}
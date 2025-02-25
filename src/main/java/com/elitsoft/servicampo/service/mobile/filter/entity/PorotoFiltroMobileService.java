package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.PorotoMapper;
import com.elitsoft.servicampo.mapstruct.PorotoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PorotoFiltroMobileService {

    @Autowired
    private PorotoMapper porotoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PorotoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PorotoFiltroMobileService.class); //Logback

}
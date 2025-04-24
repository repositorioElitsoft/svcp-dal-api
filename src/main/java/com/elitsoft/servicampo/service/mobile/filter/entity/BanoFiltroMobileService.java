package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.BanoMapper;
import com.elitsoft.servicampo.mapstruct.BanoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanoFiltroMobileService {

    @Autowired
    private BanoMapper banoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private BanoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(BanoFiltroMobileService.class); //Logback

}
package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.PaisMapper;
import com.elitsoft.servicampo.mapstruct.PaisMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisFiltroMobileService {

    @Autowired
    private PaisMapper paisMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PaisMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PaisFiltroMobileService.class); //Logback

}
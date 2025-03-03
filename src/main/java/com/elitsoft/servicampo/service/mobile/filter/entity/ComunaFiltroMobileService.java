package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ComunaMapper;
import com.elitsoft.servicampo.mapstruct.ComunaMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComunaFiltroMobileService {

    @Autowired
    private ComunaMapper comunaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ComunaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ComunaFiltroMobileService.class); //Logback

}
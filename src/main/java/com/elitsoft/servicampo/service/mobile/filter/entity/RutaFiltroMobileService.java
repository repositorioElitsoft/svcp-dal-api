package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.RutaMapper;
import com.elitsoft.servicampo.mapstruct.RutaMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaFiltroMobileService {

    @Autowired
    private RutaMapper rutaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RutaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(RutaFiltroMobileService.class); //Logback

}
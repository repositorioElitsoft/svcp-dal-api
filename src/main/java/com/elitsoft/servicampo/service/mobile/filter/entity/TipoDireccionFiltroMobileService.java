package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.TipoDireccionMapper;
import com.elitsoft.servicampo.mapstruct.TipoDireccionMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoDireccionFiltroMobileService {

    @Autowired
    private TipoDireccionMapper tipodireccionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoDireccionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoDireccionFiltroMobileService.class); //Logback

}
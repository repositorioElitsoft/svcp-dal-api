package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.TareaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaFiltroMobileService {

    @Autowired
    private TareaMapper tareaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    private static final Logger logeador = LoggerFactory.getLogger(TareaFiltroMobileService.class); //Logback

}
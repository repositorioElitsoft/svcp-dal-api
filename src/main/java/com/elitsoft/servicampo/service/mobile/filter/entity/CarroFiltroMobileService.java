package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.CarroMapper;
import com.elitsoft.servicampo.mapstruct.CarroMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroFiltroMobileService {

    @Autowired
    private CarroMapper carroMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private CarroMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(CarroFiltroMobileService.class); //Logback

}
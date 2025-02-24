package com.elitsoft.#app_name#.service.mobile.filter.entity;

import com.elitsoft.#app_name#.mapper.#Base#Mapper;
import com.elitsoft.#app_name#.mapstruct.#Base#MapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class #Base#FiltroMobileService {

    @Autowired
    private #Base#Mapper #base#Mapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private #Base#MapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(#Base#FiltroMobileService.class); //Logback

}
package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.EstadoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoFiltroMobileService {

    @Autowired
    private EstadoMapper estadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoFiltroMobileService.class); //Logback

}
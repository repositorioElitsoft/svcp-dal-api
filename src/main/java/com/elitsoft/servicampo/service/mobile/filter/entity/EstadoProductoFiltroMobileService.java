package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.EstadoProductoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoProductoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoProductoFiltroMobileService {

    @Autowired
    private EstadoProductoMapper estadoproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoProductoFiltroMobileService.class); //Logback

}
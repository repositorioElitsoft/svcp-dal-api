package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.TipoServicioMapper;
import com.elitsoft.servicampo.mapstruct.TipoServicioMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoServicioFiltroMobileService {

    @Autowired
    private TipoServicioMapper tiposervicioMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoServicioFiltroMobileService.class); //Logback

}
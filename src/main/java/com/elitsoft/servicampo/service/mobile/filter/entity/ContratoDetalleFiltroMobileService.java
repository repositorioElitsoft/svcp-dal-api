package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ContratoDetalleMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoDetalleFiltroMobileService {

    @Autowired
    private ContratoDetalleMapper contratodetalleMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleFiltroMobileService.class); //Logback

}
package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ContratoDetalleProductoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleProductoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoDetalleProductoFiltroMobileService {

    @Autowired
    private ContratoDetalleProductoMapper contratodetalleproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleProductoFiltroMobileService.class); //Logback

}
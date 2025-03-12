package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.TipoProductoTipoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoTipoComponenteMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProductoTipoComponenteFiltroMobileService {

    @Autowired
    private TipoProductoTipoComponenteMapper tipoproductotipocomponenteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoTipoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoTipoComponenteFiltroMobileService.class); //Logback

}
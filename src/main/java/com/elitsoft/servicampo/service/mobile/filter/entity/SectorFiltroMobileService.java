package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.SectorMapper;
import com.elitsoft.servicampo.mapstruct.SectorMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorFiltroMobileService {

    @Autowired
    private SectorMapper sectorMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SectorMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SectorFiltroMobileService.class); //Logback

}
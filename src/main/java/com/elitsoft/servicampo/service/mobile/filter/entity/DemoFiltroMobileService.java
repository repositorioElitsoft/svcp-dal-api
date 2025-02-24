package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.DemoMapper;
import com.elitsoft.servicampo.mapper.TareaMapper;
import com.elitsoft.servicampo.mapstruct.DemoMapStruct;
import com.elitsoft.servicampo.service.core.DemoService;
import com.elitsoft.servicampo.service.mobile.DemoMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoFiltroMobileService {

    @Autowired
    private DemoMapper demoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DemoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(DemoFiltroMobileService.class); //Logback

}
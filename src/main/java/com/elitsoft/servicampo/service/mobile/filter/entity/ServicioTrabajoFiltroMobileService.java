package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ServicioTrabajoMapper;
import com.elitsoft.servicampo.mapstruct.ServicioTrabajoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioTrabajoFiltroMobileService {

    @Autowired
    private ServicioTrabajoMapper serviciotrabajoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ServicioTrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ServicioTrabajoFiltroMobileService.class); //Logback

}
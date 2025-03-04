package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.RoleMapper;
import com.elitsoft.servicampo.mapstruct.RoleMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleFiltroMobileService {

    @Autowired
    private RoleMapper roleMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RoleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(RoleFiltroMobileService.class); //Logback

}
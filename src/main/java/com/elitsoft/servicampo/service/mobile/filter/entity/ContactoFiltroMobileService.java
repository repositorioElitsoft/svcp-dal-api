package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ContactoMapper;
import com.elitsoft.servicampo.mapstruct.ContactoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoFiltroMobileService {

    @Autowired
    private ContactoMapper contactoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContactoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ContactoFiltroMobileService.class); //Logback

}
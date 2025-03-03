package com.elitsoft.servicampo.controller.mobile.filter.entity;

import com.elitsoft.servicampo.service.mobile.filter.entity.ProvinciaFiltroMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/filter")
public class ProvinciaFiltroMobileController {

    @Autowired
    private ProvinciaFiltroMobileService provinciaFiltroMobileService; //Logica de Negocio de Filtrado del Mobile Service

    private static final Logger logeador = LoggerFactory.getLogger(ProvinciaFiltroMobileController.class); //Logback

}
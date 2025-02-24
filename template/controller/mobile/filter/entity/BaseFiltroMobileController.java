package com.elitsoft.#app_name#.controller.mobile.filter.entity;

import com.elitsoft.#app_name#.service.mobile.filter.entity.#Base#FiltroMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/filter")
public class #Base#FiltroMobileController {

    @Autowired
    private #Base#FiltroMobileService #base#FiltroMobileService; //Logica de Negocio de Filtrado del Mobile Service

    private static final Logger logeador = LoggerFactory.getLogger(#Base#FiltroMobileController.class); //Logback

}
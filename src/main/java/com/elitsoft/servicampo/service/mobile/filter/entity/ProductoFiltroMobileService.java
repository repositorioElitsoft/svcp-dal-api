package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.ProductoMapper;
import com.elitsoft.servicampo.mapstruct.ProductoMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoFiltroMobileService {

    @Autowired
    private ProductoMapper productoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ProductoFiltroMobileService.class); //Logback

}
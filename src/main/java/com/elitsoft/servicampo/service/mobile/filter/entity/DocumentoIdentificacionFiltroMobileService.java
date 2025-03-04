package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.DocumentoIdentificacionMapper;
import com.elitsoft.servicampo.mapstruct.DocumentoIdentificacionMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoIdentificacionFiltroMobileService {

    @Autowired
    private DocumentoIdentificacionMapper documentoidentificacionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DocumentoIdentificacionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(DocumentoIdentificacionFiltroMobileService.class); //Logback

}
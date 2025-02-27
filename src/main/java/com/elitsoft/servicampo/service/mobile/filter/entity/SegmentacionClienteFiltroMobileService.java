package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.SegmentacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.SegmentacionClienteMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentacionClienteFiltroMobileService {

    @Autowired
    private SegmentacionClienteMapper segmentacionclienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SegmentacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SegmentacionClienteFiltroMobileService.class); //Logback

}
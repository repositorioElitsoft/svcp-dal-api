package com.elitsoft.servicampo.service.mobile.filter.entity;

import com.elitsoft.servicampo.mapper.UserMapper; // Or a MobileUserMapper if needed
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFilterMobileService {

    @Autowired
    private UserMapper userMapper; // Or MobileUserMapper

    private static final Logger logeador = LoggerFactory.getLogger(UserFilterMobileService.class);  //Logback
}
package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.entity.User;
import com.elitsoft.servicampo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService { // Or wherever you create users

    @Autowired
    private UserMapper userMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject the PasswordEncoder

    public void createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getClave()); // Encode the password
        user.setClave(encodedPassword); // Set the encoded password on the user object
        userMapper.agregar(user); // Save the user to the database
    }


}
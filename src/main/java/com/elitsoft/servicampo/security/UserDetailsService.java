package com.elitsoft.servicampo.security;

import com.elitsoft.servicampo.domain.entity.User;
import com.elitsoft.servicampo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {



    @Autowired
    private UserMapper userMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.findByUsername(username); // Fetch user from MyBatis
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(user); // Convert to UserPrincipal
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        User user = userMapper.findById(id) ; // Fetch user from MyBatis
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(user); // Convert to UserPrincipal
    }



}

package com.elitsoft.servicampo.security;

import com.elitsoft.servicampo.domain.entity.Empleado;
import com.elitsoft.servicampo.mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    @Autowired
    private EmpleadoMapper empleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Empleado empleado = empleadoMapper.encontrarPorNombre(username); // Fetch user from MyBatis
        if (empleado == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(empleado); // Convert to UserPrincipal
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Empleado empleado = empleadoMapper.encontrarPorClave(id); // Fetch user from MyBatis
        if (empleado == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(empleado); // Convert to UserPrincipal
    }


}

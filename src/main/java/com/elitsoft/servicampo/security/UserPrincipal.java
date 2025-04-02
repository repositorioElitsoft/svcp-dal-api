package com.elitsoft.servicampo.security;

import com.elitsoft.servicampo.domain.entity.Empleado;
import com.elitsoft.servicampo.exceptions.EmpleadoAutenticacionException;
import com.elitsoft.servicampo.service.error.EmpleadoError;
import com.elitsoft.servicampo.utils.Constantes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities; // Roles/Permissions
    private Empleado empleado;

    // Constructor (Important!)
    public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities, Empleado empleado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.empleado = empleado;
    }

    public static UserPrincipal create(Empleado empleado) {  // Static factory method (Good Practice)
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + empleado.getRole().getNombreRol());
        System.out.println("UserPrincipal() methods ");
        return new UserPrincipal(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getContrasena(),
                Collections.singletonList(authority),
                empleado
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Or implement logic based on your user entity
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Or implement logic based on your user entity
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Or implement logic based on your user entity
    }

    @Override
    public boolean isEnabled() {
        if (!empleado.getEstado().getId().equals(Constantes.ESTADO_HABILITADO)) {
            throw new EmpleadoAutenticacionException(EmpleadoError.DESABILITADO.getCodigoError(), Constantes.EMPLEADO_NO_HABILITADO_MENSAJE);
        }
        return true;
    }

    public Long getId() { // Add a getter for the ID
        return this.id;
    }

    // Add other getters for user properties if you need them (e.g., email, etc.)
}
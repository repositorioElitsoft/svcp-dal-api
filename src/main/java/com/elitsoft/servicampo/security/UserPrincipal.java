package com.elitsoft.servicampo.security;

import com.elitsoft.servicampo.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities; // Roles/Permissions

    // Constructor (Important!)
    public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {  // Static factory method (Good Practice)
        List<GrantedAuthority> authorities = user.getRoles().stream() // Assuming your User entity has a getRoles() method
                .map(role -> new SimpleGrantedAuthority(role.getNombre().toString())) // Convert roles to GrantedAuthorities
                .collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getNombre(),
                user.getClave(),
                authorities
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
        return true; // Or implement logic based on your user entity
    }

    public Long getId() { // Add a getter for the ID
        return this.id;
    }

    // Add other getters for user properties if you need them (e.g., email, etc.)
}
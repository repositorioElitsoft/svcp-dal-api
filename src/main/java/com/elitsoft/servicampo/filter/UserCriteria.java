package com.elitsoft.servicampo.filter;

import lombok.Data;

@Data
public class UserCriteria {
    private String nombre;
    private String correo;
    private String role;
    // ... other user-specific filter fields
}
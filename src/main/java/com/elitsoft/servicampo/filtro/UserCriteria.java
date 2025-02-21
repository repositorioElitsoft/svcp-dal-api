package com.elitsoft.servicampo.filtro;

import lombok.Data;

@Data
public class UserCriteria {
    private String nombre;
    private String correo;
    private String role;
    // ... other user-specific filter fields
}
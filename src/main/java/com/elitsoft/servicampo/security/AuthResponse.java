package com.elitsoft.servicampo.security;

public class AuthResponse {

    private String jwt;

    // Getters and Setters are essential!
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    // Constructor (optional but good practice)
    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // Default constructor (also good practice)
    public AuthResponse() {}
}
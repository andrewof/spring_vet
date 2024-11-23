package com.andres.veterinaria.models.dto;

import com.andres.veterinaria.models.entities.Rol;

import java.util.List;

public class UsuarioRolDto {
    private String email;
    private List<Rol> roles;

    public UsuarioRolDto(String email, List<Rol> roles) {
        this.email = email;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}


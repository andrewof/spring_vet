package com.andres.veterinaria.models.mapper;

import com.andres.veterinaria.models.dto.UsuarioRolDto;
import com.andres.veterinaria.models.entities.Usuario;

public class UsuarioRolMapperDto {

    private Usuario usuario;

    private UsuarioRolMapperDto() {

    }

    public static UsuarioRolMapperDto builder() {
        return new UsuarioRolMapperDto();
    }

    public UsuarioRolMapperDto setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public UsuarioRolDto build() {
        if (usuario == null) {
            throw new RuntimeException("Se debe proporcionar el entity usuario");
        }

        return new UsuarioRolDto(usuario.getEmail(), usuario.getRoles());
    }
}

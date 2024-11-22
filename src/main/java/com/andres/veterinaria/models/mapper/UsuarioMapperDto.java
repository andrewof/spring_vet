package com.andres.veterinaria.models.mapper;

import com.andres.veterinaria.models.dto.UsuarioDto;
import com.andres.veterinaria.models.entities.Usuario;

public class UsuarioMapperDto {

    private Usuario usuario;

    private UsuarioMapperDto() {

    }

    public static UsuarioMapperDto builder() {
        return new UsuarioMapperDto();
    }

    public UsuarioMapperDto setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public UsuarioDto build() {
        if (usuario == null) {
            throw new RuntimeException("Se debe proporcionar el entity usuario");
        }

        return new UsuarioDto(usuario.getEmail(), usuario.getPassword());
    }
}

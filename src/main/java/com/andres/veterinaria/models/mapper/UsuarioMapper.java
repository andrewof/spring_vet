package com.andres.veterinaria.models.mapper;

import com.andres.veterinaria.models.dto.UsuarioDto;
import com.andres.veterinaria.models.dto.UsuarioRolDto;
import com.andres.veterinaria.models.entities.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioRolDto toUsuarioRolDto(UsuarioDto usuarioDto);

    UsuarioDto toUsuarioDto(UsuarioRolDto usuarioRolDto);

    UsuarioRolDto toUsuarioRolDto(Usuario usuario);

    List<UsuarioRolDto> toUsuariosRolesDto(List<Usuario> usuarios);
}


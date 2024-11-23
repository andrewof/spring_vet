package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.UsuarioDto;
import com.andres.veterinaria.models.dto.UsuarioRolDto;
import com.andres.veterinaria.models.entities.Usuario;
import com.andres.veterinaria.models.requests.AdminRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioRolDto> listarAdmins();

    Optional<Usuario> listarAdmin(Long id);

    Optional<UsuarioRolDto> listarAdminRolDto(Long id);

    UsuarioRolDto registrarAdmin(UsuarioDto usuarioDto);

    UsuarioRolDto actualizarAdmin(Long id, AdminRequest adminRequest);

    UsuarioRolDto eliminarUsuario(Long id);
}

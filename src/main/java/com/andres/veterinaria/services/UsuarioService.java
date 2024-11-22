package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.UsuarioDto;
import com.andres.veterinaria.models.dto.UsuarioRolDto;
import com.andres.veterinaria.models.entities.Usuario;
import com.andres.veterinaria.models.requests.AdminRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarAdmins();

    Optional<Usuario> listarAdmin(Long id);

    void registrarAdmin(Usuario usuario);

    void actualizarAdmin(Long id, AdminRequest adminRequest);

    void eliminarUsuario(Long id);
}

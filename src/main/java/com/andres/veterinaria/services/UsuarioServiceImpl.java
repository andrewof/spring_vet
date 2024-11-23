package com.andres.veterinaria.services;


import com.andres.veterinaria.models.dto.UsuarioDto;
import com.andres.veterinaria.models.dto.UsuarioRolDto;
import com.andres.veterinaria.models.entities.Rol;
import com.andres.veterinaria.models.entities.Usuario;
import com.andres.veterinaria.models.mapper.UsuarioMapper;
import com.andres.veterinaria.models.requests.AdminRequest;
import com.andres.veterinaria.repositories.RolRepository;
import com.andres.veterinaria.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioRolDto> listarAdmins() {
        return usuarioMapper.toUsuariosRolesDto(usuarioRepository.obtenerAdmins());
    }

    @Override
    public Optional<UsuarioRolDto> listarAdminRolDto(Long id) {
        Optional<Usuario> op = usuarioRepository.obtenerAdmin(id);
        return Optional.of(usuarioMapper.toUsuarioRolDto(op.orElseThrow()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> listarAdmin(Long id) {
        return usuarioRepository.obtenerAdmin(id);
    }

    @Override
    @Transactional
    public UsuarioRolDto registrarAdmin(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());

        Rol rolAdmin = rolRepository.buscarRolPorNombre("ROLE_ADMIN").orElseThrow(
                () -> new RuntimeException("No se encontró el rol"));

        usuario.setRoles(Collections.singletonList(rolAdmin));
        Usuario usuarioDb = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioRolDto(usuarioDto);
    }

    @Override
    @Transactional
    public UsuarioRolDto actualizarAdmin(Long id, AdminRequest adminRequest) {
        Optional<Usuario> exist = usuarioRepository.obtenerAdmin(id);
        if (exist.isPresent()) {
            usuarioRepository.actualizarAdmin(id, adminRequest.getEmail(), adminRequest.getPassword());

            entityManager.flush();
            entityManager.clear();

            return usuarioMapper.toUsuarioRolDto(exist.get());
        } else {
            throw new RuntimeException("No se encontró el usuario.");
        }
    }

    @Override
    @Transactional
    public UsuarioRolDto eliminarUsuario(Long id) {
        Optional<Usuario> exist = usuarioRepository.obtenerAdmin(id);
        if (exist.isPresent()) {
            usuarioRepository.eliminarAdmin(id);
            return usuarioMapper.toUsuarioRolDto(exist.get());
        } else {
            throw new RuntimeException("No se encontró el usuario.");
        }
    }
}

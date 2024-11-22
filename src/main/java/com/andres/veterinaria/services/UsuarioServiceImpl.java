package com.andres.veterinaria.services;


import com.andres.veterinaria.models.entities.Rol;
import com.andres.veterinaria.models.entities.Usuario;
import com.andres.veterinaria.models.requests.AdminRequest;
import com.andres.veterinaria.repositories.RolRepository;
import com.andres.veterinaria.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarAdmins() {
        return usuarioRepository.obtenerAdmins();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> listarAdmin(Long id) {
        return usuarioRepository.obtenerAdmin(id);
    }

    @Override
    @Transactional
    public void registrarAdmin(Usuario us) {
        Usuario usuario = new Usuario();
        usuario.setEmail(us.getEmail());
        usuario.setPassword(us.getPassword());

        Rol rolAdmin = rolRepository.buscarRolPorNombre("ROLE_ADMIN").orElseThrow(
                () -> new RuntimeException("No se encontró el rol"));

        usuario.setRoles(Arrays.asList(rolAdmin));
        Usuario usuarioDb = usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void actualizarAdmin(Long id, AdminRequest adminRequest) {
        Optional<Usuario> exist = usuarioRepository.obtenerAdmin(id);
        Usuario usuario;
        if (exist.isPresent()) {
            usuarioRepository.actualizarAdmin(id, adminRequest.getEmail(), adminRequest.getPassword());
        } else {
            throw new RuntimeException("No se encontró el usuario.");
        }
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        Optional<Usuario> exist = usuarioRepository.obtenerAdmin(id);
        if (exist.isPresent()) {
            usuarioRepository.eliminarAdmin(id);
        } else {
            throw new RuntimeException("No se encontró el usuario.");
        }
    }
}

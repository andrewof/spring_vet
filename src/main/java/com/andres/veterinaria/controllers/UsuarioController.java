package com.andres.veterinaria.controllers;

import com.andres.veterinaria.models.dto.UsuarioDto;
import com.andres.veterinaria.models.entities.Usuario;
import com.andres.veterinaria.models.requests.AdminRequest;
import com.andres.veterinaria.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@ControllerAdvice
@RequestMapping("/api/admins")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> listarAdmins() {
        return ResponseEntity.ok().body(usuarioService.listarAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarAdmin(@PathVariable Long id) {
        Optional<Usuario> op = usuarioService.listarAdmin(id);
        if (op.isPresent()) {
            return ResponseEntity.ok().body(usuarioService.listarAdminRolDto(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearAdmin(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {
        usuarioService.registrarAdmin(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAdmin(@Valid @RequestBody AdminRequest adminRequest, @PathVariable Long id) {
        Optional<Usuario> op = usuarioService.listarAdmin(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(usuarioService.actualizarAdmin(id, adminRequest));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAdmin(@PathVariable Long id) {
        Optional<Usuario> op = usuarioService.listarAdmin(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(usuarioService.eliminarUsuario(id));
        }
        return ResponseEntity.notFound().build();
    }
}

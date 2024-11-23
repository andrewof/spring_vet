package com.andres.veterinaria.controllers;

import com.andres.veterinaria.models.dto.UsuarioClienteDto;
import com.andres.veterinaria.models.entities.Cliente;
import com.andres.veterinaria.models.requests.ClienteRequest;
import com.andres.veterinaria.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@ControllerAdvice
@RequestMapping("/api/customers")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarClienteDto(id));
    }

    @PostMapping
    public ResponseEntity<?> registrarCliente(@Valid @RequestBody UsuarioClienteDto ucDto, BindingResult result) {
        UsuarioClienteDto clienteCreado = clienteService.registrarCliente(ucDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@Valid @RequestBody ClienteRequest clienteRequest, @PathVariable Long id, BindingResult result) {
        Optional<UsuarioClienteDto> op = clienteService.actualizarCliente(id, clienteRequest);
        return op.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        UsuarioClienteDto clienteEliminado = clienteService.eliminarCliente(id);
        return ResponseEntity.ok(clienteEliminado);
    }
}

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
    public ResponseEntity<?> registrarCliente(@Valid @RequestBody UsuarioClienteDto ucDto) {
        clienteService.registrarCliente(ucDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@Valid @RequestBody ClienteRequest clienteRequest, @PathVariable Long id, BindingResult result) {
        Optional<Cliente> op = clienteService.listarCliente(id);
        if (op.isPresent()) {
            clienteService.actualizarCliente(id, clienteRequest);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

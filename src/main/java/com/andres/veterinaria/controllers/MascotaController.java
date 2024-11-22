package com.andres.veterinaria.controllers;

import com.andres.veterinaria.models.dto.MascotaDuenoDto;
import com.andres.veterinaria.models.entities.Cliente;
import com.andres.veterinaria.services.ClienteService;
import com.andres.veterinaria.services.MascotaDuenoVistaService;
import com.andres.veterinaria.services.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@ControllerAdvice
@RequestMapping("/api/pets")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MascotaDuenoVistaService mascotaDuenoVistaService;

    @GetMapping()
    public ResponseEntity<?> listarMascotasDuenos() {
        return ResponseEntity.ok(mascotaDuenoVistaService.obtenerMascotasDuenosVista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarMascotaDueno(@PathVariable Long id) {
        Optional<MascotaDuenoDto> opMascota = mascotaService.listarMascota(id);
        if (opMascota.isPresent()) {
            return ResponseEntity.ok(mascotaDuenoVistaService.obtenerMascotaDuenoVistaPorId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> registrarMascota(@Valid @RequestBody MascotaDuenoDto mascotaDuenoDto, BindingResult result) {
        Optional<Cliente> optionalCliente = clienteService.listarCliente(mascotaDuenoDto.getIdDueno());
        if (optionalCliente.isEmpty()) {
            throw new RuntimeException("No se encontró el cliente.");
        }
        mascotaDuenoDto.setIdDueno(optionalCliente.get().getIdCliente());
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.registrarMascota(mascotaDuenoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMascota(@PathVariable("id") Long id) {
        Optional<MascotaDuenoDto> opCliente = mascotaService.listarMascota(id);
        if (opCliente.isPresent()) {
            return ResponseEntity.ok(mascotaService.eliminarMascota(id));
        }
        return ResponseEntity.notFound().build();
    }
}

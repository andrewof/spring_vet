package com.andres.veterinaria.controllers;

import com.andres.veterinaria.models.entities.Cita;
import com.andres.veterinaria.services.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("/api/dates")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<?> listarCitas() {
        return ResponseEntity.ok(citaService.listarCitas());
    }


    @PostMapping("/agendar")
    public ResponseEntity<?> agendarCita(@Valid @RequestBody Cita cita, BindingResult result) {
        return ResponseEntity.ok(citaService.agendarCita(cita));
    }
}
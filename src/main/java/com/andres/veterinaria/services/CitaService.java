package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.CitaDto;
import com.andres.veterinaria.models.entities.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {

    List<CitaDto> listarCitas();

    Optional<CitaDto> listarCita(Long id);

    CitaDto agendarCita(Cita cita);

    CitaDto actualizarEstadoCita(Long idCita, String estado);
}

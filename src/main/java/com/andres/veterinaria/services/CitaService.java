package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.CitaDto;
import com.andres.veterinaria.models.entities.Cita;

import java.util.List;

public interface CitaService {

    List<CitaDto> listarCitas();

    CitaDto agendarCita(Cita cita);
}

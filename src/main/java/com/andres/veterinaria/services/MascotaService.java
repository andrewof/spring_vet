package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.MascotaDuenoDto;
import com.andres.veterinaria.models.entities.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaService {
    Mascota registrarMascota(Mascota mascota);

    Optional<Mascota> listarMascota(Long id);

    List<MascotaDuenoDto> listarMascotaDueno(Long id);
}

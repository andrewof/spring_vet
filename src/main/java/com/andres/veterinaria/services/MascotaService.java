package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.MascotaDuenoDto;
import com.andres.veterinaria.models.entities.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaService {
    MascotaDuenoDto registrarMascota(MascotaDuenoDto mascotaDuenoDto);

    Optional<Mascota> listarMascota(Long id);

    List<MascotaDuenoDto> listarMascotaDueno(Long id);
}

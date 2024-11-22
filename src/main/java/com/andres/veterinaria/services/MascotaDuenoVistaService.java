package com.andres.veterinaria.services;

import com.andres.veterinaria.models.entities.MascotaDuenoVista;

import java.util.List;

public interface MascotaDuenoVistaService {

    List<MascotaDuenoVista> obtenerMascotasDuenosVista();

    List<MascotaDuenoVista> obtenerMascotaDuenoVistaPorId(Long id);
}

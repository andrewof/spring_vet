package com.andres.veterinaria.services;

import com.andres.veterinaria.models.entities.MascotaDuenoVista;
import com.andres.veterinaria.repositories.MascotaDuenoVistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MascotaDuenoVistaServiceImpl implements MascotaDuenoVistaService {

    @Autowired
    private MascotaDuenoVistaRepository mascotaDuenoVistaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MascotaDuenoVista> obtenerMascotasDuenosVista() {
        return (List<MascotaDuenoVista>) mascotaDuenoVistaRepository.findAll();
    }

    @Override
    public List<MascotaDuenoVista> obtenerMascotaDuenoVistaPorId(Long id) {
        return mascotaDuenoVistaRepository.buscarMascotaDuenoPorId(id);
    }
}

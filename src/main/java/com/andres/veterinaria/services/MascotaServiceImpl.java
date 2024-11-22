package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.MascotaDuenoDto;
import com.andres.veterinaria.models.entities.Mascota;
import com.andres.veterinaria.models.mapper.MascotaDuenoMapper;
import com.andres.veterinaria.repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaDuenoMapper mascotaDuenoMapper;

    @Autowired
    private MascotaRepository mascotaRepository;

    public Optional<Mascota> listarMascota(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public List<MascotaDuenoDto> listarMascotaDueno(Long id) {
        Optional<Mascota> mascota = mascotaRepository.buscarMascotaPorIdDto(id);
        return mascota.map(m -> List.of(mascotaDuenoMapper.toMascotaDuenoDto(m))).orElseThrow(
                () -> new RuntimeException("No se encontro la mascota"));
    }

    @Transactional
    public Mascota registrarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }
}

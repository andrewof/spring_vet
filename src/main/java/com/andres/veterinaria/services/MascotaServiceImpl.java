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

    @Transactional(readOnly = true)
    public Optional<MascotaDuenoDto> listarMascota(Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow();
        return Optional.ofNullable(mascotaDuenoMapper.toMascotaDuenoDto(mascota));
    }


    @Transactional
    public MascotaDuenoDto registrarMascota(MascotaDuenoDto mascotaDuenoDto) {
        Mascota mascota = mascotaDuenoMapper.toMascota(mascotaDuenoDto);
        mascota = mascotaRepository.save(mascota);
        return mascotaDuenoMapper.toMascotaDuenoDto(mascota);
    }

    @Transactional
    public MascotaDuenoDto eliminarMascota(Long idMascota) {
        Mascota mascota = mascotaRepository.findById(idMascota).orElseThrow();
        mascotaRepository.eliminarMascota(idMascota);
        return mascotaDuenoMapper.toMascotaDuenoDto(mascota);
    }
}

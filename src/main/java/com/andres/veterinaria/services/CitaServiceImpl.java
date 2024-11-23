package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.CitaDto;
import com.andres.veterinaria.models.entities.Cita;
import com.andres.veterinaria.models.entities.Cliente;
import com.andres.veterinaria.models.entities.Mascota;
import com.andres.veterinaria.models.mapper.CitaMapperDto;
import com.andres.veterinaria.repositories.CitaRepository;
import com.andres.veterinaria.repositories.ClienteRepository;
import com.andres.veterinaria.repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<CitaDto> listarCitas() {
        List<Cita> citas = citaRepository.listarCitas();
        return citas.stream().map(c -> CitaMapperDto.builder().setCita(c).build()).collect(Collectors.toList());
    }

    @Override
    public CitaDto agendarCita(Cita cita) {
        Optional<Cliente> cliente = clienteRepository.buscarCliente(cita.getCliente().getIdCliente());
        Optional<Mascota> mascota = mascotaRepository.findById(cita.getMascota().getIdMascota());

        if (cliente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado");
        }

        if (mascota.isEmpty()) {
            throw new RuntimeException("Mascota no encontrada");
        }

        if (!mascota.get().getDueno().getIdCliente().equals(cliente.get().getIdCliente())) {
            throw new RuntimeException("La mascota no pertenece al cliente especificado");
        }

        if (citaRepository.existePorClienteFecha(cita.getCliente().getIdCliente(), cita.getFechaHora())) {
            throw new RuntimeException("Ya existe una cita en el horario especificado");
        }

        cita.setCliente(cliente.get());
        cita.setMascota(mascota.get());

        Cita citaGuardada = citaRepository.save(cita);
        return CitaMapperDto.builder().setCita(citaGuardada).build();
    }
}

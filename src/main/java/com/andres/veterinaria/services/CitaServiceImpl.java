package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.CitaDto;
import com.andres.veterinaria.models.entities.Cita;
import com.andres.veterinaria.models.entities.Cliente;
import com.andres.veterinaria.models.entities.Mascota;
import com.andres.veterinaria.models.mapper.CitaMapperDto;
import com.andres.veterinaria.repositories.CitaRepository;
import com.andres.veterinaria.repositories.ClienteRepository;
import com.andres.veterinaria.repositories.MascotaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<CitaDto> listarCitas() {
        List<Cita> citas = citaRepository.listarCitas();
        return citas.stream().map(c -> CitaMapperDto.builder().setCita(c).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CitaDto> listarCita(Long id) {
        Optional<Cita> optionalCita = citaRepository.findById(id);
        return optionalCita.map(c -> CitaMapperDto.builder().setCita(optionalCita.get()).build());
    }

    @Override
    @Transactional
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

        if (citaRepository.existePorClienteFecha(cita.getCliente().getIdCliente(),
                cita.getFecha(), cita.getHora()) > 0) {
            throw new RuntimeException("Ya existe una cita en el horario especificado");
        }

        cita.setCliente(cliente.get());
        cita.setMascota(mascota.get());

        Cita citaGuardada = citaRepository.save(cita);
        return CitaMapperDto.builder().setCita(citaGuardada).build();
    }

    @Override
    @Transactional
    public CitaDto actualizarEstadoCita(Long idCita, String estado) {
        if (!estado.equals("APROBADA") && !estado.equals("RECHAZADA")) {
            throw new RuntimeException("Estado no válido. DEBE SER 'APROBADA' o 'RECHAZADA'");
        }

        Optional<Cita> optionalCita = citaRepository.findById(idCita);
        if (optionalCita.isEmpty()) {
            throw new RuntimeException("No se encontró la cita");
        }

        citaRepository.cambiarEstadoCita(idCita, estado);

        entityManager.flush();
        entityManager.clear();

        Cita cita = citaRepository.listarCitaPorId(idCita);

        return CitaMapperDto.builder().setCita(cita).build();
    }
}

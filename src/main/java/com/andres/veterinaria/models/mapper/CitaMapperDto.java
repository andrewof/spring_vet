package com.andres.veterinaria.models.mapper;

import com.andres.veterinaria.models.dto.CitaDto;
import com.andres.veterinaria.models.entities.Cita;
import com.andres.veterinaria.models.entities.Cliente;
import com.andres.veterinaria.models.entities.Mascota;

public class CitaMapperDto {

    private Cita cita;

    private CitaMapperDto() {

    }

    public static CitaMapperDto builder() {
        return new CitaMapperDto();
    }

    public CitaMapperDto setCita(Cita cita) {
        this.cita = cita;
        return this;
    }

    public CitaDto build() {
        if (cita == null) {
            throw new RuntimeException("Debe proporcionar el entity cita");
        }

        return new CitaDto(cita.getIdCita(), cita.getCliente().getIdCliente(), cita.getMascota().getIdMascota(),
                cita.getFecha(), cita.getHora(), cita.getEstado().toString());
    }

    public static Cita toEntity(CitaDto citaDto) {
        if (citaDto == null) {
            throw new RuntimeException("Debe proporcionar el DTO de cita");
        }

        Cliente cliente = new Cliente();
        cliente.setIdCliente(citaDto.getIdCliente());

        Mascota mascota = new Mascota();
        mascota.setIdMascota(citaDto.getIdMascota());

        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setMascota(mascota);
        cita.setFecha(citaDto.getFecha());
        cita.setHora(citaDto.getHora());

        return cita;
    }
}

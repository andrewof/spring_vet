package com.andres.veterinaria.models.mapper;

import com.andres.veterinaria.models.dto.CitaDto;
import com.andres.veterinaria.models.entities.Cita;

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

        return new CitaDto(cita.getIdCita(), cita.getCliente().getIdCliente(),
                cita.getMascota().getIdMascota(), cita.getFechaHora(), cita.getEstado().toString());
    }
}

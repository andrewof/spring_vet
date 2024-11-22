package com.andres.veterinaria.models.mapper;

import com.andres.veterinaria.models.dto.MascotaDuenoDto;
import com.andres.veterinaria.models.entities.Mascota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MascotaDuenoMapper {


    @Mappings({
            @Mapping(source = "nombre", target = "nombreMascota"),
            @Mapping(source = "tipo", target = "tipo"),
            @Mapping(source = "raza", target = "raza"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "dueno.idCliente", target = "idDueno")
    })
    MascotaDuenoDto toMascotaDuenoDto(Mascota mascota);

    @Mappings({
            @Mapping(source = "nombreMascota", target = "nombre"),
            @Mapping(source = "tipo", target = "tipo"),
            @Mapping(source = "raza", target = "raza"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "idDueno", target = "dueno.idCliente")
    })
    Mascota toMascota(MascotaDuenoDto mascotaDuenoDto);

    List<MascotaDuenoDto> toMascotasDuenosDtos(List<Mascota> mascotas);
}

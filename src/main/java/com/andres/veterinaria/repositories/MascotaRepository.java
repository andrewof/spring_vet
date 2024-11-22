package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.dto.MascotaDuenoDto;
import com.andres.veterinaria.models.entities.Mascota;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {

    @Query(value = "SELECT * FROM mascotas_duenos WHERE idMascota = :id", nativeQuery = true)
    Optional<Mascota> buscarMascotaPorIdDto(@Param("id") Long id);
}

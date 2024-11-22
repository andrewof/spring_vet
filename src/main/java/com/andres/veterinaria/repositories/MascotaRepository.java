package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.entities.Mascota;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {

    @Modifying
    @Query(value = "DELETE FROM mascotas WHERE id_mascota = :idMascota", nativeQuery = true)
    void eliminarMascota(@Param("idMascota") Long idMascota);
}

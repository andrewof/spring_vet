package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.entities.MascotaDuenoVista;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaDuenoVistaRepository extends CrudRepository<MascotaDuenoVista, Long> {

    @Query(value = "SELECT * FROM vw_mascotas_duenos WHERE id_mascota = :idMascota", nativeQuery = true)
    List<MascotaDuenoVista> buscarMascotaDuenoPorId(@Param("idMascota") Long idMascota);
}

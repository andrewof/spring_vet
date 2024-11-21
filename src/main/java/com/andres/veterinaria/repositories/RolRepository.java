package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.entities.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {

    @Query(value = "SELECT * FROM roles WHERE nombre = :nombre", nativeQuery = true)
    Optional<Rol> buscarRolPorNombre(@Param("nombre") String nombre);
}

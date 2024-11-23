package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.entities.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {

    @Query(value = "SELECT * FROM citas", nativeQuery = true)
    List<Cita> listarCitas();

    @Query(value = "SELECT * FROM citas WHERE id_cita = :idCita", nativeQuery = true)
    List<Cita> listarCitaPorId(@Param("idCita") Long idCita);

    @Query(value = "SELECT COUNT(*) > 0 FROM citas WHERE id_cliente = :idCliente " +
            "AND fecha = :fecha AND hora = :hora", nativeQuery = true)
    Long existePorClienteFecha(@Param("idCliente") Long idCliente, @Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora);

}

package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.entities.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {


    @Query(value = "SELECT * FROM clientes c WHERE c.id_cliente = :idCliente", nativeQuery = true)
    Optional<Cliente> buscarCliente(@Param("idCliente") Long idCliente);
}

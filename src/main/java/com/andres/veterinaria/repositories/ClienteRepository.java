package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.entities.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {


    @Query(value = "SELECT * FROM clientes c WHERE c.id_cliente = :idCliente", nativeQuery = true)
    Optional<Cliente> buscarCliente(@Param("idCliente") Long idCliente);

    @Modifying
    @Transactional
    @Query(value = """
    UPDATE clientes c 
    JOIN usuarios u ON c.id_usuario = u.id_usuario
    SET 
        c.nombre = COALESCE(:nombre, c.nombre),
        c.apellido = COALESCE(:apellido, c.apellido),
        c.telefono = COALESCE(:telefono, c.telefono),
        u.email = COALESCE(:email, u.email),
        u.password = COALESCE(:password, u.password)
    WHERE c.id_cliente = :idCliente
    """, nativeQuery = true)
    void actualizarCliente(
            @Param("idCliente") Long idCliente,
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("telefono") String telefono,
            @Param("email") String email,
            @Param("password") String password
    );
}

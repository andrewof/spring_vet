package com.andres.veterinaria.repositories;

import com.andres.veterinaria.models.dto.UsuarioClienteDto;
import com.andres.veterinaria.models.dto.UsuarioRolDto;
import com.andres.veterinaria.models.entities.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query(value = """
            SELECT u.*, r.* FROM usuarios_roles ur
            JOIN usuarios u ON ur.id_usuario = u.id_usuario
            JOIN roles r ON ur.id_rol = r.id_rol
            WHERE r.nombre = "ROLE_ADMIN"
            """, nativeQuery = true)
    List<Usuario> obtenerAdmins();

    @Query(value = """
            SELECT u.*, r.* FROM usuarios_roles ur
            JOIN usuarios u ON ur.id_usuario = u.id_usuario
            JOIN roles r ON ur.id_rol = r.id_rol
            WHERE r.nombre = 'ROLE_ADMIN' AND u.id_usuario = :idUsuario
            """, nativeQuery = true)
    Optional<Usuario> obtenerAdmin(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Query(value = """
            UPDATE usuarios
            SET 
                email = COALESCE(:email, email),
                password = COALESCE(:password, password)
            WHERE id_usuario = :idUsuario
            AND EXISTS (
             SELECT 1 FROM usuarios_roles ur
             JOIN roles r ON ur.id_rol = r.id_rol
             WHERE ur.id_usuario = :idUsuario AND r.nombre = 'ROLE_ADMIN' 
            )
            """, nativeQuery = true)
    void actualizarAdmin(@Param("idUsuario") Long idUsuario, @Param("email") String email, @Param("password") String password);

    @Modifying
    @Query(value = """
            DELETE FROM usuarios
            WHERE id_usuario = :idUsuario
              AND EXISTS (
                  SELECT 1 FROM usuarios_roles ur
                  JOIN roles r ON ur.id_rol = r.id_rol
                  WHERE ur.id_usuario = :idUsuario AND r.nombre = 'ROLE_ADMIN'
              )
            """, nativeQuery = true)
    void eliminarAdmin(@Param("idUsuario") Long idUsuario);

}

package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.UsuarioClienteDto;
import com.andres.veterinaria.models.entities.Cliente;
import com.andres.veterinaria.models.requests.ClienteRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    UsuarioClienteDto registrarCliente(UsuarioClienteDto ucDto);

    List<UsuarioClienteDto> listarClientes();

    Optional<Cliente> listarCliente(Long idCliente);

    List<UsuarioClienteDto> listarClienteDto(Long idCliente);

    Optional<UsuarioClienteDto> actualizarCliente(Long idCliente, ClienteRequest clienteRequest);

    UsuarioClienteDto eliminarCliente(Long id);
}

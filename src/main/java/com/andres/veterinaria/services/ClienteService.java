package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.UsuarioClienteDto;
import com.andres.veterinaria.models.requests.ClienteRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    void registrarCliente(UsuarioClienteDto ucDto);

    List<UsuarioClienteDto> listarClientes();

    Optional<Object> listarCliente(Long idCliente);

    Optional<Object> actualizarCliente(Long idCliente, ClienteRequest clienteRequest);
}

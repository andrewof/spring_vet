package com.andres.veterinaria.models.mapper;

import com.andres.veterinaria.models.dto.UsuarioClienteDto;
import com.andres.veterinaria.models.entities.Cliente;

public class ClienteMapperDto {

    private Cliente cliente;

    private ClienteMapperDto() {

    }

    public static  ClienteMapperDto builder() {
        return new ClienteMapperDto();
    }

    public ClienteMapperDto setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public UsuarioClienteDto build() {
        if (cliente == null) {
            throw new RuntimeException("Debe pasar el entity cliente");
        }
        return new UsuarioClienteDto(cliente.getUsuario().getIdUsuario(),
                cliente.getIdCliente(), cliente.getNombre(), cliente.getApellido(),
                cliente.getUsuario().getEmail(), cliente.getUsuario().getPassword(), cliente.getTelefono());
    }
}

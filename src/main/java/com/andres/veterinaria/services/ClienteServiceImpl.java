package com.andres.veterinaria.services;

import com.andres.veterinaria.models.dto.UsuarioClienteDto;
import com.andres.veterinaria.models.entities.Cliente;
import com.andres.veterinaria.models.entities.Rol;
import com.andres.veterinaria.models.entities.Usuario;
import com.andres.veterinaria.models.mapper.ClienteMapperDto;
import com.andres.veterinaria.models.requests.ClienteRequest;
import com.andres.veterinaria.repositories.ClienteRepository;
import com.andres.veterinaria.repositories.RolRepository;
import com.andres.veterinaria.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    @Transactional(readOnly = true)
    public List<UsuarioClienteDto> listarClientes() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        return clientes.stream().map(c -> ClienteMapperDto.builder().setCliente(c).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Object> listarCliente(Long idCliente) {
        Optional<Cliente> opClientes = clienteRepository.buscarCliente(idCliente);
        if (opClientes.isPresent()) {
            return Optional.of(opClientes.stream().map(c -> ClienteMapperDto.builder().setCliente(c).build()).collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void registrarCliente(UsuarioClienteDto ucDto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(ucDto.getEmail());
        usuario.setPassword(ucDto.getPassword());

        Rol rolCliente = rolRepository.buscarRolPorNombre("ROLE_CUSTOMER").orElseThrow(
                () -> new RuntimeException("No se encontró el rol"));

        usuario.setRoles(Arrays.asList(rolCliente));
        Usuario usuarioDb = usuarioRepository.save(usuario);

        Cliente cliente = new Cliente();
        cliente.setNombre(ucDto.getNombre());
        cliente.setApellido(ucDto.getApellido());
        cliente.setTelefono(ucDto.getTelefono());
        cliente.setUsuario(usuarioDb);
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Object> actualizarCliente(Long idCliente, ClienteRequest clienteRequest) {
        Optional<Object> op = Optional.of(clienteRepository.buscarCliente(idCliente));
        if (op.isPresent()) {
            clienteRepository.actualizarCliente(idCliente, clienteRequest.getNombre(), clienteRequest.getApellido(),
                    clienteRequest.getTelefono(), clienteRequest.getEmail(), clienteRequest.getPassword());
        } else {
            throw new RuntimeException("El cliente no se encuentra.");
        }

        return Optional.of(clienteRepository.buscarCliente(idCliente));
    }
}

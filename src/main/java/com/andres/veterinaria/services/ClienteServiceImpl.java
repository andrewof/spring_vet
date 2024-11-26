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
import jakarta.persistence.EntityManager;
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

    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public List<UsuarioClienteDto> listarClientes() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        return clientes.stream().map(c -> ClienteMapperDto.builder().setCliente(c).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> listarCliente(Long idCliente) {
        return clienteRepository.buscarCliente(idCliente);
    }

    @Override
    public List<UsuarioClienteDto> listarClienteDto(Long idCliente) {
        Optional<Cliente> opClientes = clienteRepository.buscarCliente(idCliente);
        return opClientes.stream().map(c -> ClienteMapperDto.builder().setCliente(c).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioClienteDto registrarCliente(UsuarioClienteDto ucDto) {
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
        Cliente clienteDb = clienteRepository.save(cliente);
        return ClienteMapperDto.builder().setCliente(clienteDb).build();
    }

    @Override
    @Transactional
    public Optional<UsuarioClienteDto> actualizarCliente(Long idCliente, ClienteRequest clienteRequest) {
        Optional<Cliente> op = clienteRepository.buscarCliente(idCliente);
        if (op.isPresent()) {
            clienteRepository.actualizarCliente(idCliente, clienteRequest.getNombre(), clienteRequest.getApellido(),
                    clienteRequest.getTelefono(), clienteRequest.getEmail(), clienteRequest.getPassword());

            entityManager.flush();
            entityManager.clear();

            Optional<Cliente> opClienteActualizado = clienteRepository.buscarCliente(idCliente);
            return opClienteActualizado.map(c -> ClienteMapperDto.builder().setCliente(c).build());
        } else {
            throw new RuntimeException("El cliente no se encuentra.");
        }
    }

    @Override
    @Transactional
    public UsuarioClienteDto eliminarCliente(Long id) {
        Optional<Cliente> op = clienteRepository.buscarCliente(id);
        if (op.isPresent()) {
            Cliente clienteDb = op.get();
            usuarioRepository.deleteById(clienteDb.getUsuario().getIdUsuario());
            return ClienteMapperDto.builder().setCliente(clienteDb).build();
        } else {
            throw new RuntimeException("No se encontró el cliente");
        }
    }
}

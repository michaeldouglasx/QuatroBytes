package com.example.QuatroBytes.service;

import com.example.QuatroBytes.model.Cliente;
import com.example.QuatroBytes.repository.ClienteRepository;
import com.example.QuatroBytes.repository.dto.ClienteRequestDTO;
import com.example.QuatroBytes.repository.dto.ClienteResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO clienteRequestDTO){

        Cliente cliente = new Cliente(
                clienteRequestDTO.nome(),
                clienteRequestDTO.cpf(),
                clienteRequestDTO.telefone(),
                clienteRequestDTO.endereco()
        );


        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getCpf(),
                clienteSalvo.getTelefone(),
                clienteSalvo.getDataRegistro(),
                clienteSalvo.getEndereco()
        );

    }
    public ClienteResponseDTO editarCliente(Long id, ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow();

        cliente.atualizar(clienteRequestDTO.nome(),
                clienteRequestDTO.cpf(),
                clienteRequestDTO.telefone(),
                clienteRequestDTO.endereco());

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return new ClienteResponseDTO(clienteAtualizado.getId(),
                clienteAtualizado.getNome(),
                clienteAtualizado.getCpf(),
                clienteAtualizado.getTelefone(),
                clienteAtualizado.getDataRegistro(),
                clienteAtualizado.getEndereco());



    }






}

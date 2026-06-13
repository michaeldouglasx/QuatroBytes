package com.example.QuatroBytes.service;

import com.example.QuatroBytes.model.Cliente;
import com.example.QuatroBytes.repository.ClienteRepository;
import com.example.QuatroBytes.repository.VendaRepository;
import com.example.QuatroBytes.repository.dto.ClienteRequestDTO;
import com.example.QuatroBytes.repository.dto.ClienteResponseDTO;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;

    public ClienteService(ClienteRepository clienteRepository, VendaRepository vendaRepository) {
        this.clienteRepository = clienteRepository;
        this.vendaRepository= vendaRepository;
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
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado!"));

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

    public void deletarCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente não existe para ser excluído!"));

        if (vendaRepository.existsVendaByCliente(cliente)){
            throw new RuntimeException("Cliente está com venda ativa!");
        }
        else{clienteRepository.delete(cliente);}


    }

    public ClienteResponseDTO visualizaCliente(Long id){

      Cliente cliente = clienteRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Cliente não encontrado"));

       return new ClienteResponseDTO(cliente.getId(),
               cliente.getNome(),
               cliente.getCpf(),
               cliente.getTelefone(),
               cliente.getDataRegistro(),
               cliente.getEndereco());
    }

    public List<ClienteResponseDTO> buscarClientes(){

        List<Cliente> clienteList= clienteRepository.findAll();

        return clienteList.stream()
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getTelefone(),
                        cliente.getDataRegistro(),
                        cliente.getEndereco()
                ))
                        .toList();


    }



}

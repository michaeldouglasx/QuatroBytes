package com.example.QuatroBytes.controller;
import com.example.QuatroBytes.repository.dto.ClienteRequestDTO;
import com.example.QuatroBytes.repository.dto.ClienteResponseDTO;
import com.example.QuatroBytes.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes(){

        List<ClienteResponseDTO> listaClientes = clienteService.buscarClientes();

        return ResponseEntity.ok(listaClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarCliente(@PathVariable("id") Long id){
        ClienteResponseDTO cliente = clienteService.visualizaCliente(id);
        return ResponseEntity.ok(cliente);

    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO){

        ClienteResponseDTO cliente = clienteService.cadastrarCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteAtualizado = clienteService.editarCliente(id, clienteRequestDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }



}

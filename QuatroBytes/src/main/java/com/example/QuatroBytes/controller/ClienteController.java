package com.example.QuatroBytes.controller;
import com.example.QuatroBytes.dto.cliente.ClienteRequestDTO;
import com.example.QuatroBytes.dto.cliente.ClienteResponseDTO;
import com.example.QuatroBytes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes(){

        List<ClienteResponseDTO> listaClientes = clienteService.buscarClientes();

        return ResponseEntity.ok(listaClientes);
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarCliente(@PathVariable("id") Long id){
        ClienteResponseDTO cliente = clienteService.visualizaCliente(id);
        return ResponseEntity.ok(cliente);

    }
    @Operation(summary = "Cadastrar cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO cliente = clienteService.cadastrarCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @Operation(summary = "Deletar Cliente por ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza cliente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable("id") Long id, @RequestBody @Valid ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteAtualizado = clienteService.editarCliente(id, clienteRequestDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }



}

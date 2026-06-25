package com.example.QuatroBytes.controller;


import com.example.QuatroBytes.dto.venda.VendaRequestDTO;
import com.example.QuatroBytes.dto.venda.VendaResponseDTO;
import com.example.QuatroBytes.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vendas")
@Tag(name = "Vendas", description = "Gerenciamento de Vendas")
public class VendaController {
    private final VendaService vendaService;
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Operation(summary = "Registrar venda")
    @PostMapping
    public ResponseEntity<VendaResponseDTO> criarVenda(@RequestBody @Valid VendaRequestDTO vendaRequestDTO){
        VendaResponseDTO vendaResponseDTO= vendaService.gerarVenda(vendaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

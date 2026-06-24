package com.example.QuatroBytes.dto.venda;

import com.example.QuatroBytes.model.Cliente;
import com.example.QuatroBytes.model.ItemVenda;
import com.example.QuatroBytes.model.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VendaRequestDTO(
        @NotNull
        Long clienteId,
        @NotEmpty
        List<ItemVenda> itensVenda,
        @NotNull
        Long usuarioResponsavelId) {
}

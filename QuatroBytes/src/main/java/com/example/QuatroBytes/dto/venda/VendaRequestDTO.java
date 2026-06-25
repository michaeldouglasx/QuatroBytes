package com.example.QuatroBytes.dto.venda;

import com.example.QuatroBytes.dto.ItemVenda.ItemVendaRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VendaRequestDTO(

        @Schema(description = "ID do cliente", example = "1")
        @NotNull
        Long clienteId,

        @Schema(description = "Lista de itens", example = "")
        @NotEmpty
        List<ItemVendaRequestDTO> itensVenda,

        @Schema(description = "ID do usuário", example = "1")
        @NotNull
        Long usuarioResponsavelId) {
}

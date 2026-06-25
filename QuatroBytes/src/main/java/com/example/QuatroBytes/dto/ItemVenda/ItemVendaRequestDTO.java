package com.example.QuatroBytes.dto.ItemVenda;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ItemVendaRequestDTO(
        @NotNull
        Long produtoId,
        @NotEmpty
        @Min(0)
        Integer quantidade) {
}

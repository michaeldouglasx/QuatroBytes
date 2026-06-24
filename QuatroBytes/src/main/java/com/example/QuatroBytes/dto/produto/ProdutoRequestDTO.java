package com.example.QuatroBytes.dto.produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank
        String nome,

        String descricao,

        @NotNull
        @Positive
        BigDecimal preco,

        @NotNull
        @Min(0)
        Integer quantidadeEstoque,

        @NotNull
        @Min(0)
        Integer estoqueMinimo
) {

}

package com.example.QuatroBytes.dto.produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(

        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidadeEstoque,
        Integer estoqueMinimo) {

}


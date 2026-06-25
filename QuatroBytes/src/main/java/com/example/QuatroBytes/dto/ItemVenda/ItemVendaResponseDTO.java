package com.example.QuatroBytes.dto.ItemVenda;

import java.math.BigDecimal;

public record ItemVendaResponseDTO(
        Long produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal subtotal) {
}

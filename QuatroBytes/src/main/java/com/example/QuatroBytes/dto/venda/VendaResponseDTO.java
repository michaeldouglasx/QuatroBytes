package com.example.QuatroBytes.dto.venda;

import com.example.QuatroBytes.dto.ItemVenda.ItemVendaResponseDTO;

import java.util.List;

public record VendaResponseDTO(Long clienteId, List<ItemVendaResponseDTO> itensVenda, String status, Long  usuarioResponsavelId) {
}

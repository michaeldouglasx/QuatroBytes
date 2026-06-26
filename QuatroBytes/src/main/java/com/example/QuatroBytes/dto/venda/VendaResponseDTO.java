package com.example.QuatroBytes.dto.venda;

import com.example.QuatroBytes.dto.ItemVenda.ItemVendaResponseDTO;
import com.example.QuatroBytes.model.Status;

import java.util.List;

public record VendaResponseDTO(Long clienteId, List<ItemVendaResponseDTO> itensVenda, Status status, Long  usuarioResponsavelId) {
}

package com.example.QuatroBytes.service;

import com.example.QuatroBytes.dto.ItemVenda.ItemVendaRequestDTO;
import com.example.QuatroBytes.dto.ItemVenda.ItemVendaResponseDTO;
import com.example.QuatroBytes.dto.venda.VendaRequestDTO;
import com.example.QuatroBytes.dto.venda.VendaResponseDTO;
import com.example.QuatroBytes.model.*;
import com.example.QuatroBytes.repository.ClienteRepository;
import com.example.QuatroBytes.repository.ProdutoRepository;
import com.example.QuatroBytes.repository.UsuarioRepository;
import com.example.QuatroBytes.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {
    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public VendaService(ProdutoRepository produtoRepository, VendaRepository vendaRepository, ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @Transactional
    public VendaResponseDTO gerarVenda (VendaRequestDTO vendaDTO){
        Cliente cliente = clienteRepository.findById(vendaDTO.clienteId())
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado!"));
        Usuario usuario = usuarioRepository.findById(vendaDTO.usuarioResponsavelId())
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));

        List<ItemVenda> itens = new ArrayList<>();

        for(ItemVendaRequestDTO itemDto: vendaDTO.itensVenda()){
            Produto produto = produtoRepository.findById(itemDto.produtoId())
                    .orElseThrow(()->new RuntimeException("Produto ID: " + itemDto.produtoId() +  "Não Encontrado" ));
            if (produto.getQuantidadeEstoque()< itemDto.quantidade()) {
                throw new RuntimeException("Quantidade solicitada excede Estoque");
            }
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDto.quantidade());

            BigDecimal precoUnitario = produto.getPreco();

            BigDecimal subtotal = precoUnitario.multiply(new BigDecimal(itemDto.quantidade()));

            ItemVenda novoItem = new ItemVenda(null, produto, itemDto.quantidade(), precoUnitario, subtotal);

            itens.add(novoItem);

        }
        Venda novaVenda = new Venda(cliente,itens,Status.CONFIRMADA, usuario);

        for(ItemVenda itemVenda : itens){
            itemVenda.setVenda(novaVenda);
        }

        Venda vendaSalva = vendaRepository.save(novaVenda);

        return converterParaResponseDTO(vendaSalva);

    }

    public VendaResponseDTO converterParaResponseDTO(Venda venda){
        List<ItemVendaResponseDTO> itensDTO = venda.getItensVenda().stream()
                .map(item -> new ItemVendaResponseDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getSubtotal()
                )).toList();


        return new VendaResponseDTO(
                venda.getCliente().getId(),
                itensDTO,
                venda.getStatus(),
                venda.getUsuarioResponsavel().getId()
        );

    }

    @Transactional
    public VendaResponseDTO cancelarVenda(Long id){
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Venda não existe com ID:" + id));
        if (venda.getStatus() == Status.CANCELADA){
            throw new IllegalArgumentException("Essa venda já foi cancelada anteriormente");
        }
        venda.setStatus(Status.CANCELADA);

        for (ItemVenda item : venda.getItensVenda()){

            Produto produto = item.getProduto();
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque()+item.getQuantidade());
        }

        Venda vendaAtualizada = vendaRepository.save(venda);
        return converterParaResponseDTO(vendaAtualizada);

    }







}

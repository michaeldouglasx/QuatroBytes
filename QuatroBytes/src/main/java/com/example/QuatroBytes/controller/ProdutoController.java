package com.example.QuatroBytes.controller;

import com.example.QuatroBytes.dto.produto.ProdutoRequestDTO;
import com.example.QuatroBytes.dto.produto.ProdutoResponseDTO;
import com.example.QuatroBytes.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("api/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de Produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos(){
        List<ProdutoResponseDTO> listaDeProdutos = produtoService.listarProdutos();
        return ResponseEntity.status(HttpStatus.OK).body(listaDeProdutos);
    }
    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> consultarProduto (@PathVariable("id") Long id){
        ProdutoResponseDTO produto = produtoService.buscarProduto(id);
        return ResponseEntity.ok().body(produto);
    }
    @Operation(summary = "Cadastrar Produto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProdutos(@RequestBody @Valid ProdutoRequestDTO produto){
        ProdutoResponseDTO produtoCriado = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }
    @Operation(summary = "Deletar produto por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Atualizar produto por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO>editarProduto(@PathVariable("id") Long id, @RequestBody @Valid ProdutoRequestDTO produto){
        ProdutoResponseDTO produtoEditado = produtoService.editarProduto(id, produto);
        return ResponseEntity.ok(produtoEditado);
    }

}




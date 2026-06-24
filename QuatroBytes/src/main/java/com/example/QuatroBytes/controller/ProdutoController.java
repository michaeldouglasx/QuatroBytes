package com.example.QuatroBytes.controller;

import com.example.QuatroBytes.dto.produto.ProdutoRequestDTO;
import com.example.QuatroBytes.dto.produto.ProdutoResponseDTO;
import com.example.QuatroBytes.service.ProdutoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos(){
        List<ProdutoResponseDTO> listaDeProdutos = produtoService.listarProdutos();
        return ResponseEntity.status(HttpStatus.OK).body(listaDeProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> consultarProduto (@PathVariable("id") Long id){
        ProdutoResponseDTO produto = produtoService.buscarProduto(id);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProdutos(@RequestBody @Valid ProdutoRequestDTO produto){
        ProdutoResponseDTO produtoCriado = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/id")
    public ResponseEntity<ProdutoResponseDTO>editarProduto(@PathVariable("id") Long id, @RequestBody @Valid ProdutoRequestDTO produto){
        ProdutoResponseDTO produtoEditado = produtoService.editarProduto(id, produto);
        return ResponseEntity.ok(produtoEditado);
    }

}




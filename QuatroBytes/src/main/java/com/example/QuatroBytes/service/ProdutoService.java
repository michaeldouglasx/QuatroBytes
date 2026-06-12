package com.example.QuatroBytes.service;

import com.example.QuatroBytes.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import com.example.QuatroBytes.model.Produto;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos (){
        return produtoRepository.findAll();
    }
    public void deletarProduto(Produto produto){
         produtoRepository.delete(produto);
    }


}

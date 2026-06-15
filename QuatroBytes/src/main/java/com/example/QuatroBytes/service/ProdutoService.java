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

    public Produto editarProduto(Long id,Produto produto){
        Produto buscaProduto = produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));

        buscaProduto.atualizarProduto(produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                produto.getEstoqueMinimo()
        );
        Produto produtoSalvo = produtoRepository.save(buscaProduto);

        return produtoSalvo;

    }
    public Produto criarProduto (Produto produto) {
        Produto novoProduto = new Produto(produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                produto.getEstoqueMinimo()
        );
         Produto produtoSalvo = produtoRepository.save(novoProduto);

         return produtoSalvo;


    }

}

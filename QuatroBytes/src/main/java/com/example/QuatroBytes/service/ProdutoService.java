package com.example.QuatroBytes.service;

import com.example.QuatroBytes.dto.produto.ProdutoRequestDTO;
import com.example.QuatroBytes.dto.produto.ProdutoResponseDTO;
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

    public List<ProdutoResponseDTO> listarProdutos (){
            List<Produto> lista = produtoRepository.findAll();
            return lista.stream()
                    .map(item -> new ProdutoResponseDTO(item.getNome(),
                            item.getDescricao(),
                            item.getPreco(),
                            item.getQuantidadeEstoque(),
                            item.getEstoqueMinimo())).toList();

    }

    public ProdutoResponseDTO buscarProduto(Long id){
             Produto produtoEcnontrado = produtoRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
             return converterParaDTO(produtoEcnontrado);

    }

    public void deletarProduto(Long id){
        if (!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado!");
        }
         produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO editarProduto(Long id,ProdutoRequestDTO produto ){
        Produto buscaProduto = produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));

        buscaProduto.atualizarProduto(produto.nome(),
                produto.descricao(),
                produto.preco(),
                produto.quantidadeEstoque(),
                produto.estoqueMinimo()
        );
        Produto produtoSalvo = produtoRepository.save(buscaProduto);

        return converterParaDTO(produtoSalvo);
    }


    public ProdutoResponseDTO criarProduto (ProdutoRequestDTO produto) {

        Produto novoProduto = new Produto(
                produto.nome(),
                produto.descricao(),
                produto.preco(),
                produto.quantidadeEstoque(),
                produto.estoqueMinimo()
        );

         Produto produtoSalvo = produtoRepository.save(novoProduto);

         return converterParaDTO(produtoSalvo);
    }

    private ProdutoResponseDTO converterParaDTO(Produto produto){
        return new ProdutoResponseDTO(produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getQuantidadeEstoque(), produto.getEstoqueMinimo());

    }
}

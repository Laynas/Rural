package br.com.agro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agro.entities.Produto;
import br.com.agro.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto Produto) {
        return produtoRepository.save(Produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto obterProduto(Long id) throws Exception{
        Optional<Produto> Produto = produtoRepository.findById(id);
        if(Produto.isPresent()) {
            return Produto.get();
        } else {
            throw new Exception("Produto não encontrado");
        }
    }
}

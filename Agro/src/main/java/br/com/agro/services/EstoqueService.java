package br.com.agro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agro.entities.Estoque;
import br.com.agro.repositories.EstoqueRepository;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    public Estoque salvarEstoque(Estoque Estoque) {
        return estoqueRepository.save(Estoque);
    }

    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    public void excluirEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

    public Estoque obterEstoque(Long id) throws Exception{
        Optional<Estoque> Estoque = estoqueRepository.findById(id);
        if(Estoque.isPresent()) {
            return Estoque.get();
        } else {
            throw new Exception("Estoque não encontrado");
        }
    }
}

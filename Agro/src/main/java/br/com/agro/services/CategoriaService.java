package br.com.agro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agro.entities.Categoria;
import br.com.agro.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria salvarCategoria(Categoria Categoria) {
        return categoriaRepository.save(Categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria obterCategoria(Long id) throws Exception{
        Optional<Categoria> Categoria = categoriaRepository.findById(id);
        if(Categoria.isPresent()) {
            return Categoria.get();
        } else {
            throw new Exception("Categoria não encontrado");
        }
    }
}

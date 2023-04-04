package br.com.agro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agro.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}

package br.com.agro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agro.entities.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    
}

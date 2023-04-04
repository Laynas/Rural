package br.com.agro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agro.entities.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    
}

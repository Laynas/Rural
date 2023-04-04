package br.com.agro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agro.entities.ItemOrcamento;

@Repository
public interface ItemOrcamentoRepository extends JpaRepository<ItemOrcamento, Long> {
    
}

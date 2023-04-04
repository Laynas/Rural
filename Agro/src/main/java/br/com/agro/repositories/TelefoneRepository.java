package br.com.agro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agro.entities.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    
}

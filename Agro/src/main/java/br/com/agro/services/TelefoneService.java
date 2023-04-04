package br.com.agro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agro.entities.Telefone;
import br.com.agro.repositories.TelefoneRepository;

@Service
public class TelefoneService {

    @Autowired
    TelefoneRepository telefoneRepository;

    public Telefone salvarTelefone(Telefone telefone) {
        return telefoneRepository.save(telefone);
    }

    public List<Telefone> findAll() {
        return telefoneRepository.findAll();
    }

    public void excluirTelefone(Long id) {
        telefoneRepository.deleteById(id);
    }

    public Telefone obterTelefone(Long id) throws Exception{
        Optional<Telefone> telefone = telefoneRepository.findById(id);
        if(telefone.isPresent()) {
            return telefone.get();
        } else {
            throw new Exception("Telefone não encontrado");
        }
    }
}

package br.com.agro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agro.entities.Orcamento;
import br.com.agro.repositories.OrcamentoRepository;

@Service
public class OrcamentoService {

    @Autowired
    OrcamentoRepository orcamentoRepository;

    public Orcamento salvarOrcamento(Orcamento Orcamento) {
        return orcamentoRepository.save(Orcamento);
    }

    public List<Orcamento> findAll() {
        return orcamentoRepository.findAll();
    }

    public void excluirOrcamento(Long id) {
        orcamentoRepository.deleteById(id);
    }

    public Orcamento obterOrcamento(Long id) throws Exception{
        Optional<Orcamento> Orcamento = orcamentoRepository.findById(id);
        if(Orcamento.isPresent()) {
            return Orcamento.get();
        } else {
            throw new Exception("Orcamento não encontrado");
        }
    }
}

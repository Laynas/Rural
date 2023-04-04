package br.com.agro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agro.entities.ItemOrcamento;
import br.com.agro.repositories.ItemOrcamentoRepository;

@Service
public class ItemOrcamentoService {

    @Autowired
    ItemOrcamentoRepository itemorcamentoRepository;

    public ItemOrcamento salvarItemOrcamento(ItemOrcamento ItemOrcamento) {
        return itemorcamentoRepository.save(ItemOrcamento);
    }

    public List<ItemOrcamento> findAll() {
        return itemorcamentoRepository.findAll();
    }

    public void excluirItemOrcamento(Long id) {
        itemorcamentoRepository.deleteById(id);
    }

    public ItemOrcamento obterItemOrcamento(Long id) throws Exception{
        Optional<ItemOrcamento> ItemOrcamento = itemorcamentoRepository.findById(id);
        if(ItemOrcamento.isPresent()) {
            return ItemOrcamento.get();
        } else {
            throw new Exception("ItemOrcamento não encontrado");
        }
    }
}

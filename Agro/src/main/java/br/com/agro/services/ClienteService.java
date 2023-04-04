package br.com.agro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agro.entities.Cliente;
import br.com.agro.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente obterCliente(Long id) throws Exception{
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new Exception("Cliente não encontrado");
        }
    }
}

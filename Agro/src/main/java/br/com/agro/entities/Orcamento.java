package br.com.agro.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal;

    @OneToOne(mappedBy = "orcamento")
    private Cliente cliente;

    @OneToMany(mappedBy = "orcamento")
    private List<ItemOrcamento> itemOrcamento;

    public Orcamento() {
    }

    public Orcamento(Long id, BigDecimal valorTotal, Cliente cliente, List<ItemOrcamento> itemOrcamento) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.itemOrcamento = itemOrcamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemOrcamento> getItemOrcamento() {
        return itemOrcamento;
    }

    public void setItemOrcamento(List<ItemOrcamento> itemOrcamento) {
        this.itemOrcamento = itemOrcamento;
    }
}

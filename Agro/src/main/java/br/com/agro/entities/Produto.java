package br.com.agro.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String unidadeMedida;

    @OneToMany(mappedBy = "produto")
    private List<ItemOrcamento> itemOrcamento;

    @ManyToOne
    private Estoque estoque;

    @OneToOne
    private Categoria categoria;
    
    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, 
                String unidadeMedida, List<ItemOrcamento> itemOrcamento, Estoque estoque, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.itemOrcamento = itemOrcamento;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getdescricao() {
        return descricao;
    }

    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getunidadeMedida() {
        return unidadeMedida;
    }

    public void setunidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public List<ItemOrcamento> getitemOrcamento() {
        return itemOrcamento;
    }

    public void setitemOrcamento(List<ItemOrcamento> itemOrcamento) {
        this.itemOrcamento = itemOrcamento;
    }

    public Estoque getestoque() {
        return estoque;
    }

    public void setestoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Categoria getcategoria() {
        return categoria;
    }

    public void setcategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
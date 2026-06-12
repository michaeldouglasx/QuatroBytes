package com.example.QuatroBytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "data_venda")
    private LocalDateTime dataVenda;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "Venda")
    private List<ItemVenda> itensVenda;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "status")
    String status;

    @ManyToOne
    @JoinColumn(name = "usuario_reponsavel_id")
    Usuario usuarioResponsavel;


    protected Venda() {
    }

    public Venda(LocalDateTime dataVenda, Cliente cliente, List<ItemVenda> itensVenda, BigDecimal valorTotal, String status, Usuario usuarioResponsavel) {

        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.itensVenda = itensVenda;
        this.valorTotal = valorTotal;
        this.status = status;
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
}

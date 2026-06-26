package com.example.QuatroBytes.model;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itensVenda;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_reponsavel_id")
    private Usuario usuarioResponsavel;


    protected Venda() {
    }

    public Venda(Cliente cliente, List<ItemVenda> itensVenda, Status status, Usuario usuarioResponsavel) {

        this.cliente = cliente;
        this.itensVenda = validarItensVenda(itensVenda);
        this.status = status;
        this.usuarioResponsavel = usuarioResponsavel;
        calcularValorTotal();

    }

    private void calcularValorTotal(){
        this.valorTotal = this.itensVenda.stream().map(item -> item.getSubtotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add );
    }

    private List<ItemVenda>validarItensVenda(List<ItemVenda> lista){
        if (lista == null || lista.isEmpty()){
            throw new IllegalArgumentException("Não há itens na lista");
        }
        return lista;
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
        this.itensVenda =  validarItensVenda(itensVenda);
        calcularValorTotal();
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
    @PrePersist
    public void prePresist(){
        this.dataVenda = LocalDateTime.now();
    }
}

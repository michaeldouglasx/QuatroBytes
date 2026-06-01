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
    Long id;

    @Column()
    LocalDateTime dataVenda;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    Cliente cliente;

    @OneToMany(mappedBy = "Venda")
    List<ItemVenda> itensVenda;

    @Column
    BigDecimal valorTotal;

    @Column
    String status;

    Usuario usuarioResponsavel;
}

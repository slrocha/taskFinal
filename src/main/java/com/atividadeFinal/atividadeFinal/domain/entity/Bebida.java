package com.atividadeFinal.atividadeFinal.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="bebida")
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco", precision = 20, scale = 2)
    private BigDecimal preco;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bebida")
    private List<BebidaPedida> bebidasPedidas;
}

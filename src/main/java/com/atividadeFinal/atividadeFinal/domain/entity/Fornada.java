package com.atividadeFinal.atividadeFinal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="fornada")
public class Fornada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "qntd_pizzas")
    private Integer qntdPizzas;

    @Column(name = "num_fornada")
    private Integer numFornada;

    @JsonIgnore
    @OneToMany(mappedBy ="fornada", fetch = FetchType.LAZY) //lazy otimiza a busca
    private Set<Pedido> pedidos;
}

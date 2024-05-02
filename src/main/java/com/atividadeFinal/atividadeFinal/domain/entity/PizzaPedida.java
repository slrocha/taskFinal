package com.atividadeFinal.atividadeFinal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="pizza_pedida")
public class PizzaPedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "tamanho_id")
    private Tamanho tamanho;

    @Column(name = "quantidade")
    private Integer quantidade;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "pizza_pedida_has_ingrediente",
            joinColumns =  @JoinColumn(name = "pizza_pedida_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private Set<PizzaPedida> pizzaPedida;
}

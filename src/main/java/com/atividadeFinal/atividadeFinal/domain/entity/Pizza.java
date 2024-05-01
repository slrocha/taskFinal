package com.atividadeFinal.atividadeFinal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco", precision = 20, scale = 2)
    private BigDecimal preco;

    @Column(name = "personalizado")
    private Boolean personalizado;

    @JsonIgnore
    @OneToMany(mappedBy ="pizza", fetch = FetchType.LAZY) //lazy otimiza a busca
    private Set<PizzaPedida> pizzaPedidas;
}

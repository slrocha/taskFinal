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
@Table(name ="tamanho")
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "desconto", precision = 20, scale = 2)
    private BigDecimal desconto;

    @JsonIgnore
    @OneToMany(mappedBy ="tamanho", fetch = FetchType.LAZY) //lazy otimiza a busca
    private Set<PizzaPedida> pizzaPedidas;

}

package com.atividadeFinal.atividadeFinal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fornada_id")
    private Fornada fornada;

    @Column(name = "data_hora")
    private LocalDate dataHora;

    @JsonIgnore
    @OneToMany(mappedBy ="pedido", fetch = FetchType.LAZY) //lazy otimiza a busca
    private Set<PizzaPedida> pizzaPedidas;

}

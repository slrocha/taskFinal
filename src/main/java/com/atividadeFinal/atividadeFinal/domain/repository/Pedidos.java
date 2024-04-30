package com.atividadeFinal.atividadeFinal.domain.repository;

import com.atividadeFinal.atividadeFinal.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos  extends JpaRepository<Pedido, Integer> {
}

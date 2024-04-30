package com.atividadeFinal.atividadeFinal.domain.repository;

import com.atividadeFinal.atividadeFinal.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes  extends JpaRepository<Cliente, Integer> {
}

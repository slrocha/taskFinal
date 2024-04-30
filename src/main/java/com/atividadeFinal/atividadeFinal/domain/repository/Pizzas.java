package com.atividadeFinal.atividadeFinal.domain.repository;

import com.atividadeFinal.atividadeFinal.domain.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pizzas  extends JpaRepository<Pizza, Integer> {
}

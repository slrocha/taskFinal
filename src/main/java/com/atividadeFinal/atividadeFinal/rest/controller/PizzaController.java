package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Bebida;
import com.atividadeFinal.atividadeFinal.domain.entity.Fornada;
import com.atividadeFinal.atividadeFinal.domain.entity.Pizza;
import com.atividadeFinal.atividadeFinal.domain.repository.Fornadas;
import com.atividadeFinal.atividadeFinal.domain.repository.Pizzas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    private Pizzas pizzasRepository;

    public PizzaController(Pizzas pizzas){
        this.pizzasRepository = pizzas;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza salvar(@RequestBody Pizza pizza){
        return pizzasRepository.save(pizza);
    }

    @GetMapping("{id}")
    public Pizza getPizzaById(@PathVariable Integer id){
        return pizzasRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Pizza não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pizza deletePizzaById(@PathVariable Integer id){
        return pizzasRepository.findById(id)
                .map( pizza -> {
                    pizzasRepository.delete(pizza);
                    return pizza;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pizza updatePizzaById(@PathVariable Integer id, @RequestBody Pizza pizza){
        return pizzasRepository.findById(id)
                .map( pizzaExistente -> {
                    pizzaExistente.setId(pizza.getId());
                    pizzasRepository.save(pizza);
                    return pizzaExistente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza não encontrada"));
    }

    @GetMapping
    public List<Pizza> find(Pizza filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return pizzasRepository.findAll(example);
    }
}

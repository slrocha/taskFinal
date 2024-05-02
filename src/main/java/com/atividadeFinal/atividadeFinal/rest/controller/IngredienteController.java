package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Ingrediente;
import com.atividadeFinal.atividadeFinal.domain.repository.Ingredientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class IngredienteController {

    @Autowired
    private Ingredientes ingredientesRepository;

    public IngredienteController(Ingredientes ingredientes){
        this.ingredientesRepository = ingredientes;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingrediente salvar(@RequestBody Ingrediente bebida){
        return ingredientesRepository.save(bebida);
    }

    @GetMapping("{id}")
    public Ingrediente getIngredienteById(@PathVariable Integer id){
        return ingredientesRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Ingrediente não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Ingrediente deleteIngredienteById(@PathVariable Integer id){
        return ingredientesRepository.findById(id)
                .map( tamanho -> {
                    ingredientesRepository.delete(tamanho);
                    return tamanho;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Ingrediente updateIngredienteById(@PathVariable Integer id, @RequestBody Ingrediente ingrediente){
        return ingredientesRepository.findById(id)
                .map( ingredienteExistente -> {
                    ingredienteExistente.setId(ingrediente.getId());
                    ingredientesRepository.save(ingrediente);
                    return ingredienteExistente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrada"));
    }

    @GetMapping
    public List<Ingrediente> find(Ingrediente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return ingredientesRepository.findAll(example);
    }
}

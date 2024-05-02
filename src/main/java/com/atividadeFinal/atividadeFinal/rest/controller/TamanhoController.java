package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Tamanho;
import com.atividadeFinal.atividadeFinal.domain.repository.Tamanhos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class TamanhoController {

    @Autowired
    private Tamanhos tamanhosRepository;

    public TamanhoController(Tamanhos tamanhos){
        this.tamanhosRepository = tamanhos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tamanho salvar(@RequestBody Tamanho tamanho){
        return tamanhosRepository.save(tamanho);
    }

    @GetMapping("{id}")
    public Tamanho getTamanhoById(@PathVariable Integer id){
        return tamanhosRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Tamanho não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Tamanho deleteTamanhoById(@PathVariable Integer id){
        return tamanhosRepository.findById(id)
                .map( tamanho -> {
                    tamanhosRepository.delete(tamanho);
                    return tamanho;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Tamanho updateTamanhoById(@PathVariable Integer id, @RequestBody Tamanho tamanho){
        return tamanhosRepository.findById(id)
                .map( tamanhoExistente -> {
                    tamanhoExistente.setId(tamanho.getId());
                    tamanhosRepository.save(tamanho);
                    return tamanhoExistente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrada"));
    }

    @GetMapping
    public List<Tamanho> find(Tamanho filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher); //pegar as propriedas populadas e criar o objeto
        return tamanhosRepository.findAll(example);
    }
}

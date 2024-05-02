package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Bebida;
import com.atividadeFinal.atividadeFinal.domain.repository.Bebidas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bebidas")
public class BebidaController {

    @Autowired
    private Bebidas bebidasRepository;

    public BebidaController(Bebidas bebidas){
        this.bebidasRepository = bebidas;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bebida salvar(@RequestBody Bebida bebida){
        return bebidasRepository.save(bebida);
    }

    @GetMapping("{id}")
    public Bebida getBebidaById(@PathVariable Integer id){
        return bebidasRepository.findById(id)
                .orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Bebida não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Bebida deleteBebidaById(@PathVariable Integer id){
        return bebidasRepository.findById(id)
                .map( bebida -> {
                    bebidasRepository.delete(bebida);
                    return bebida;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bebida não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Bebida updateBebidaById(@PathVariable Integer id, @RequestBody Bebida bebida){
        return bebidasRepository.findById(id)
                .map( bebidaExistente -> {
                    bebidaExistente.setId(bebida.getId());
                    bebidasRepository.save(bebida);
                    return bebidaExistente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bebida não encontrada"));
    }

    @GetMapping
    public List<Bebida> find(Bebida filtro){
        //metodo que permite buscar por todos os dados da base da table especifico bem como filtrar por qualquer coluna que contenha  na
        //base de dados, ignorando letras maiuscula/minuscula
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher); //pegar as propriedas populadas e criar o objeto
        return bebidasRepository.findAll(example);
    }
}

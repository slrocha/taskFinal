package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Bebida;
import com.atividadeFinal.atividadeFinal.domain.repository.Bebidas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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

}

package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Bebida;
import com.atividadeFinal.atividadeFinal.domain.entity.Fornada;
import com.atividadeFinal.atividadeFinal.domain.repository.Bebidas;
import com.atividadeFinal.atividadeFinal.domain.repository.Fornadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/fornadas")
public class FornadaController {

    @Autowired
    private Fornadas fornadasRepository;

    public FornadaController(Fornadas fornadas){
        this.fornadasRepository = fornadas;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornada salvar(@RequestBody Fornada fornada){
        return fornadasRepository.save(fornada);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Fornada getFornadaById(@PathVariable Integer id){
        return fornadasRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Bebida não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Fornada deleteFornadaById(@PathVariable Integer id){
        return fornadasRepository.findById(id)
                .map( bebida -> {
                    fornadasRepository.delete(bebida);
                    return bebida;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bebida não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Fornada updateFornadaById(@PathVariable Integer id, @RequestBody Fornada fornada){
        return fornadasRepository.findById(id)
                .map( fornadaExistente -> {
                    fornadaExistente.setId(fornada.getId());
                    fornadasRepository.save(fornada);
                    return fornadaExistente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bebida não encontrada"));
    }
}

package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Bebida;
import com.atividadeFinal.atividadeFinal.domain.repository.Bebidas;
import com.atividadeFinal.atividadeFinal.service.BebidaService;
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

    private BebidaService bebidaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bebida salvar(@RequestBody Bebida bebida){
        return this.bebidaService.salvar(bebida);
    }

    @GetMapping("{id}")
    public Bebida getBebidaById(@PathVariable Integer id){
        return this.bebidaService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Bebida deleteBebidaById(@PathVariable Integer id){
        return this.bebidaService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Bebida updateBebidaById(@PathVariable Integer id, @RequestBody Bebida bebida){
        return this.bebidaService.updateById(id, bebida);
    }

    @GetMapping
    public List<Bebida> find(Bebida filtro){
        return this.bebidaService.findAll(filtro);
    }
}

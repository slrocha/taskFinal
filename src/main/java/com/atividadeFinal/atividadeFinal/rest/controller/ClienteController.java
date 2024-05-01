package com.atividadeFinal.atividadeFinal.rest.controller;

import com.atividadeFinal.atividadeFinal.domain.entity.Cliente;
import com.atividadeFinal.atividadeFinal.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private Clientes clientesRepository;

    public ClienteController(Clientes clientes){
        this.clientesRepository = clientes;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente bebida){
        return clientesRepository.save(bebida);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente getClienteById(@PathVariable Integer id){
        return clientesRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Bebida não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente deleteClienteById(@PathVariable Integer id){
        return clientesRepository.findById(id)
                .map( bebida -> {
                    clientesRepository.delete(bebida);
                    return bebida;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente updateClienteById(@PathVariable Integer id, @RequestBody Cliente cliente){
        return clientesRepository.findById(id)
                .map( clienteExistente -> {
                    clienteExistente.setId(cliente.getId());
                    clientesRepository.save(cliente);
                    return clienteExistente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrada"));
    }
}

package com.atividadeFinal.atividadeFinal.service;

import com.atividadeFinal.atividadeFinal.domain.entity.Bebida;
import com.atividadeFinal.atividadeFinal.domain.repository.Bebidas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BebidaService {

    @Autowired
    private Bebidas bebidasRepository;

    public Bebida salvar(Bebida bebida){
        return bebidasRepository.save(bebida);
    }

    public List<Bebida> findAll(Bebida filtro){
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

    public Bebida updateById(Integer id, Bebida bebida){
        return bebidasRepository.findById(id)
                .map( bebidaExistente -> {
                    bebidaExistente.setId(bebida.getId());
                    bebidasRepository.save(bebida);
                    return bebidaExistente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bebida não encontrada"));
    }

    public Bebida findById(Integer id){
        return bebidasRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Bebida não encontrada"));

    }

    public Bebida delete(Integer id){
        return bebidasRepository.findById(id)
                .map( bebida -> {
                    bebidasRepository.delete(bebida);
                    return bebida;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bebida não encontrada"));
    }


}

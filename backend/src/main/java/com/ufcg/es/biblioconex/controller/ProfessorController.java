package com.ufcg.es.biblioconex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.model.Professor;
import com.ufcg.es.biblioconex.service.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/professores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    public Professor cadastrarProfessor(@RequestBody @Valid ProfessorDTO professorDTO){
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(professorService.cadastrarProfessor(professorDTO)).getBody();

    }

    @GetMapping
    public Professor buscaProfessor(){
        //TODO
        return null;
    }

    @PatchMapping
    public Professor atualizarProfessor(){
        //TODO
        return null;
    }

    @DeleteMapping
    public void deletarProfessor(){
        //TODO
    }
    
}

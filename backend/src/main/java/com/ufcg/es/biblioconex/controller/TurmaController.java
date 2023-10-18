package com.ufcg.es.biblioconex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.es.biblioconex.dto.TurmaPostPutRequestDTO;
import com.ufcg.es.biblioconex.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(
        value = "/api/turmas",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @PostMapping()
    public ResponseEntity<?> criarTurma(
            @RequestBody @Valid TurmaPostPutRequestDTO turmaPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(turmaService.criar(turmaPostPutRequestDTO));
    }

    @GetMapping("/registraTexto/{idTurma}")
    public ResponseEntity<?> registraTextoHorarioNobre(@PathVariable Long idTurma, String textoHorarioNobre){

        return ResponseEntity.status(HttpStatus.OK).body(turmaService.registraTexto(idTurma, textoHorarioNobre));
        
    }
}
package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.AlunoDTO;
import com.ufcg.es.biblioconex.service.aluno.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/v1/aluno",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AlunoController {

    @Autowired
    AlunoCriarService alunoCriarService;

    @PostMapping()
    public ResponseEntity<?> criarAluno(
            @RequestBody @Valid AlunoDTO alunoPostPutRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alunoCriarService.criar(alunoPostPutRequestDto));
    }
}

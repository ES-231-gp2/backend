package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(
        value = "/api/alunos",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AlunoController {

    @Autowired
    AlunoService alunoCriarService;


    @PostMapping()
    public ResponseEntity<?> criarAluno(
            @RequestBody @Valid AlunoPostPutRequestDTO alunoPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alunoCriarService.criar(alunoPostPutRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarAluno(
            @PathVariable Long id,
            @RequestBody @Valid AlunoPostPutRequestDTO alunoPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunoAlterarService.alterar(id, alunoPostPutRequestDTO));
    }
}

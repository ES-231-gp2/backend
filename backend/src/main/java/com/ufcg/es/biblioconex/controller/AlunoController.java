package com.ufcg.es.biblioconex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.service.AlunoService;

import jakarta.validation.Valid;
@RestController
@RequestMapping(
        value = "/api/alunos",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping()
    public ResponseEntity<?> criarAluno(
            @RequestBody @Valid AlunoPostPutRequestDTO alunoPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alunoService.criar(alunoPostPutRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarAluno(
            @PathVariable Long id,
            @RequestBody @Valid AlunoPostPutRequestDTO alunoPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunoService.alterar(id, alunoPostPutRequestDTO));
    }

    @GetMapping("/{idAluno}/horarioNobre")
    public ResponseEntity<?> textoHorarioNobre(Long idAluno){

        return ResponseEntity.status(HttpStatus.OK).body(alunoService.getTextoHorarioNobre(idAluno));
        
    }

    @PostMapping
    public ResponseEntity<?> submeterResenha(Long idAluno, String isbn, String resenha){

        return ResponseEntity.status(HttpStatus.OK).body(alunoService.submeterResenha(idAluno, isbn, resenha));
        
    }
}
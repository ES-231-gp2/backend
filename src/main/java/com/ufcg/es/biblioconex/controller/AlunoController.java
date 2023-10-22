package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.AlunoDTO;
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
    AlunoService alunoService;

    @PostMapping()
    public ResponseEntity<?> criarAluno(
            @RequestBody @Valid AlunoDTO alunoDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alunoService.cadastrarAluno(alunoDTO));
    }

    @GetMapping()
    public ResponseEntity<?> buscarAlunos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunoService.buscarAlunos(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAluno(
            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunoService.buscarAlunos(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarAluno(
            @PathVariable Long id,
            @RequestBody @Valid AlunoDTO alunoDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunoService.alterarAluno(id, alunoDTO));
    }

    @GetMapping("/{id}/turma")
    public ResponseEntity<?> buscarTurmaAluno(
            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunoService.buscarTurmaAluno(id));
    }
}

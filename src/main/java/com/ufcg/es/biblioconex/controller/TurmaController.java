package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.TurmaDTO;
import com.ufcg.es.biblioconex.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/turmas",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @PostMapping()
    public ResponseEntity<?> cadastrarTurma(
            @RequestBody @Valid TurmaDTO turmaDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(turmaService.cadastrarTurma(turmaDTO));
    }

    @GetMapping()
    public ResponseEntity<?> buscarTurmas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(turmaService.buscarTurmas(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTurma(
            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(turmaService.buscarTurmas(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarTurma(
            @PathVariable Long id,
            @RequestBody @Valid TurmaDTO turmaDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(turmaService.alterarTurma(id, turmaDTO));
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<?> buscarAlunosTurma(
            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(turmaService.buscarAlunosTurma(id));
    }
}

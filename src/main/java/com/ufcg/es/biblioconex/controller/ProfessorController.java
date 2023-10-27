package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/professores",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping()
    public ResponseEntity<?> cadastrarProfessor(
            @RequestBody @Valid ProfessorDTO professorDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professorService.cadastrarProfessor(professorDTO));
    }

    @GetMapping()
    public ResponseEntity<?> buscarProfessores() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.buscarProfessores(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProfessor(
            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.buscarProfessores(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarProfessor(
            @PathVariable Long id,
            @RequestBody @Valid ProfessorDTO professorDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.alterarProfessor(id, professorDTO));
    }

    @GetMapping("/{id}/turma")
    public ResponseEntity<?> buscarTurmaProfessor(
            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.buscarTurmasProfessor(id));
    }
}

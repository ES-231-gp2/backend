package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.ResenhaDTO;
import com.ufcg.es.biblioconex.service.ResenhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/resenhas",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ResenhaController {

    @Autowired
    ResenhaService resenhaService;

    @PostMapping()
    public ResponseEntity<?> cadastrarResenha(
            @RequestBody @Valid ResenhaDTO resenhaDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resenhaService.cadastrarResenha(resenhaDTO));
    }

    @GetMapping("/livro/{livroId}")
    public ResponseEntity<?> buscarResenhasPorLivro(
            @PathVariable Long livroId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resenhaService.buscarResenhasPorLivro(livroId));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<?> buscarResenhasPorAluno(
            @PathVariable Long alunoId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resenhaService.buscarResenhasPorAluno(alunoId));
    }

    @GetMapping("/alunos-mais-resenhas")
    public ResponseEntity<?> buscarAlunosMaisResenhas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resenhaService.buscarAlunosMaisResenhas());
    }
}

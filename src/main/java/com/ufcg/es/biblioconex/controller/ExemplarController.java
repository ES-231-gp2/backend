package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.service.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/exemplares",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ExemplarController {

    @Autowired
    ExemplarService exemplarService;

    @PostMapping("/emprestimo")
    public ResponseEntity<?> realizarEmprestimo(
            @RequestBody EmprestimoDTO emprestimoDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(exemplarService.realizarEmprestimo(emprestimoDTO));
    }

    @PostMapping("/devolucao/{id}")
    public ResponseEntity<?> realizarDevolucao(
            @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(exemplarService.realizarDevolucao(id));
    }

    @GetMapping("/historico/{usuarioId}")
    public ResponseEntity<?> consultarHistorico(
            @PathVariable Long usuarioId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(exemplarService.consultarHistorico(usuarioId));
    }
}

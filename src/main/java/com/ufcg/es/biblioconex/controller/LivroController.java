package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/livros",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping()
    public ResponseEntity<?> cadastrarLivro(
            @RequestBody @Valid LivroDTO livroDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroService.cadastrarLivro(livroDTO));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> buscarLivroPorIsbn(
            @PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.buscarLivroPorIsbn(isbn));
    }
}

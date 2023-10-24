package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
            @RequestBody @Valid LivroDTO livroDTO,
            @RequestParam Integer numeroExemplares) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroService.cadastrarLivro(livroDTO, numeroExemplares));
    }

    @GetMapping("")
    public ResponseEntity<?> buscarLivros() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.buscarLivros(null));
    }

    @GetMapping("/generos")
    public ResponseEntity<?> buscarLivrosPorGenero(
            @RequestParam Set<String> generos) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.buscarLivrosPorGenero(generos));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> buscarLivroPorIsbn(
            @PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.buscarLivroPorIsbn(isbn));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarLivro(
            @PathVariable(required = false) Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.buscarLivros(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLivro(
            @PathVariable Long id,
            @RequestBody @Valid LivroDTO livroDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.atualizarLivro(id, livroDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerLivro(
            @PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/exemplares/{id}")
    public ResponseEntity<?> adicionarExemplares(
            @PathVariable Long id,
            @RequestParam Integer numeroExemplares) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.adicionarExemplares(id, numeroExemplares));
    }

    @PutMapping("/livro-do-mes/{isbn}")
    public ResponseEntity<?> atualizarLivroDoMes(
            @PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.atualizarLivroDoMes(isbn));
    }

    @GetMapping("/livro-do-mes")
    public ResponseEntity<?> verLivroDoMes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.verLivroDoMes());
    }

    @GetMapping("/mais-lidos")
    public ResponseEntity<?> buscarMaisLidos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroService.buscarMaisLidos());
    }
}

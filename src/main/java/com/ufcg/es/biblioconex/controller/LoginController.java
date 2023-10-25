package com.ufcg.es.biblioconex.controller;

import com.ufcg.es.biblioconex.dto.UsuarioDTO;
import com.ufcg.es.biblioconex.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/login",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class LoginController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(
            @RequestBody @Valid UsuarioDTO usuarioDTO,
            @RequestParam String loginBibliotecario, @RequestParam String senhaBibliotecario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.cadastrarUsuario(loginBibliotecario, senhaBibliotecario, usuarioDTO));
    }

    @GetMapping("")
    public ResponseEntity<?> login(
            @RequestParam String login, @RequestParam String senha) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.login(login, senha));
    }
}

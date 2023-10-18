package com.ufcg.es.biblioconex.dto;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.model.Resenha;
import com.ufcg.es.biblioconex.model.Turma;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoPostPutRequestDTO {

    @JsonProperty("nome")
    @NotBlank(message = "O nome nao pode ser vazio")
    private String nome;

    @JsonProperty("email")
    @NotBlank(message = "Email nao pode ser vazio")
    @Email(message = "Email invalido")
    private String email;

    @JsonProperty("turma")
    @NotNull(message = "Turma nao pode ser vazia")
    private Turma turma;

    @JsonProperty("resenhas")
    @NotNull(message = "A coleção de resenhas não pode ser null")
    private HashMap<String,Resenha> resenhas;
}
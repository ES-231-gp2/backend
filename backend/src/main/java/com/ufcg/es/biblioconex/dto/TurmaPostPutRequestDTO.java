package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.model.Professor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurmaPostPutRequestDTO {


    @JsonProperty("nome")
    @NotBlank(message = "O nome nao pode ser vazio")
    private String nome;

    @JsonProperty("professor")
    private Professor professor;

    @JsonProperty("textoTurma")
    private String textoTurma;
}
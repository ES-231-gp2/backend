package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoGetRequestDTO{

    @JsonProperty("nome")
    String nome;

    @JsonProperty("email")
    String email;
}
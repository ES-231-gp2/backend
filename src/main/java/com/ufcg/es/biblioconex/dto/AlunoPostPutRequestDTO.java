package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.model.Turma;
import jakarta.validation.constraints.*;
import lombok.*;

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
}
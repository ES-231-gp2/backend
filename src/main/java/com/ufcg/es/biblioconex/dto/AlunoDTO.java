package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.model.Turma;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {


    @JsonProperty("nome")
    @NotBlank(message = "O nome não pode ser vazio")
    String nome;

    @JsonProperty("email")
    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    String email;

    @JsonProperty("turma")
    @NotBlank(message = "Turma não pode ser vazia")
    Turma turma;
}
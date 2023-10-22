package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    @JsonProperty("nome")
    @NotBlank(message = "O nome nao pode ser vazio")
    private String nome;

    @JsonProperty("email")
    @NotBlank(message = "Email nao pode ser vazio")
    @Email(message = "Email invalido")
    private String email;

    @JsonProperty("senha")
    @NotBlank(message = "Senha nao pode ser vazia")
    private String senha;

    @JsonProperty("turma_id")
    private Long turmaId;
}

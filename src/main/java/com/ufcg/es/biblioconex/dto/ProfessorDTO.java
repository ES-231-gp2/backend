package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {

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

    @JsonProperty("turmas_ids")
    @Builder.Default
    private Set<Long> turmasIds = new LinkedHashSet<>();
}

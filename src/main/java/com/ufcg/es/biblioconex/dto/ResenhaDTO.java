package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ResenhaDTO {

    @JsonProperty("livro_id")
    @NotNull(message = "Livro não pode ser nulo")
    private Long livroId;

    @JsonProperty("aluno_id")
    @NotNull(message = "Aluno não pode ser nulo")
    private Long alunoId;

    @JsonProperty("conteudo")
    @NotBlank(message = "Conteúdo não pode ser vazio")
    private String conteudo;
}

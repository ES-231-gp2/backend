package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.model.Aluno;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResenhasDTO {

    @JsonProperty("aluno")
    @NotNull(message = "O aluno não pode ser nulo")
    private Aluno aluno;

    @JsonProperty("total_resenhas")
    private Long totalResenhas;
}

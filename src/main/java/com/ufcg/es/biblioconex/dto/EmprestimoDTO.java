package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {

    @JsonProperty("exemplar_id")
    @NotBlank(message = "O emprestimo deve ter um exemplar")
    private Long exemplarId;

    @JsonProperty("usuario_id")
    @NotBlank(message = "O emprestimo deve ter um usuario")
    private Long usuario;

    @JsonProperty("data_devolucao_prevista")
    @NotBlank(message = "O emprestimo deve ter uma data de devolucao prevista")
    private LocalDate dataDevolucaoPrevista;
}

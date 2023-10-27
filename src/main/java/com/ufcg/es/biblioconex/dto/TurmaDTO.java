package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TurmaDTO {

    @JsonProperty("serie")
    private String serie;

    @JsonProperty("professor_id")
    private Long professor;

    @JsonProperty("alunos_id")
    @Builder.Default
    private Set<Long> alunosId = new LinkedHashSet<>();
}

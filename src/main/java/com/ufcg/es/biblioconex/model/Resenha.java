package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resenhas")
public class Resenha {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("livro")
    @ManyToOne
    private Livro livro;

    @JsonProperty("aluno")
    @ManyToOne
    private Aluno aluno;

    @JsonProperty("conteudo")
    @Lob
    private String conteudo;

    @JsonProperty("aprovada")
    @Builder.Default
    private Boolean aprovada = false;
}

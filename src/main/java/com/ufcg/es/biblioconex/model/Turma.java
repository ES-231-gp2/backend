package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "turmas")
public class Turma {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("nome")
    @Column(name = "nome", nullable = false)
    private String nome;

    @JsonProperty("alunos")
    @OneToMany(mappedBy = "turma", orphanRemoval = true)
    private Set<Aluno> alunos;
}
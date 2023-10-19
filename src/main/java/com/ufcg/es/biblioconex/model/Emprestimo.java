package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emprestimos")
public class Emprestimo {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("exemplar")
    @ManyToOne
    @JoinColumn(name = "exemplar_id")
    private Exemplar exemplar;

    @JsonProperty("usuario_id")
    @Column(nullable = false)
    private Long usuario;

    @JsonProperty("data_emprestimo")
    @Column(nullable = false, columnDefinition = "DATE")
    @Builder.Default
    private LocalDate dataEmprestimo = LocalDate.now();

    @JsonProperty("data_devolucao_prevista")
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataDevolucaoPrevista;

    @JsonProperty("data_devolucao")
    @Column(columnDefinition = "DATE")
    private LocalDate dataDevolucao;
}

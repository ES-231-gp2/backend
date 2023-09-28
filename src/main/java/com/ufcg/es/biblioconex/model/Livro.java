package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livros")
public class Livro {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("isbn")
    @Column(nullable = false, unique = true)
    private String isbn;

    @JsonProperty("titulo")
    @Column(nullable = false)
    private String titulo;

    @JsonProperty("autores")
    @ElementCollection
    @Column(nullable = false)
    private List<String> autores = new ArrayList<>();

    @JsonProperty("editora")
    @Column(nullable = false)
    private String editora;

    @JsonProperty("ano")
    @Column(nullable = false)
    private String ano;

    @JsonProperty("edicao")
    @Column(nullable = false)
    private String edicao;
}

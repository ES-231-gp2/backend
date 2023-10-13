package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
    @Builder.Default
    private Set<String> autores = new LinkedHashSet<>();

    @JsonProperty("editora")
    @Column(nullable = false)
    private String editora;

    @JsonProperty("ano")
    @Column(nullable = false)
    private String ano;

    @JsonProperty("paginas")
    private Integer paginas;

    @JsonProperty("edicao")
    private Integer edicao;

    @JsonProperty("descricao")
    @Lob
    private String descricao;

    @JsonProperty("capa")
    private String capa;


    @JsonProperty("exemplares")
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Exemplar> exemplares = new LinkedHashSet<>();

    @JsonProperty("livroDoMes")
    @Column(nullable = false)
    @Builder.Default
    private boolean livroDoMes = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

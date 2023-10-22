package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.enums.StatusExemplarEnum;
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
@Table(name = "exemplares")
public class Exemplar {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @JsonProperty("numero")
    @Column(nullable = false)
    private Integer numero;

    @JsonProperty("status")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatusExemplarEnum status = StatusExemplarEnum.DISPONIVEL;

    @JsonIgnore
    @OneToMany(mappedBy = "exemplar", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Emprestimo> emprestimos = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exemplar exemplar = (Exemplar) o;
        return Objects.equals(livro, exemplar.livro) && Objects.equals(numero, exemplar.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, numero);
    }
}

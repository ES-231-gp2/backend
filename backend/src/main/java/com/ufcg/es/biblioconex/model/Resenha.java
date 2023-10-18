package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resenha {
    
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("isbnLivro")
    @NotNull
    @NotBlank
    @NotEmpty
    private String isbn;

    @JsonProperty("resenhaAprovada")
    private Boolean resenhaAprovada;

    @JsonProperty("conteudo")
    @NotNull
    @NotBlank
    @NotEmpty
    private String conteudo;

    @JsonProperty("identificador")
    @NotNull
    @NotBlank
    @NotEmpty
    private String identificador;
}

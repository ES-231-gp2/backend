package com.ufcg.es.biblioconex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
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
@Table(name = "usuarios")
public class Usuario {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("login")
    @Column(nullable = false)
    private String login;

    @JsonProperty("senha")
    @Column(nullable = false)
    private String senha;

    @JsonProperty("tipoUsuario")
    @Column(nullable = false)
    private TipoUsuarioEnum tipoUsuario;
}

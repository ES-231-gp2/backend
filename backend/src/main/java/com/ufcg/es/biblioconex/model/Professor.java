package com.ufcg.es.biblioconex.model;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professores")
public class Professor {
    
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("nomeProfessor")
    @Column(nullable = false)
    private String nome;

    @JsonProperty("emailAcesso")
    @Column(nullable = false)
    private String emailAcesso;

    // Função de Hash utilizada: PBKDF2
    @JsonProperty("hashSenha")
    @Column(nullable = false)
    private String hashSenha;

    @JsonProperty("turmasAssociadas")
    @ElementCollection
    private HashSet<Turma> turmasAssociadas;
}

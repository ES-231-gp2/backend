package com.ufcg.es.biblioconex.dto;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.model.Turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {

    @JsonProperty("nomeProfessor")
    @NotBlank(message = "O nome do professor não pode estar em branco.")
    @NotEmpty(message = "O nome do professor não pode ser vazio.")
    @NotNull(message = "O nome do professor não pode ser null")
    @Size(min = 5, message = "O nome fornecido não é suficientemente longo")
    private String nomeProfessor;

    @JsonProperty("emailAcesso")
    @NotBlank(message = "O email de acesso não pode estar em branco.")
    @NotEmpty(message = "O email de acesso não pode ser vazio.")
    @NotNull(message = "O email de acesso não pode ser null")
    private String emailAcesso;

    @JsonProperty("hashSenha")
    @NotBlank(message = "A senha não pode estar em branco.")
    @NotEmpty(message = "A senha não pode ser vazia.")
    @NotNull(message = "A senha não pode ser null")
    private String hashSenha;

    @JsonProperty("turmasAssociadas")
    @Size(min = 1, message = "Um professor deve estar associado a pelo menos uma turma.")
    private HashSet<Turma> turmasAssociadas;
    
}

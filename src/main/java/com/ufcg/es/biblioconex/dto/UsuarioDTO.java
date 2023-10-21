package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    @JsonProperty("email")
    @NotBlank(message = "O email não pode ser vazio")
    private String email;

    @JsonProperty("senha")
    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

    @JsonProperty("tipoUsuario")
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipoUsuario;
}

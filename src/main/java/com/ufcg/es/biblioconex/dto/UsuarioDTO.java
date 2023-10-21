package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
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
    @JsonProperty("login")
    @NotBlank(message = "O login não pode ser vazio")
    private String login;

    @JsonProperty("senha")
    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

    @JsonProperty("tipoUsuario")
    @NotBlank(message = "O tipo do usuário não pode ser vazio")
    private TipoUsuarioEnum tipoUsuario;
}

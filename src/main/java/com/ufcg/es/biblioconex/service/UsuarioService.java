package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.UsuarioDTO;
import com.ufcg.es.biblioconex.model.Usuario;
import org.springframework.stereotype.Service;

public interface UsuarioService {
    public Usuario login (String login, String senha);

    public Usuario cadastrarUsuario (String login, String senha, UsuarioDTO usuarioDTO);
}

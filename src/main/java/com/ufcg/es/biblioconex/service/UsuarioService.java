package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.UsuarioDTO;
import com.ufcg.es.biblioconex.model.Usuario;

public interface UsuarioService {
    Usuario login(String login, String senha);

    Usuario cadastrarUsuario(String login, String senha, UsuarioDTO usuarioDTO);
}

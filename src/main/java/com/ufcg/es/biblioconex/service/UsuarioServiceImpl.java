package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.UsuarioDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.SenhaIncorretaException;
import com.ufcg.es.biblioconex.exception.UsuarioNaoAutorizadoException;
import com.ufcg.es.biblioconex.exception.UsuarioNaoEncontradoException;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Usuario login(String email, String senha) {
        Usuario usuario = this.usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException();
        }else{
            if(!usuario.getSenha().equals(senha)){
                throw new SenhaIncorretaException();
            }
        }
        return usuario;
    }

    @Override
    public Usuario cadastrarUsuario(String login, String senha, UsuarioDTO usuarioDTO) {
        Usuario usuario = this.login(login, senha);

        if (!usuario.getTipoUsuario().equals(TipoUsuarioEnum.BIBLIOTECARIO)){
            throw new UsuarioNaoAutorizadoException();
        }

        Usuario novoUsuario = modelMapper.map(usuarioDTO, Usuario.class);

        return this.usuarioRepository.save(novoUsuario);
    }
}

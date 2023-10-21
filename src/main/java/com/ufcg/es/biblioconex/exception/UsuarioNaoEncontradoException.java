package com.ufcg.es.biblioconex.exception;

public class UsuarioNaoEncontradoException extends BiblioConexException{
    public UsuarioNaoEncontradoException() {
        super("Nenhum usuário encontrado com esse login.");
    }
}

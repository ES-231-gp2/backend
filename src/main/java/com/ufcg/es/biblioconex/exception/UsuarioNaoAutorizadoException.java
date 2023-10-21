package com.ufcg.es.biblioconex.exception;

public class UsuarioNaoAutorizadoException extends BiblioConexException{
    public UsuarioNaoAutorizadoException() {
        super("O usuário não tem permissão para realizar essa ação!");
    }
}

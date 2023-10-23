package com.ufcg.es.biblioconex.exception;

public class EmailJaExisteException extends BiblioConexException {

    public EmailJaExisteException() {
        super("O email consultado já pertence a um usuário!");
    }
}

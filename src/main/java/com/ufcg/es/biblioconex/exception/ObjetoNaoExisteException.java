package com.ufcg.es.biblioconex.exception;

public class ObjetoNaoExisteException extends BiblioConexException {

    public ObjetoNaoExisteException() {
        super("O objeto consultado nao existe!");
    }
}

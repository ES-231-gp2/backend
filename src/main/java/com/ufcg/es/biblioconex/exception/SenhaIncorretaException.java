package com.ufcg.es.biblioconex.exception;

public class SenhaIncorretaException extends BiblioConexException{
    public SenhaIncorretaException() {
        super("Senha incorreta.");
    }
}


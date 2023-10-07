package com.ufcg.es.biblioconex.exception;

public class BiblioConexException extends RuntimeException {
    public BiblioConexException() {
        super("Erro inesperado no BiblioConex!");
    }

    public BiblioConexException(String message) {
        super(message);
    }
}

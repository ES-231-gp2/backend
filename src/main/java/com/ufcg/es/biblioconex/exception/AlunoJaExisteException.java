package com.ufcg.es.biblioconex.exception;

public class AlunoJaExisteException extends BiblioConexException {
    public AlunoJaExisteException() {
        super("Aluno ja existe!");
    }
}

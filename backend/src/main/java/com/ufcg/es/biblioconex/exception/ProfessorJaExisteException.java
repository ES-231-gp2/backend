package com.ufcg.es.biblioconex.exception;

public class ProfessorJaExisteException extends BiblioConexException {
    public ProfessorJaExisteException() {
        super("Professor já existe!");
    }
}
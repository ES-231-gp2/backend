package com.ufcg.es.biblioconex.exception;

public class AlunoNaoExisteException extends BiblioConexException{
    public AlunoNaoExisteException() {
        super("O aluno consultado nao existe!");
    }
}
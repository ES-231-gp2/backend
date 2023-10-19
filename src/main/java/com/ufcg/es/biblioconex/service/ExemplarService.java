package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.model.Emprestimo;

public interface ExemplarService {

    Emprestimo realizarEmprestimo(EmprestimoDTO emprestimoDTO);

    Emprestimo realizarDevolucao(Long id);
}

package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.model.Emprestimo;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.model.Usuario;

import java.util.List;

public interface ExemplarService {

    /**
     * Realiza um empréstimo de um exemplar.
     *
     * @param emprestimoDTO DTO com os dados do empréstimo
     * @return o empréstimo realizado
     */
    Emprestimo realizarEmprestimo(EmprestimoDTO emprestimoDTO);

    /**
     * Realiza uma devolução de um exemplar.
     *
     * @param id id do empréstimo
     * @return o empréstimo devolvido
     */
    Emprestimo realizarDevolucao(Long id);

    List<Livro> consultarHistorico(Long usuarioId);
}

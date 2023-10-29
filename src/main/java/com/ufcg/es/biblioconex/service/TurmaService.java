package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.TurmaDTO;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Texto;
import com.ufcg.es.biblioconex.model.Turma;

import java.util.List;

public interface TurmaService {

    Turma cadastrarTurma(TurmaDTO turmaDTO);

    List<Turma> buscarTurmas(Long id);

    Turma alterarTurma(Long id, TurmaDTO turmaDTO);

    List<Aluno> buscarAlunosTurma(Long id);

    Texto visualizarTexto(Long turmaId);

    Texto alterarTexto(Long turmaId, Texto texto);
}

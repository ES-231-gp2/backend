package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.AlunoDTO;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.Usuario;

import java.util.List;

public interface AlunoService {

    Aluno cadastrarAluno(AlunoDTO alunoDTO);

    List<Usuario> buscarAlunos(Long id);

    Aluno alterarAluno(Long id, AlunoDTO alunoDTO);

    Turma buscarTurmaAluno(Long id);
}

package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Resenha;

public interface AlunoService {

    Aluno criar(AlunoPostPutRequestDTO alunoPostPutRequestDto);

    Aluno alterar(Long id, AlunoPostPutRequestDTO alunoPostPutRequestDTO);

    String getTextoHorarioNobre(Long idTurma);

    Resenha submeterResenha(Long idAluno, String isbn, String conteudo);
}
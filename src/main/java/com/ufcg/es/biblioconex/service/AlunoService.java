package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.model.Aluno;

public interface AlunoService {

    Aluno criar(AlunoPostPutRequestDTO alunoPostPutRequestDto);

    Aluno alterar(Long id, AlunoPostPutRequestDTO alunoPostPutRequestDTO);
}

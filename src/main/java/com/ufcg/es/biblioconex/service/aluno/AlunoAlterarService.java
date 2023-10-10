package com.ufcg.es.biblioconex.service.aluno;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.model.Aluno;

@FunctionalInterface
public interface AlunoAlterarService {
    Aluno alterar(Long id, AlunoPostPutRequestDTO alunoPostPutRequestDTO);
}

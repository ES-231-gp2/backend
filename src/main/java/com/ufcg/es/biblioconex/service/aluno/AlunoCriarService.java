package com.ufcg.es.biblioconex.service.aluno;

import com.ufcg.es.biblioconex.dto.AlunoDTO;
import com.ufcg.es.biblioconex.model.Aluno;

@FunctionalInterface
public interface AlunoCriarService {

    Aluno criar(AlunoDTO alunoPostPutRequestDto);
}

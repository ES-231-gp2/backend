package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.TurmaPostPutRequestDTO;
import com.ufcg.es.biblioconex.model.Turma;

public interface TurmaService {

    Turma criar(TurmaPostPutRequestDTO turmaPostPutRequestDto);

    String registraTexto(Long idTurma, String texto);
}
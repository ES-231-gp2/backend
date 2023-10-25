package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.AlunoResenhasDTO;
import com.ufcg.es.biblioconex.dto.ResenhaDTO;
import com.ufcg.es.biblioconex.model.Resenha;

import java.util.List;

public interface ResenhaService {

    Resenha cadastrarResenha(ResenhaDTO resenhaDTO);

    List<Resenha> buscarResenhasPorLivro(Long livroId);

    List<Resenha> buscarResenhasPorAluno(Long alunoId);

    List<AlunoResenhasDTO> buscarAlunosMaisResenhas();
}

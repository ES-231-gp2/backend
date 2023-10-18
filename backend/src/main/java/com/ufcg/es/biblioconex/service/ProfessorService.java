package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.model.Professor;

public interface ProfessorService {
    
    Professor cadastrarProfessor(ProfessorDTO professorDTO);

    Professor buscarProfessor(Long idProfessor);

    Professor atualizarProfessor(Long idProfessor, ProfessorDTO professorDTO);

    void deletarProfessor(Long idProfessor);
}

package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.model.Professor;
import com.ufcg.es.biblioconex.model.Texto;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.Usuario;

import java.util.List;

public interface ProfessorService {

    Professor cadastrarProfessor(ProfessorDTO professorDTO);

    List<Usuario> buscarProfessores(Long id);

    Professor alterarProfessor(Long id, ProfessorDTO professorDTO);

    List<Turma> buscarTurmasProfessor(Long id);

}

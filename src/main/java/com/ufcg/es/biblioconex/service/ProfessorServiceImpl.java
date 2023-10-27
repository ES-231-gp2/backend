package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.exception.EmailJaExisteException;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Professor;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaProfessor;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.TurmaProfessorRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TurmaRepository turmaRepository;
    @Autowired
    TurmaProfessorRepository turmaProfessorRepository;

    @Override
    public Professor cadastrarProfessor(ProfessorDTO professorDTO) {
        Usuario usuario = usuarioRepository.findByEmail(professorDTO.getEmail());
        if (usuario != null) {
            throw new EmailJaExisteException();
        }

        Professor professor = usuarioRepository.save(new Professor(professorDTO.getNome(), professorDTO.getEmail(),
                professorDTO.getSenha()));
        if (professorDTO.getTurmasIds() != null) {
            mapeiaTurmas(professor, professorDTO.getTurmasIds());
        }

        return professor;
    }

    @Override
    public List<Usuario> buscarProfessores(Long id) {
        if (id != null) {
            Usuario professor = usuarioRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);
            return List.of(professor);
        }
        return usuarioRepository.findAll();
    }

    @Override
    public Professor alterarProfessor(Long id, ProfessorDTO professorDTO) {
        Professor professor = (Professor) usuarioRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);

        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());
        professor.setSenha(professorDTO.getSenha());

        mapeiaTurmas(professor, professorDTO.getTurmasIds());

        return usuarioRepository.save(professor);
    }

    @Override
    public List<Turma> buscarTurmasProfessor(Long id) {
        return turmaProfessorRepository.findTurmasByProfessorId(id);
    }

    private void mapeiaTurmas(Professor professor, Set<Long> turmasIds) {
        for (Long turmaId : turmasIds) {
            Turma turma = turmaRepository.findById(turmaId).orElseThrow(ObjetoNaoExisteException::new);
            turmaProfessorRepository.save(TurmaProfessor.builder()
                    .turma(turma)
                    .professor(professor)
                    .build());
        }
    }
}

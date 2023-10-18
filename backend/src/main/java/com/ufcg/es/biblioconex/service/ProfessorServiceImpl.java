package com.ufcg.es.biblioconex.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.exception.ProfessorJaExisteException;
import com.ufcg.es.biblioconex.model.Professor;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.repository.ProfessorRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    @Autowired
    Utilitarios utilitarios;

    @Override
    public Professor cadastrarProfessor(ProfessorDTO professorDTO) {

        if (!Utilitarios.validaEmail(professorDTO.getEmailAcesso())){
            throw new IllegalArgumentException();
        }

        professorRepository.findAll().forEach(professor -> {
            if (professor.getNome().equals(professorDTO.getNomeProfessor()) && professor.getEmailAcesso().equals(professorDTO.getEmailAcesso())) {
                throw new ProfessorJaExisteException();
            }
        });

        String senhaHashed = pbkdf2PasswordEncoder.encode(professorDTO.getHashSenha());

        Professor professor = Professor.builder()
            .nome(professorDTO.getNomeProfessor())
            .emailAcesso(professorDTO.getEmailAcesso())
            .hashSenha(senhaHashed)
            .turmasAssociadas(professorDTO.getTurmasAssociadas())
            .build();

        List<Turma> turmas = turmaRepository.findAll();
        for (Turma t:turmas){
            if (professorDTO.getTurmasAssociadas().contains(t)){
                t.setProfessor(professor);
            }
        }

        return professorRepository.save(professor);
    }

    @Override
    public Professor buscarProfessor(Long idProfessor) {

        if (idProfessor <= 0L){
            throw new IllegalArgumentException();
        }

        return professorRepository.findById(idProfessor).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Professor atualizarProfessor(Long idProfessor, ProfessorDTO professorDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarProfessor'");
    }

    @Override
    public void deletarProfessor(Long idProfessor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarProfessor'");
    }

}

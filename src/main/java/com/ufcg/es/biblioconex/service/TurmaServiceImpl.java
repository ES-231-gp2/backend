package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.TurmaDTO;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.*;
import com.ufcg.es.biblioconex.repository.TurmaAlunoRepository;
import com.ufcg.es.biblioconex.repository.TurmaProfessorRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    TurmaRepository turmaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TurmaProfessorRepository turmaProfessorRepository;
    @Autowired
    TurmaAlunoRepository turmaAlunoRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Turma cadastrarTurma(TurmaDTO turmaDTO) {
        Professor professor =
                (Professor) usuarioRepository.findById(turmaDTO.getProfessorId()).orElseThrow(ObjetoNaoExisteException::new);

        Turma turma = Turma.builder()
                .serie(turmaDTO.getSerie())
                .build();
        turma = turmaRepository.save(turma);

        turmaProfessorRepository.save(TurmaProfessor.builder()
                .turma(turma)
                .professor(professor)
                .build());

        mapeiaAlunos(turma, turmaDTO.getAlunosId());

        return turma;
    }

    @Override
    public List<Turma> buscarTurmas(Long id) {
        if (id != null) {
            Turma turma = turmaRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);
            return List.of(turma);
        }

        return turmaRepository.findAll();
    }

    @Override
    public Turma alterarTurma(Long id, TurmaDTO turmaDTO) {
        Turma turma = turmaRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);
        turma.setSerie(turmaDTO.getSerie());

        mapeiaAlunos(turma, turmaDTO.getAlunosId());

        return turmaRepository.save(turma);
    }

    @Override
    public List<Aluno> buscarAlunosTurma(Long id) {
        List<TurmaAluno> turmaAlunos = turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(id);
        return turmaAlunos.stream()
                .map(TurmaAluno::getAluno)
                .collect(Collectors.toList());
    }

    @Override
    public Texto visualizarTexto(Long turmaId) {
        Turma turma = turmaRepository.findById(turmaId).orElseThrow(ObjetoNaoExisteException::new);
        return turma.getTexto();
    }

    private void mapeiaAlunos(Turma turma, Set<Long> alunosId) {
        for (Long alunoId : alunosId) {
            Aluno aluno = (Aluno) usuarioRepository.findById(alunoId).orElseThrow(ObjetoNaoExisteException::new);
            turmaAlunoRepository.save(TurmaAluno.builder()
                    .turma(turma)
                    .aluno(aluno)
                    .build());
        }
    }

    @Override
    public Texto alterarTexto(Long turmaId, Texto texto) {
        Turma turma = turmaRepository.findById(turmaId).orElseThrow(ObjetoNaoExisteException::new);
        turma.setTexto(texto);
        return turmaRepository.save(turma).getTexto();
    }
}

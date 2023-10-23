package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.TurmaDTO;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaAluno;
import com.ufcg.es.biblioconex.repository.TurmaAlunoRepository;
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
    TurmaAlunoRepository turmaAlunoRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Turma cadastrarTurma(TurmaDTO turmaDTO) {
        Turma turma = Turma.builder()
                .serie(turmaDTO.getSerie())
                .professor(turmaDTO.getProfessor())
                .build();
        turma = turmaRepository.save(turma);

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
        turma.setProfessor(turmaDTO.getProfessor());
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

    private void mapeiaAlunos(Turma turma, Set<Long> alunosId) {
        for (Long alunoId : alunosId) {
            Aluno aluno = (Aluno) usuarioRepository.findById(alunoId).orElseThrow(ObjetoNaoExisteException::new);
            turmaAlunoRepository.save(TurmaAluno.builder()
                    .turma(turma)
                    .aluno(aluno)
                    .build());
        }
    }
}

package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.AlunoDTO;
import com.ufcg.es.biblioconex.exception.EmailJaExisteException;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaAluno;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.TurmaAlunoRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TurmaRepository turmaRepository;
    @Autowired
    TurmaAlunoRepository turmaAlunoRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Aluno cadastrarAluno(AlunoDTO alunoDTO) {
        Usuario usuario = usuarioRepository.findByLogin(alunoDTO.getLogin());
        if (usuario != null) {
            throw new EmailJaExisteException();
        }

        Aluno aluno = usuarioRepository.save(new Aluno(alunoDTO.getNome(), alunoDTO.getLogin(), alunoDTO.getSenha()));
        if (alunoDTO.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(alunoDTO.getTurmaId()).orElseThrow(ObjetoNaoExisteException::new);
            turmaAlunoRepository.save(TurmaAluno.builder()
                    .turma(turma)
                    .aluno(aluno)
                    .build());
        }

        return aluno;
    }

    @Override
    public List<Usuario> buscarAlunos(Long id) {
        if (id != null) {
            Usuario aluno = usuarioRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);
            return List.of(aluno);
        }

        return usuarioRepository.findAll();
    }

    @Override
    public Aluno alterarAluno(Long id, AlunoDTO alunoDTO) {
        Aluno aluno = (Aluno) usuarioRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);

        Usuario usuario = usuarioRepository.findByLogin(alunoDTO.getLogin());
        if (usuario != null && !aluno.getLogin().equals(alunoDTO.getLogin())) {
            throw new EmailJaExisteException();
        }

        aluno.setNome(alunoDTO.getNome());
        aluno.setLogin(alunoDTO.getLogin());
        aluno.setSenha(alunoDTO.getSenha());

        if (alunoDTO.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(alunoDTO.getTurmaId()).orElseThrow(ObjetoNaoExisteException::new);
            turmaAlunoRepository.save(TurmaAluno.builder()
                    .turma(turma)
                    .aluno(aluno)
                    .build());
        }
        return usuarioRepository.save(aluno);
    }

    @Override
    public Turma buscarTurmaAluno(Long id) {
        return turmaAlunoRepository.findTurmaByAlunoId(id).getTurma();
    }
}

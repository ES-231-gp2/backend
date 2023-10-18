package com.ufcg.es.biblioconex.service;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.exception.AlunoJaExisteException;
import com.ufcg.es.biblioconex.exception.AlunoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Resenha;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.repository.AlunoRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Utilitarios utilitarios;

    @Override
    public Aluno criar(AlunoPostPutRequestDTO alunoPostPutRequestDTO) {
        alunoRepository.findAll().forEach(aluno -> {
            if (aluno.getNome().equals(alunoPostPutRequestDTO.getNome()) && aluno.getEmail().equals(alunoPostPutRequestDTO.getEmail())) {
                throw new AlunoJaExisteException();
            }
        });
        Aluno aluno = modelMapper.map(alunoPostPutRequestDTO, Aluno.class);
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno alterar(Long id, AlunoPostPutRequestDTO alunoPostPutRequestDTO) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNaoExisteException::new);

        modelMapper.map(alunoPostPutRequestDTO, aluno);
        return alunoRepository.save(aluno);
    }

    @Override
    public String getTextoHorarioNobre(Long idTurma) {
        Turma turma = turmaRepository.findById(idTurma).orElseThrow(NoSuchElementException::new);
        return turma.getTextoTurma();
    }

    @Override
    public Resenha submeterResenha(Long idAluno, String isbn, String conteudo) {
        
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(AlunoNaoExisteException::new);

        if ( isbn == null || isbn.isEmpty() || isbn.isBlank() ||
             conteudo == null || conteudo.isEmpty() || conteudo.isBlank() || conteudo.length() < 100){
            throw new IllegalArgumentException();
        }

        String identificador = utilitarios.identificadorResenha();
        Resenha resenha = Resenha.builder()
            .isbn(isbn)
            .resenhaAprovada(null)
            .conteudo(conteudo)
            .identificador(identificador)
            .build();

        aluno.getResenhas().put(resenha.getIdentificador(),resenha);
        
        return resenha;

    }
}
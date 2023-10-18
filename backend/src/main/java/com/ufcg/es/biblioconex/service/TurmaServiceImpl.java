package com.ufcg.es.biblioconex.service;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.es.biblioconex.dto.TurmaPostPutRequestDTO;
import com.ufcg.es.biblioconex.exception.TurmaJaExisteException;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.repository.TurmaRepository;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Turma criar(TurmaPostPutRequestDTO turmaPostPutRequestDto) {
        turmaRepository.findAll().forEach(turma -> {
            if (turma.getNome().equals(turmaPostPutRequestDto.getNome())) {
                throw new TurmaJaExisteException();
            }
        });
        Turma turma = modelMapper.map(turmaPostPutRequestDto, Turma.class);
        return turmaRepository.save(turma);
    }

    @Override
    public String registraTexto(Long idTurma, String texto) {

        Turma t = turmaRepository.findById(idTurma).orElseThrow(NoSuchElementException::new);
        t.setTextoTurma(texto);
        return texto;

    }
}
package com.ufcg.es.biblioconex.service.aluno;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.exception.AlunoJaExisteException;
import com.ufcg.es.biblioconex.repository.AlunoRepository;
import com.ufcg.es.biblioconex.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class AlunoCriarServiceImpl implements AlunoCriarService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    ModelMapper modelMapper;

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
}

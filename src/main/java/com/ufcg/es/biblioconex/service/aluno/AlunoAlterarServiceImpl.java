package com.ufcg.es.biblioconex.service.aluno;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.exception.AlunoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.repository.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoAlterarServiceImpl implements AlunoAlterarService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Aluno alterar(Long id, AlunoPostPutRequestDTO alunoPostPutRequestDTO) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNaoExisteException::new);

        modelMapper.map(alunoPostPutRequestDTO, aluno);
        return alunoRepository.save(aluno);
    }

}

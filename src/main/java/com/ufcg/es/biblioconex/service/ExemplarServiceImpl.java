package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.enums.StatusExemplarEnum;
import com.ufcg.es.biblioconex.exception.BiblioConexException;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Emprestimo;
import com.ufcg.es.biblioconex.model.Exemplar;
import com.ufcg.es.biblioconex.repository.EmprestimoRepository;
import com.ufcg.es.biblioconex.repository.ExemplarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ExemplarServiceImpl implements ExemplarService {

    @Autowired
    EmprestimoRepository emprestimoRepository;
    @Autowired
    ExemplarRepository exemplarRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Emprestimo realizarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Exemplar exemplar =
                exemplarRepository.findById(emprestimoDTO.getExemplarId()).orElseThrow(ObjetoNaoExisteException::new);

        Emprestimo emprestimo = Emprestimo.builder()
                .exemplar(exemplar)
                .usuario(emprestimoDTO.getUsuario())
                .dataDevolucaoPrevista(emprestimoDTO.getDataDevolucaoPrevista())
                .build();

        if (emprestimo.getDataEmprestimo().isAfter(emprestimo.getDataDevolucaoPrevista())) {
            throw new BiblioConexException("A data de emprestimo deve ser antes da data de devolucao prevista");
        }

        Set<Emprestimo> emprestimos = exemplar.getEmprestimos();
        emprestimos.add(emprestimo);

        exemplar.setEmprestimos(emprestimos);
        exemplar.setStatus(StatusExemplarEnum.EMPRESTADO);

        exemplarRepository.save(exemplar);
        return emprestimo;
    }

    @Override
    public Emprestimo realizarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);
        Exemplar exemplar = emprestimo.getExemplar();

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimoRepository.save(emprestimo);

        exemplar.setStatus(StatusExemplarEnum.DISPONIVEL);
        exemplar.getLivro().setLeituras(exemplar.getLivro().getLeituras() + 1);
        exemplarRepository.save(exemplar);

        return emprestimo;
    }
}

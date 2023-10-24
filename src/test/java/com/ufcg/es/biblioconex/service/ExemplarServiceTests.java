package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.enums.StatusExemplarEnum;
import com.ufcg.es.biblioconex.exception.BiblioConexException;
import com.ufcg.es.biblioconex.model.Emprestimo;
import com.ufcg.es.biblioconex.model.Exemplar;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.repository.EmprestimoRepository;
import com.ufcg.es.biblioconex.repository.ExemplarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ExemplarServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ExemplarServiceTests {
    @MockBean
    private EmprestimoRepository emprestimoRepository;

    @MockBean
    private ExemplarRepository exemplarRepository;

    @Autowired
    private ExemplarServiceImpl exemplarServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test:
     * {@link ExemplarServiceImpl#realizarEmprestimo(EmprestimoDTO)}
     */
    @Test
    void testRealizarEmprestimo() {
        when(exemplarRepository.findById(Mockito.<Long>any())).thenThrow(new BiblioConexException("An error occurred"));
        assertThrows(BiblioConexException.class,
                () -> exemplarServiceImpl.realizarEmprestimo(new EmprestimoDTO()));
        verify(exemplarRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link ExemplarServiceImpl#realizarEmprestimo(EmprestimoDTO)}
     */

}


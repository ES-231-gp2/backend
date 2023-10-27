package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.repository.EmprestimoRepository;
import com.ufcg.es.biblioconex.repository.ExemplarRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

}


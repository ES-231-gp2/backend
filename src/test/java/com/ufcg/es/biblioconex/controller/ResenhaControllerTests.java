package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.ResenhaDTO;
import com.ufcg.es.biblioconex.service.ResenhaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ResenhaController.class})
@ExtendWith(SpringExtension.class)
class ResenhaControllerTests {
    @Autowired
    private ResenhaController resenhaController;

    @MockBean
    private ResenhaService resenhaService;

    /**
     * Method under test: {@link ResenhaController#buscarResenhasPorLivro(Long)}
     */
    @Test
    void testBuscarResenhasPorLivro() throws Exception {
        when(resenhaService.buscarResenhasPorLivro(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/resenhas/livro/{livroId}", 1L);
        MockMvcBuilders.standaloneSetup(resenhaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ResenhaController#buscarResenhasPorAluno(Long)}
     */
    @Test
    void testBuscarResenhasPorAluno() throws Exception {
        when(resenhaService.buscarResenhasPorAluno(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/resenhas/aluno/{alunoId}", 1L);
        MockMvcBuilders.standaloneSetup(resenhaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ResenhaController#buscarAlunosMaisResenhas()}
     */
    @Test
    void testBuscarAlunosMaisResenhas() throws Exception {
        when(resenhaService.buscarAlunosMaisResenhas()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/resenhas/alunos-mais-resenhas");
        MockMvcBuilders.standaloneSetup(resenhaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ResenhaController#cadastrarResenha(ResenhaDTO)}
     */
    @Test
    void testCadastrarResenha() throws Exception {
        ResenhaDTO resenhaDTO = new ResenhaDTO();
        resenhaDTO.setAlunoId(1L);
        resenhaDTO.setConteudo("Conteudo");
        resenhaDTO.setLivroId(1L);
        String content = (new ObjectMapper()).writeValueAsString(resenhaDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/resenhas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(resenhaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}


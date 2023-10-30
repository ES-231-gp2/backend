package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.TurmaDTO;
import com.ufcg.es.biblioconex.model.Texto;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.service.TurmaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TurmaController.class})
@ExtendWith(SpringExtension.class)
class TurmaControllerTests {
    @Autowired
    private TurmaController turmaController;

    @MockBean
    private TurmaService turmaService;

    /**
     * Method under test: {@link TurmaController#buscarTurmas()}
     */
    @Test
    void testBuscarTurmas() throws Exception {
        when(turmaService.buscarTurmas(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/turmas");
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TurmaController#adicionarTextoTurma(Long, Texto)}
     */
    @Test
    void testAdicionarTextoTurma() throws Exception {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");
        when(turmaService.alterarTexto(Mockito.<Long>any(), Mockito.any())).thenReturn(texto);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");
        String content = (new ObjectMapper()).writeValueAsString(texto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/turmas/texto/adiciona/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"nome\":\"Nome\",\"resumo\":\"Resumo\",\"conteudo\":\"Conteudo\"}"));
    }

    /**
     * Method under test: {@link TurmaController#alterarTurma(Long, TurmaDTO)}
     */
    @Test
    void testAlterarTurma() throws Exception {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        when(turmaService.alterarTurma(Mockito.<Long>any(), Mockito.any())).thenReturn(turma);

        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setAlunosId(new HashSet<>());
        turmaDTO.setProfessorId(1L);
        turmaDTO.setSerie("Serie");
        String content = (new ObjectMapper()).writeValueAsString(turmaDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/turmas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"serie\":\"Serie\",\"texto\":{\"id\":1,\"nome\":\"Nome\"," +
                                        "\"resumo\":\"Resumo\",\"conteudo\":\"Conteudo\"}}"));
    }

    /**
     * Method under test: {@link TurmaController#buscarAlunosTurma(Long)}
     */
    @Test
    void testBuscarAlunosTurma() throws Exception {
        when(turmaService.buscarAlunosTurma(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/turmas/{id}/alunos", 1L);
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TurmaController#buscarTurma(Long)}
     */
    @Test
    void testBuscarTurma() throws Exception {
        when(turmaService.buscarTurmas(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/turmas/{id}", 1L);
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TurmaController#buscarTurma(Long)}
     */
    @Test
    void testBuscarTurma2() throws Exception {
        when(turmaService.buscarTurmas(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/turmas/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TurmaController#cadastrarTurma(TurmaDTO)}
     */
    @Test
    void testCadastrarTurma() throws Exception {
        when(turmaService.buscarTurmas(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setAlunosId(new HashSet<>());
        turmaDTO.setProfessorId(1L);
        turmaDTO.setSerie("Serie");
        String content = (new ObjectMapper()).writeValueAsString(turmaDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/turmas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TurmaController#visualizarTextoTurma(Long)}
     */
    @Test
    void testVisualizarTextoTurma() throws Exception {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");
        when(turmaService.visualizarTexto(Mockito.<Long>any())).thenReturn(texto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/turmas/texto/{id}", 1L);
        MockMvcBuilders.standaloneSetup(turmaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"nome\":\"Nome\",\"resumo\":\"Resumo\",\"conteudo\":\"Conteudo\"}"));
    }
}


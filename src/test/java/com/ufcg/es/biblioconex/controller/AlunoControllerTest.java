package com.ufcg.es.biblioconex.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.AlunoDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Texto;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.service.AlunoService;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {AlunoController.class})
@ExtendWith(SpringExtension.class)
class AlunoControllerTest {
    @Autowired
    private AlunoController alunoController;

    @MockBean
    private AlunoService alunoService;

    /**
     * Method under test: {@link AlunoController#buscarAlunos()}
     */
    @Test
    void testBuscarAlunos() throws Exception {
        when(alunoService.buscarAlunos(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alunos");
        MockMvcBuilders.standaloneSetup(alunoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AlunoController#alterarAluno(Long, AlunoDTO)}
     */
    @Test
    void testAlterarAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(alunoService.alterarAluno(Mockito.<Long>any(), Mockito.<AlunoDTO>any())).thenReturn(aluno);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setEmail("jane.doe@example.org");
        alunoDTO.setNome("Nome");
        alunoDTO.setSenha("Senha");
        alunoDTO.setTurmaId(1L);
        String content = (new ObjectMapper()).writeValueAsString(alunoDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/alunos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(alunoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nome\":\"Nome\",\"email\":\"jane.doe@example.org\",\"senha\":\"Senha\",\"tipo_usuario\":\"ALUNO\"}"));
    }

    /**
     * Method under test: {@link AlunoController#buscarTurmaAluno(Long)}
     */
    @Test
    void testBuscarTurmaAluno() throws Exception {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        when(alunoService.buscarTurmaAluno(Mockito.<Long>any())).thenReturn(turma);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alunos/{id}/turma", 1L);
        MockMvcBuilders.standaloneSetup(alunoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"serie\":\"Serie\",\"texto\":{\"id\":1,\"nome\":\"Nome\",\"resumo\":\"Resumo\",\"conteudo\":\"Conteudo\"}}"));
    }

    /**
     * Method under test: {@link AlunoController#buscarAluno(Long)}
     */
    @Test
    void testBuscarAluno() throws Exception {
        when(alunoService.buscarAlunos(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alunos/{id}", 1L);
        MockMvcBuilders.standaloneSetup(alunoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AlunoController#buscarAluno(Long)}
     */
    @Test
    void testBuscarAluno2() throws Exception {
        when(alunoService.buscarAlunos(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alunos/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(alunoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AlunoController#cadastrarAluno(AlunoDTO)}
     */
    @Test
    void testCadastrarAluno() throws Exception {
        when(alunoService.buscarAlunos(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setEmail("jane.doe@example.org");
        alunoDTO.setNome("Nome");
        alunoDTO.setSenha("Senha");
        alunoDTO.setTurmaId(1L);
        String content = (new ObjectMapper()).writeValueAsString(alunoDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(alunoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


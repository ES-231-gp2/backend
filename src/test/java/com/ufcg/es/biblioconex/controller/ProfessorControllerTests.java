package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.model.Professor;
import com.ufcg.es.biblioconex.service.ProfessorService;
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

@ContextConfiguration(classes = {ProfessorController.class})
@ExtendWith(SpringExtension.class)
class ProfessorControllerTests {
    @Autowired
    private ProfessorController professorController;

    @MockBean
    private ProfessorService professorService;

    /**
     * Method under test: {@link ProfessorController#buscarProfessores()}
     */
    @Test
    void testBuscarProfessores() throws Exception {
        when(professorService.buscarProfessores(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/professores");
        MockMvcBuilders.standaloneSetup(professorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProfessorController#alterarProfessor(Long, ProfessorDTO)}
     */
    @Test
    void testAlterarProfessor() throws Exception {
        Professor professor = new Professor();
        professor.setEmail("jane.doe@example.org");
        professor.setId(1L);
        professor.setNome("Nome");
        professor.setSenha("Senha");
        professor.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(professorService.alterarProfessor(Mockito.<Long>any(), Mockito.any())).thenReturn(professor);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setEmail("jane.doe@example.org");
        professorDTO.setNome("Nome");
        professorDTO.setSenha("Senha");
        professorDTO.setTurmasIds(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(professorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/professores/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(professorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nome\":\"Nome\",\"email\":\"jane.doe@example.org\",\"senha\":\"Senha\"," +
                                        "\"tipo_usuario\":\"ALUNO\"}"));
    }

    /**
     * Method under test: {@link ProfessorController#buscarProfessor(Long)}
     */
    @Test
    void testBuscarProfessor() throws Exception {
        when(professorService.buscarProfessores(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/professores/{id}", 1L);
        MockMvcBuilders.standaloneSetup(professorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProfessorController#buscarProfessor(Long)}
     */
    @Test
    void testBuscarProfessor2() throws Exception {
        when(professorService.buscarProfessores(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/professores/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(professorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProfessorController#buscarTurmaProfessor(Long)}
     */
    @Test
    void testBuscarTurmaProfessor() throws Exception {
        when(professorService.buscarTurmasProfessor(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/professores/{id}/turma", 1L);
        MockMvcBuilders.standaloneSetup(professorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProfessorController#cadastrarProfessor(ProfessorDTO)}
     */
    @Test
    void testCadastrarProfessor() throws Exception {
        when(professorService.buscarProfessores(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setEmail("jane.doe@example.org");
        professorDTO.setNome("Nome");
        professorDTO.setSenha("Senha");
        professorDTO.setTurmasIds(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(professorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/professores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(professorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


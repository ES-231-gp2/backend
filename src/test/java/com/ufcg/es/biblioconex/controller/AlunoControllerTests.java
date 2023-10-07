package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ufcg.es.biblioconex.dto.AlunoDTO;
import com.ufcg.es.biblioconex.exception.CustomErrorType;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.repository.AlunoRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Alunos")
public class AlunoControllerTests {

    final String URI_ALUNOS = "/v1/alunos";

    @Autowired
    MockMvc driver;

    @Autowired
    AlunoRepository alunoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Aluno aluno;

    AlunoDTO alunoDTO;

    Turma turma;

    @Autowired
    TurmaRepository turmaRepository;

    @BeforeEach
    void setup() {
        turma = turmaRepository.save(Turma.builder().build());
        // Object Mapper support para LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        aluno = Aluno.builder()
                .nome("Aluno Ponto da Silva")
                .turma(turma)
                .email("alunoponto@gmail.com")
                .build();
        alunoDTO = AlunoDTO.builder()
                .nome("Aluno Ponto da Silva")
                .turma(turma)
                .email("alunoponto@gmail.com")
                .build();
    }

    @AfterEach
    void tearDown() {
        alunoRepository.deleteAll();
    }


    @Test
    @Transactional
    @DisplayName("Quando crio um aluno com dados válidos")
    void test01() throws Exception {

        String responseJsonString = driver.perform(post(URI_ALUNOS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();

        assertAll(
                () -> assertEquals(1, alunoRepository.count()),
                () -> assertEquals(alunoDTO.getNome(), resultado.getNome()),
                () -> assertEquals(alunoDTO.getTurma(), resultado.getTurma()),
                () -> assertEquals(alunoDTO.getEmail(), resultado.getEmail())
        );

    }

    @Test
    @Transactional
    @DisplayName("Quando tento criar um aluno com nome inválido")
    void test02() throws Exception {

        alunoDTO.setNome("");

        String responseJsonString = driver.perform(post(URI_ALUNOS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

        assertEquals("Erros de validacao encontrados", error.getMessage());
        assertTrue(error.getErrors().contains("O nome não pode ser vazio"));
    }

    @Test
    @Transactional
    @DisplayName("Quando tento criar um aluno com turma inválida")
    void test03() throws Exception {

        alunoDTO.setTurma(null);

        String responseJsonString = driver.perform(post(URI_ALUNOS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

        assertEquals("Erros de validacao encontrados", error.getMessage());
        assertTrue(error.getErrors().contains("Turma nao pode ser vazia"));
    }

    @Test
    @Transactional
    @DisplayName("Quando tento criar um aluno com email inválido")
    void test04() throws Exception {

        alunoDTO.setEmail("emailinvalido");

        String responseJsonString = driver.perform(post(URI_ALUNOS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

        assertEquals("Erros de validacao encontrados", error.getMessage());
        assertTrue(error.getErrors().contains("Email invalido"));
    }

    @Test
    @Transactional
    @DisplayName("Quando tento criar um aluno com email vazio")
    void test05() throws Exception {

        alunoDTO.setEmail("");

        String responseJsonString = driver.perform(post(URI_ALUNOS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

        assertEquals("Erros de validacao encontrados", error.getMessage());
        assertTrue(error.getErrors().contains("Email nao pode ser vazio"));
    }

}

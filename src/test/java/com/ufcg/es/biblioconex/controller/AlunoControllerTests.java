package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
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
@Transactional
@DisplayName("Testes do controlador de Alunos")
public class AlunoControllerTests {

    final String URI_ALUNOS = "/v1/alunos";

    @Autowired
    MockMvc driver;

    @Autowired
    AlunoRepository alunoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Aluno aluno;

    AlunoPostPutRequestDTO alunoPostPutRequestDTO;

    Turma turma;

    @Autowired
    TurmaRepository turmaRepository;

    @BeforeEach
    void setup() {
        turma = turmaRepository.save(Turma.builder()
                .nome("Turma 1")
                .build());
        // Object Mapper support para LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        aluno = alunoRepository.save(Aluno.builder()
                .nome("Aluno Ponto da Silva")
                .turma(turma)
                .email("alunoponto@gmail.com")
                .build());
        alunoPostPutRequestDTO = AlunoPostPutRequestDTO.builder()
                .nome("Aluno Ponto da Silva")
                .turma(turma)
                .email("alunoponto@gmail.com")
                .build();
    }

    @AfterEach
    void tearDown() {
        alunoRepository.deleteAll();
    }


    @Nested
    @DisplayName("Testes do método POST")
    class AlunoPost {
        @Test
        @DisplayName("Quando crio um aluno com dados válidos")
        void test01() throws Exception {
            //Act
            alunoRepository.deleteAll();
            // Arrange
            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();
            //Assert
            assertAll(
                    () -> assertEquals(1, alunoRepository.count()),
                    () -> assertEquals(alunoPostPutRequestDTO.getNome(), resultado.getNome()),
                    () -> assertEquals(alunoPostPutRequestDTO.getTurma(), resultado.getTurma()),
                    () -> assertEquals(alunoPostPutRequestDTO.getEmail(), resultado.getEmail())
            );

        }

        @Test
        @Transactional
        @DisplayName("Quando tento criar um aluno com nome inválido")
        void test02() throws Exception {
            //Act
            alunoRepository.deleteAll();
            alunoPostPutRequestDTO.setNome("");
            //Arrange
            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);
            //Assert
            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("O nome nao pode ser vazio"));
        }

        @Test
        @DisplayName("Quando tento criar um aluno com turma inválida")
        void test03() throws Exception {
            //Act
            alunoRepository.deleteAll();
            alunoPostPutRequestDTO.setTurma(null);
            //Arrange
            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);
            //Assert
            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Turma nao pode ser vazia"));
        }

        @Test
        @DisplayName("Quando tento criar um aluno com email inválido")
        void test04() throws Exception {
            //Act
            alunoRepository.deleteAll();
            alunoPostPutRequestDTO.setEmail("emailinvalido");
            //Arrange
            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);
            //Assert
            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Email invalido"));
        }

        @Test
        @DisplayName("Quando tento criar um aluno com email vazio")
        void test05() throws Exception {
            //Act
            alunoRepository.deleteAll();
            alunoPostPutRequestDTO.setEmail("");
            //Arrange
            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);
            //Assert
            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Email nao pode ser vazio"));
        }

        @Test
        @DisplayName("Quando tento criar um aluno que já existe")
        void test06() throws Exception {
            //Act
            //Arrange
            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);
            //Assert
            assertEquals("Aluno ja existe!", error.getMessage());
        }
    }
}

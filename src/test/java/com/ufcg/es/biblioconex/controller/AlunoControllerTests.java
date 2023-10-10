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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        turma = turmaRepository.save(Turma.builder().build());
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

            alunoPostPutRequestDTO.setNome("");

            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("O nome nao pode ser vazio"));
        }

        @Test
        @DisplayName("Quando tento criar um aluno com turma inválida")
        void test03() throws Exception {

            alunoPostPutRequestDTO.setTurma(null);

            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Turma nao pode ser vazia"));
        }

        @Test
        @DisplayName("Quando tento criar um aluno com email inválido")
        void test04() throws Exception {

            alunoPostPutRequestDTO.setEmail("emailinvalido");

            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Email invalido"));
        }

        @Test
        @DisplayName("Quando tento criar um aluno com email vazio")
        void test05() throws Exception {

            alunoPostPutRequestDTO.setEmail("");

            String responseJsonString = driver.perform(post(URI_ALUNOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Email nao pode ser vazio"));
        }
    }

    @Nested
    @DisplayName("Testes do método PUT")
    class AlunoPut {
        @Test
        @DisplayName("Quando alteramos o nome de um aluno")
        void test01() throws Exception {
            // Arrange
            alunoPostPutRequestDTO.setNome("Aluno Ponto da Silva Alterado");
            // Act
            String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(1, alunoRepository.count()),
                    () -> assertEquals(alunoPostPutRequestDTO.getNome(), resultado.getNome())
            );
        }

        @Test
        @DisplayName("Quando alteramos a turma de um aluno")
        void test02() throws Exception {
            // Arrange
            Turma turma2 = turmaRepository.save(Turma.builder().build());
            alunoPostPutRequestDTO.setTurma(turma2);
            // Act
            String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(1, alunoRepository.count()),
                    () -> assertEquals(alunoPostPutRequestDTO.getTurma().getId(), resultado.getTurma().getId())
            );
        }

        @Test
        @DisplayName("Quando alteramos o email de um aluno")
        void test03() throws Exception {
            // Arrange
            alunoPostPutRequestDTO.setEmail("alunopontoalterado@gmail.com");
            // Act
            String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(1, alunoRepository.count()),
                    () -> assertEquals(alunoPostPutRequestDTO.getEmail(), resultado.getEmail()));
        }

        @Test
        @DisplayName("Quando tento alterar um aluno com nome inválido")
        void test04() throws Exception {

            alunoPostPutRequestDTO.setNome("");

            String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("O nome nao pode ser vazio"));
        }

        @Test
        @DisplayName("Quando tento alterar um aluno com turma inválida")
        void test05() throws Exception {

            alunoPostPutRequestDTO.setTurma(null);

            String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Turma nao pode ser vazia"));
        }

        @Test
        @DisplayName("Quando tento alterar um aluno com email inválido")
        void test06() throws Exception {

            alunoPostPutRequestDTO.setEmail("emailinvalido");

            String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Email invalido"));
        }

        @Test
        @DisplayName("Quando tento alterar um aluno com email vazio")
        void test07() throws Exception {

            alunoPostPutRequestDTO.setEmail("");

            String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType error = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertEquals("Erros de validacao encontrados", error.getMessage());
            assertTrue(error.getErrors().contains("Email nao pode ser vazio"));
        }
    }

}
package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.model.Aluno;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("Testes do Serviço de criação de aluno")
class AlunoCriarServiceTests extends AlunoBaseTests {

    @Test
    @DisplayName("Quando criamos um aluno primeiro")
    void quandoCriamosUmAlunoPrimeiro() {
        // Arrange
        // nenhuma necessidade além do setup()

        // Act
        Aluno resultado = driver.criar(alunoPostPutRequestDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, alunoRepository.count()),
                () -> assertEquals(aluno.getNome(), resultado.getNome()),
                () -> assertEquals(aluno.getTurma().getNome(), resultado.getTurma().getNome()),
                () -> assertEquals(aluno.getEmail(), resultado.getEmail())
        );
    }

    @Test
    @DisplayName("Quando criamos um aluno segundo ou posterior")
    void quandoCriamosUmAlunoSegundoOuPosterior() {
        // Arrange
        alunoRepository.save(aluno);
        alunoPostPutRequestDTO.setNome("Aluno Ponto da Silva 2");
        alunoPostPutRequestDTO.setEmail("aluno2@gmail.com");
        // Act
        Aluno resultado = driver.criar(alunoPostPutRequestDTO);

        // Assert
        assertAll(
                () -> assertEquals(2, alunoRepository.count()),
                () -> assertEquals(alunoPostPutRequestDTO.getNome(), resultado.getNome()),
                () -> assertEquals(alunoPostPutRequestDTO.getTurma().getNome(), resultado.getTurma().getNome()),
                () -> assertEquals(alunoPostPutRequestDTO.getEmail(), resultado.getEmail())
        );
    }
}

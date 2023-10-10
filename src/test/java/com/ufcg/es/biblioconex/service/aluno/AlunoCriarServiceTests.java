package com.ufcg.es.biblioconex.service.aluno;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.repository.AlunoRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    AlunoCriarService driver;


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
                () -> assertEquals(aluno.getTurma(), resultado.getTurma()),
                () -> assertEquals(aluno.getEmail(), resultado.getEmail())
        );
    }

    @Test
    @DisplayName("Quando criamos um aluno segundo ou posterior")
    void quandoCriamosUmAlunoSegundoOuPosterior() {
        // Arrange
        alunoRepository.save(aluno);

        // Act
        Aluno resultado = driver.criar(alunoPostPutRequestDTO);

        // Assert
        assertAll(
                () -> assertEquals(2, alunoRepository.count()),
                () -> assertEquals(aluno.getNome(), resultado.getNome()),
                () -> assertEquals(aluno.getTurma(), resultado.getTurma()),
                () -> assertEquals(aluno.getEmail(), resultado.getEmail())
        );
    }
}

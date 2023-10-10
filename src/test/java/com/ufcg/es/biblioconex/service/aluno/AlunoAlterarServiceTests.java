package com.ufcg.es.biblioconex.service.aluno;

import com.ufcg.es.biblioconex.exception.AlunoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Testes do Serviço de alteração de aluno")
public class AlunoAlterarServiceTests extends AlunoBaseTests {

    @Autowired
    AlunoAlterarService driver;

    @Test
    @DisplayName("Quando alteramos o nome de um aluno")
    void quandoAlteramosNomeAluno() {
        // Arrange
        alunoRepository.save(aluno);
        alunoPostPutRequestDTO.setNome("Aluno Ponto da Silva Alterado");
        // Act
        Aluno resultado = driver.alterar(aluno.getId(), alunoPostPutRequestDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, alunoRepository.count()),
                () -> assertEquals(alunoPostPutRequestDTO.getNome(), resultado.getNome())
        );
    }

    @Test
    @DisplayName("Quando alteramos a turma de um aluno")
    void quandoAlteramosTurmaAluno() {
        // Arrange
        alunoRepository.save(aluno);
        Turma turma2 = turmaRepository.save(Turma.builder().nome("Turma 2").build());
        alunoPostPutRequestDTO.setTurma(turma2);
        // Act
        Aluno resultado = driver.alterar(aluno.getId(), alunoPostPutRequestDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, alunoRepository.count()),
                () -> assertEquals(alunoPostPutRequestDTO.getTurma().getNome(), resultado.getTurma().getNome())
        );
    }

    @Test
    @DisplayName("Quando alteramos o email de um aluno")
    void quandoAlteramosEmailAluno() {
        // Arrange
        alunoRepository.save(aluno);
        alunoPostPutRequestDTO.setEmail("alunopontoalterado@gmail.com");
        // Act
        Aluno resultado = driver.alterar(aluno.getId(), alunoPostPutRequestDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, alunoRepository.count()),
                () -> assertEquals(alunoPostPutRequestDTO.getEmail(), resultado.getEmail())
        );
    }

    @Test
    @DisplayName("Quando tentamos alterar um aluno que não existe")
    void quandoTentamosAlterarAlunoQueNaoExiste() {
        // Arrange
        alunoRepository.save(aluno);
        // Nenhuma necessidade além do setup()
        AlunoNaoExisteException thrown = assertThrows(
                AlunoNaoExisteException.class,
                () -> driver.alterar(2L, alunoPostPutRequestDTO)
        );

        // Assert
        assertAll(
                () -> assertEquals("O aluno consultado nao existe!", thrown.getMessage()),
                () -> assertEquals(1, alunoRepository.count())
        );
    }
}

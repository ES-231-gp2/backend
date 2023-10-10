package com.ufcg.es.biblioconex.service.aluno;

import com.ufcg.es.biblioconex.dto.AlunoPostPutRequestDTO;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.repository.AlunoRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Testes do Serviço de criação de aluno")
public class AlunoBaseTests {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    Aluno aluno;

    AlunoPostPutRequestDTO alunoPostPutRequestDTO;

    Turma turma;

    @BeforeEach
    void setup() {
        turma = turmaRepository.save(Turma.builder().build());
        aluno = Aluno.builder()
                .nome("Aluno Ponto da Silva")
                .turma(turma)
                .email("alunoponto@gmail.com")
                .build();
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
}

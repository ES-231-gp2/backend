package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.TurmaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno, Long> {

    List<TurmaAluno> findAllByTurmaIdOrderByAlunoNome(Long turmaId);

    TurmaAluno findTurmaByAlunoId(Long alunoId);
}

package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

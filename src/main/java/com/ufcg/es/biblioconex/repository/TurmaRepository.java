package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}

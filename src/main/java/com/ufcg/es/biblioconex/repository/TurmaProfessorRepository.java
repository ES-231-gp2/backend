package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.TurmaProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaProfessorRepository extends JpaRepository<TurmaProfessor, Long> {
}

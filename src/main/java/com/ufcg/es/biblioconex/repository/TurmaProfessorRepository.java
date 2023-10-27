package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaProfessorRepository extends JpaRepository<TurmaProfessor, Long> {

    @Query("SELECT tp.turma FROM TurmaProfessor tp WHERE tp.professor.id = :professorId")
    List<Turma> findTurmasByProfessorId(@Param("professorId") Long professorId);
}


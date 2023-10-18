package com.ufcg.es.biblioconex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.es.biblioconex.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}

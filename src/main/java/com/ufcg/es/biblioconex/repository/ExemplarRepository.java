package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
}

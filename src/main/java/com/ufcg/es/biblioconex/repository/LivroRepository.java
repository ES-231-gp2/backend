package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}

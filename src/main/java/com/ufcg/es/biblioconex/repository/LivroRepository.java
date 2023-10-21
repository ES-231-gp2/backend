package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByOrderByTituloAsc();

    List<Livro> findByGenerosInOrderByTituloAsc(Set<String> generos);

    Livro findFirstByLivroDoMesTrue();

    List<Livro> findTop10ByOrderByLeiturasDesc();

}

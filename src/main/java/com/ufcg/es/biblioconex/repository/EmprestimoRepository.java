package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Emprestimo;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    @Query("SELECT e.exemplar.livro FROM Emprestimo e WHERE e.usuario = :usuario")
    List<Livro> consultarHistorico(@Param("usuario") Usuario usuario);
}

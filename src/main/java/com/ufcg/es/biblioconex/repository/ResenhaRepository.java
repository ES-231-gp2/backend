package com.ufcg.es.biblioconex.repository;

import com.ufcg.es.biblioconex.model.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenhaRepository extends JpaRepository<Resenha, Long> {

    List<Resenha> findByLivroId(Long livroId);

    List<Resenha> findByAlunoId(Long alunoId);

    @Query("SELECT r.aluno as aluno, COUNT(r) as totalResenhas " +
            "FROM Resenha r " +
            "GROUP BY r.aluno " +
            "ORDER BY totalResenhas DESC")
    List<Object[]> findTop10AlunosMaisResenhas();
}

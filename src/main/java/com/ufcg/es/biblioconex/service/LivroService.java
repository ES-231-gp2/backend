package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;

import java.util.List;
import java.util.Set;

public interface LivroService {

    Livro cadastrarLivro(LivroDTO livroDTO, Integer numeroExemplares);

    List<Livro> buscarLivros(Long id);

    List<Livro> buscarLivrosPorGenero(Set<String> generos);

    LivroDTO buscarLivroPorIsbn(String isbn);

    Livro atualizarLivro(Long id, LivroDTO DTO);

    void removerLivro(Long id);

    Livro adicionarExemplares(Long id, Integer numeroExemplares);

    Livro[] atualizarLivroDoMes(Long id);

    Livro verLivroDoMes();

    List<Livro> buscarMaisLidos();
}

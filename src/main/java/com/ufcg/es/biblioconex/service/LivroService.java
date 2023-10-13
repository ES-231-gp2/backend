package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;

import java.util.List;

public interface LivroService {

    Livro cadastrarLivro(LivroDTO livroDTO, Integer numeroExemplares);

    LivroDTO buscarLivroPorIsbn(String isbn);

    List<Livro> buscarLivros(Long id);

    Livro atualizarLivro(Long id, LivroDTO DTO);

    void removerLivro(Long id);

    Livro adicionarExemplares(Long id, Integer numeroExemplares);
}

package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;

import java.util.List;
import java.util.Set;

public interface LivroService {

    /**
     * Cadastra um livro no sistema.
     *
     * @param livroDTO         DTO com os dados do livro
     * @param numeroExemplares número de exemplares do livro
     * @return o livro cadastrado
     */
    Livro cadastrarLivro(LivroDTO livroDTO, Integer numeroExemplares);

    /**
     * Busca 1 livro específico no sistema pelo id, ou buscar todos livros no sistema quando id é null.
     *
     * @param id id do livro
     * @return lista de livros encontrados
     */
    List<Livro> buscarLivros(Long id);

    /**
     * Busca livros por gênero.
     *
     * @param generos listas de gêneros buscados
     * @return lista de livros encontrados
     */
    List<Livro> buscarLivrosPorGenero(Set<String> generos);

    /**
     * Busca um livro por isbn na API go Google Books.
     *
     * @param isbn isbn do livro
     * @return o livro encontrado
     */
    LivroDTO buscarLivroPorIsbn(String isbn);

    /**
     * Atualiza um livro no sistema.
     *
     * @param id  id do livro
     * @param DTO DTO com os dados do livro
     * @return o livro atualizado
     */
    Livro atualizarLivro(Long id, LivroDTO DTO);

    /**
     * Remove um livro do sistema.
     *
     * @param id id do livro
     */
    void removerLivro(Long id);

    /**
     * Adiciona exemplares a um livro.
     *
     * @param id               id do livro
     * @param numeroExemplares número de exemplares a serem adicionados
     * @return o livro com os exemplares adicionados
     */
    Livro adicionarExemplares(Long id, Integer numeroExemplares);

    /**
     * Atualiza o livro do mês.
     *
     * @param id id do livro
     * @return o livro do mês atualizado
     */
    Livro[] atualizarLivroDoMes(Long id);

    /**
     * Retorna o livro do mês.
     *
     * @return o livro do mês
     */
    Livro verLivroDoMes();
}

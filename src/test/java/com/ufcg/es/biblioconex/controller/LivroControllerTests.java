package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.service.LivroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LivroController.class})
@ExtendWith(SpringExtension.class)
class LivroControllerTests {
    @Autowired
    private LivroController livroController;

    @MockBean
    private LivroService livroService;

    /**
     * Method under test: {@link LivroController#buscarLivros()}
     */
    @Test
    void testBuscarLivros() throws Exception {
        when(livroService.buscarLivros(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/livros");
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LivroController#buscarLivrosPorGenero(Set)}
     */
    @Test
    void testBuscarLivrosPorGenero() throws Exception {
        when(livroService.buscarLivrosPorGenero(Mockito.any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get(
                "/api/livros/generos");
        MockHttpServletRequestBuilder requestBuilder = getResult.param(
                "generos", String.valueOf(new HashSet<>()));
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LivroController#buscarLivroPorIsbn(String)}
     */
    @Test
    void testBuscarLivroPorIsbn() throws Exception {
        when(livroService.buscarLivroPorIsbn(Mockito.any())).thenReturn(LivroDTO.builder()
                .ano("Ano")
                .capa("Capa")
                .descricao("Descricao")
                .edicao(1)
                .editora("Editora")
                .paginas("Paginas")
                .titulo("Titulo")
                .build());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/livros/isbn/{isbn}", "Isbn");
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"isbn\":null,\"titulo\":\"Titulo\"," +
                                        "\"autores\":[]," +
                                        "\"editora\":\"Editora\"," +
                                        "\"ano\":\"Ano\"," +
                                        "\"paginas\":\"Paginas\",\"edicao"
                                        + "\":1,\"descricao\":\"Descricao\"," +
                                        "\"generos\":[],\"capa\":\"Capa\"}"));
    }

    /**
     * Method under test: {@link LivroController#removerLivro(Long)}
     */
    @Test
    void testRemoverLivro() throws Exception {
        doNothing().when(livroService).removerLivro(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.delete("/api/livros/{id}", 1L);
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(livroController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link LivroController#removerLivro(Long)}
     */
    @Test
    void testRemoverLivro2() throws Exception {
        doNothing().when(livroService).removerLivro(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.delete("/api/livros/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(livroController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test:
     * {@link LivroController#adicionarExemplares(Long, Integer)}
     */
    @Test
    void testAdicionarExemplares() throws Exception {
        Livro livro = new Livro();
        livro.setAno("Ano");
        livro.setAutores(new HashSet<>());
        livro.setCapa("Capa");
        livro.setDescricao("Descricao");
        livro.setEdicao(1);
        livro.setEditora("Editora");
        livro.setExemplares(new HashSet<>());
        livro.setGeneros(new HashSet<>());
        livro.setId(1L);
        livro.setIsbn("Isbn");
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        when(livroService.adicionarExemplares(Mockito.<Long>any(),
                Mockito.<Integer>any())).thenReturn(livro);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put(
                "/api/livros/exemplares/{id}", 1L);
        MockHttpServletRequestBuilder requestBuilder = putResult.param(
                "numeroExemplares", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"isbn\":\"Isbn\"," +
                                        "\"titulo\":\"Titulo\"," +
                                        "\"autores\":[]," +
                                        "\"editora\":\"Editora\"," +
                                        "\"ano\":\"Ano\",\"paginas\":1,"
                                        + "\"edicao\":1," +
                                        "\"descricao\":\"Descricao\"," +
                                        "\"generos\":[],\"capa\":\"Capa\"," +
                                        "\"exemplares\":[]," +
                                        "\"livroDoMes\":true}"));
    }

    /**
     * Method under test: {@link LivroController#verLivroDoMes()}
     */
    @Test
    void testVerLivroDoMes() throws Exception {
        Livro livro = new Livro();
        livro.setAno("Ano");
        livro.setAutores(new HashSet<>());
        livro.setCapa("Capa");
        livro.setDescricao("Descricao");
        livro.setEdicao(1);
        livro.setEditora("Editora");
        livro.setExemplares(new HashSet<>());
        livro.setGeneros(new HashSet<>());
        livro.setId(1L);
        livro.setIsbn("Isbn");
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        when(livroService.verLivroDoMes()).thenReturn(livro);
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/livros/livro-do-mes");
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"isbn\":\"Isbn\"," +
                                        "\"titulo\":\"Titulo\"," +
                                        "\"autores\":[]," +
                                        "\"editora\":\"Editora\"," +
                                        "\"ano\":\"Ano\",\"paginas\":1,"
                                        + "\"edicao\":1," +
                                        "\"descricao\":\"Descricao\"," +
                                        "\"generos\":[],\"capa\":\"Capa\"," +
                                        "\"exemplares\":[]," +
                                        "\"livroDoMes\":true}"));
    }

    /**
     * Method under test: {@link LivroController#atualizarLivro(Long, LivroDTO)}
     */
    @Test
    void testAtualizarLivro() throws Exception {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setAno("Ano");
        livroDTO.setAutores(new HashSet<>());
        livroDTO.setCapa("Capa");
        livroDTO.setDescricao("Descricao");
        livroDTO.setEdicao(1);
        livroDTO.setEditora("Editora");
        livroDTO.setGeneros(new HashSet<>());
        livroDTO.setIsbn("Isbn");
        livroDTO.setPaginas("Paginas");
        livroDTO.setTitulo("Titulo");
        String content = (new ObjectMapper()).writeValueAsString(livroDTO);
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.put("/api/livros/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content);
        ResultActions actualPerformResult =
                MockMvcBuilders.standaloneSetup(livroController)
                        .build()
                        .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link LivroController#buscarLivro(Long)}
     */
    @Test
    void testBuscarLivro() throws Exception {
        when(livroService.buscarLivros(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/livros/{id}", 1L);
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LivroController#buscarLivro(Long)}
     */
    @Test
    void testBuscarLivro2() throws Exception {
        when(livroService.buscarLivros(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/livros/{id}", "",
                        "Uri Variables");
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link LivroController#cadastrarLivro(LivroDTO, Integer)}
     */
    @Test
    void testCadastrarLivro() throws Exception {
        when(livroService.buscarLivros(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setAno("Ano");
        livroDTO.setAutores(new HashSet<>());
        livroDTO.setCapa("Capa");
        livroDTO.setDescricao("Descricao");
        livroDTO.setEdicao(1);
        livroDTO.setEditora("Editora");
        livroDTO.setGeneros(new HashSet<>());
        livroDTO.setIsbn("Isbn");
        livroDTO.setPaginas("Paginas");
        livroDTO.setTitulo("Titulo");
        String content = (new ObjectMapper()).writeValueAsString(livroDTO);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get(
                "/api/livros");
        MockHttpServletRequestBuilder requestBuilder = getResult.param(
                        "numeroExemplares", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(livroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        "application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


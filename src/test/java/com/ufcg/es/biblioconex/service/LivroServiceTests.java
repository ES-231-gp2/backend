package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.enums.StatusExemplarEnum;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Exemplar;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {LivroServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LivroServiceTests {
    @MockBean
    private LivroRepository livroRepository;

    @Autowired
    private LivroServiceImpl livroServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link LivroServiceImpl#cadastrarLivro(LivroDTO, Integer)}
     */
    @Test
    void testCadastrarLivro() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        when(livroRepository.save(Mockito.any())).thenReturn(livro);

        Livro livro2 = new Livro();
        livro2.setAno("Ano");
        livro2.setAutores(new HashSet<>());
        livro2.setCapa("Capa");
        livro2.setDescricao("Descricao");
        livro2.setEdicao(1);
        livro2.setEditora("Editora");
        livro2.setExemplares(new HashSet<>());
        livro2.setGeneros(new HashSet<>());
        livro2.setId(1L);
        livro2.setIsbn("Isbn");
        livro2.setLeituras(1);
        livro2.setLivroDoMes(true);
        livro2.setPaginas(1);
        livro2.setTitulo("Titulo");
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Livro>>any())).thenReturn(livro2);
        LivroDTO livroDTO = new LivroDTO();
        Livro actualCadastrarLivroResult = livroServiceImpl.cadastrarLivro(livroDTO, 10);
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Livro>>any());
        verify(livroRepository, atLeast(1)).save(Mockito.any());
        assertEquals("", livroDTO.getIsbn());
        assertSame(livro, actualCadastrarLivroResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#cadastrarLivro(LivroDTO, Integer)}
     */
    @Test
    void testCadastrarLivro2() {
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Livro>>any()))
                .thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.cadastrarLivro(new LivroDTO(), 10));
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Livro>>any());
    }

    /**
     * Method under test: {@link LivroServiceImpl#cadastrarLivro(LivroDTO, Integer)}
     */
    @Test
    void testCadastrarLivro3() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        when(livroRepository.save(Mockito.any())).thenReturn(livro);

        Livro livro2 = new Livro();
        livro2.setAno("Ano");
        livro2.setAutores(new HashSet<>());
        livro2.setCapa("Capa");
        livro2.setDescricao("Descricao");
        livro2.setEdicao(1);
        livro2.setEditora("Editora");
        livro2.setExemplares(new HashSet<>());
        livro2.setGeneros(new HashSet<>());
        livro2.setId(1L);
        livro2.setIsbn("Isbn");
        livro2.setLeituras(1);
        livro2.setLivroDoMes(true);
        livro2.setPaginas(1);
        livro2.setTitulo("Titulo");

        Exemplar exemplar = new Exemplar();
        exemplar.setEmprestimos(new HashSet<>());
        exemplar.setId(1L);
        exemplar.setLivro(livro2);
        exemplar.setNumero(10);
        exemplar.setStatus(StatusExemplarEnum.DISPONIVEL);

        HashSet<Exemplar> exemplares = new HashSet<>();
        exemplares.add(exemplar);

        Livro livro3 = new Livro();
        livro3.setAno("Ano");
        livro3.setAutores(new HashSet<>());
        livro3.setCapa("Capa");
        livro3.setDescricao("Descricao");
        livro3.setEdicao(1);
        livro3.setEditora("Editora");
        livro3.setExemplares(exemplares);
        livro3.setGeneros(new HashSet<>());
        livro3.setId(1L);
        livro3.setIsbn("Isbn");
        livro3.setLeituras(1);
        livro3.setLivroDoMes(true);
        livro3.setPaginas(1);
        livro3.setTitulo("Titulo");
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Livro>>any())).thenReturn(livro3);
        LivroDTO livroDTO = new LivroDTO();
        Livro actualCadastrarLivroResult = livroServiceImpl.cadastrarLivro(livroDTO, 10);
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Livro>>any());
        verify(livroRepository, atLeast(1)).save(Mockito.any());
        assertEquals("", livroDTO.getIsbn());
        assertSame(livro, actualCadastrarLivroResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#cadastrarLivro(LivroDTO, Integer)}
     */
    @Test
    void testCadastrarLivro4() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        when(livroRepository.save(Mockito.any())).thenReturn(livro);

        Livro livro2 = new Livro();
        livro2.setAno("Ano");
        livro2.setAutores(new HashSet<>());
        livro2.setCapa("Capa");
        livro2.setDescricao("Descricao");
        livro2.setEdicao(1);
        livro2.setEditora("Editora");
        livro2.setExemplares(new HashSet<>());
        livro2.setGeneros(new HashSet<>());
        livro2.setId(1L);
        livro2.setIsbn("Isbn");
        livro2.setLeituras(1);
        livro2.setLivroDoMes(true);
        livro2.setPaginas(1);
        livro2.setTitulo("Titulo");
        when(modelMapper.map(Mockito.any(), Mockito.<Class<Livro>>any())).thenReturn(livro2);
        HashSet<String> autores = new HashSet<>();
        LivroDTO livroDTO = new LivroDTO("Isbn", "Titulo", autores, "Editora", "Ano", "Paginas", 1, "Descricao",
                new HashSet<>(), "Capa");

        Livro actualCadastrarLivroResult = livroServiceImpl.cadastrarLivro(livroDTO, 10);
        verify(modelMapper).map(Mockito.any(), Mockito.<Class<Livro>>any());
        verify(livroRepository, atLeast(1)).save(Mockito.any());
        assertEquals("Isbn", livroDTO.getIsbn());
        assertSame(livro, actualCadastrarLivroResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivros(Long)}
     */
    @Test
    void testBuscarLivros() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        Optional<Livro> ofResult = Optional.of(livro);
        when(livroRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Livro> actualBuscarLivrosResult = livroServiceImpl.buscarLivros(1L);
        verify(livroRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualBuscarLivrosResult.size());
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivros(Long)}
     */
    @Test
    void testBuscarLivros2() {
        ArrayList<Livro> livroList = new ArrayList<>();
        when(livroRepository.findByOrderByTituloAsc()).thenReturn(livroList);
        List<Livro> actualBuscarLivrosResult = livroServiceImpl.buscarLivros(0L);
        verify(livroRepository).findByOrderByTituloAsc();
        assertTrue(actualBuscarLivrosResult.isEmpty());
        assertSame(livroList, actualBuscarLivrosResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivros(Long)}
     */
    @Test
    void testBuscarLivros3() {
        ArrayList<Livro> livroList = new ArrayList<>();
        when(livroRepository.findByOrderByTituloAsc()).thenReturn(livroList);
        List<Livro> actualBuscarLivrosResult = livroServiceImpl.buscarLivros(null);
        verify(livroRepository).findByOrderByTituloAsc();
        assertTrue(actualBuscarLivrosResult.isEmpty());
        assertSame(livroList, actualBuscarLivrosResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivros(Long)}
     */
    @Test
    void testBuscarLivros4() {
        when(livroRepository.findByOrderByTituloAsc()).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.buscarLivros(0L));
        verify(livroRepository).findByOrderByTituloAsc();
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivrosPorGenero(Set)}
     */
    @Test
    void testBuscarLivrosPorGenero() {
        ArrayList<Livro> livroList = new ArrayList<>();
        when(livroRepository.findByGenerosInOrderByTituloAsc(Mockito.any())).thenReturn(livroList);
        List<Livro> actualBuscarLivrosPorGeneroResult = livroServiceImpl.buscarLivrosPorGenero(new HashSet<>());
        verify(livroRepository).findByGenerosInOrderByTituloAsc(Mockito.any());
        assertTrue(actualBuscarLivrosPorGeneroResult.isEmpty());
        assertSame(livroList, actualBuscarLivrosPorGeneroResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivrosPorGenero(Set)}
     */
    @Test
    void testBuscarLivrosPorGenero2() {
        ArrayList<Livro> livroList = new ArrayList<>();
        when(livroRepository.findByGenerosInOrderByTituloAsc(Mockito.any())).thenReturn(livroList);

        HashSet<String> generos = new HashSet<>();
        generos.add("foo");
        List<Livro> actualBuscarLivrosPorGeneroResult = livroServiceImpl.buscarLivrosPorGenero(generos);
        verify(livroRepository).findByGenerosInOrderByTituloAsc(Mockito.any());
        assertTrue(actualBuscarLivrosPorGeneroResult.isEmpty());
        assertSame(livroList, actualBuscarLivrosPorGeneroResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivrosPorGenero(Set)}
     */
    @Test
    void testBuscarLivrosPorGenero3() {
        ArrayList<Livro> livroList = new ArrayList<>();
        when(livroRepository.findByGenerosInOrderByTituloAsc(Mockito.any())).thenReturn(livroList);

        HashSet<String> generos = new HashSet<>();
        generos.add("42");
        generos.add("foo");
        List<Livro> actualBuscarLivrosPorGeneroResult = livroServiceImpl.buscarLivrosPorGenero(generos);
        verify(livroRepository).findByGenerosInOrderByTituloAsc(Mockito.any());
        assertTrue(actualBuscarLivrosPorGeneroResult.isEmpty());
        assertSame(livroList, actualBuscarLivrosPorGeneroResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarLivrosPorGenero(Set)}
     */
    @Test
    void testBuscarLivrosPorGenero4() {
        when(livroRepository.findByGenerosInOrderByTituloAsc(Mockito.any()))
                .thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.buscarLivrosPorGenero(new HashSet<>()));
        verify(livroRepository).findByGenerosInOrderByTituloAsc(Mockito.any());
    }

    /**
     * Method under test: {@link LivroServiceImpl#atualizarLivro(Long, LivroDTO)}
     */
    @Test
    void testAtualizarLivro() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        Optional<Livro> ofResult = Optional.of(livro);

        Livro livro2 = new Livro();
        livro2.setAno("Ano");
        livro2.setAutores(new HashSet<>());
        livro2.setCapa("Capa");
        livro2.setDescricao("Descricao");
        livro2.setEdicao(1);
        livro2.setEditora("Editora");
        livro2.setExemplares(new HashSet<>());
        livro2.setGeneros(new HashSet<>());
        livro2.setId(1L);
        livro2.setIsbn("Isbn");
        livro2.setLeituras(1);
        livro2.setLivroDoMes(true);
        livro2.setPaginas(1);
        livro2.setTitulo("Titulo");
        when(livroRepository.save(Mockito.any())).thenReturn(livro2);
        when(livroRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doNothing().when(modelMapper).map(Mockito.any(), Mockito.<Object>any());
        Livro actualAtualizarLivroResult = livroServiceImpl.atualizarLivro(1L, new LivroDTO());
        verify(modelMapper).map(Mockito.any(), Mockito.<Object>any());
        verify(livroRepository).findById(Mockito.<Long>any());
        verify(livroRepository).save(Mockito.any());
        assertSame(livro2, actualAtualizarLivroResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#atualizarLivro(Long, LivroDTO)}
     */
    @Test
    void testAtualizarLivro2() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        Optional<Livro> ofResult = Optional.of(livro);
        when(livroRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doThrow(new ObjetoNaoExisteException()).when(modelMapper).map(Mockito.any(), Mockito.<Object>any());
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.atualizarLivro(1L, new LivroDTO()));
        verify(modelMapper).map(Mockito.any(), Mockito.<Object>any());
        verify(livroRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link LivroServiceImpl#atualizarLivro(Long, LivroDTO)}
     */
    @Test
    void testAtualizarLivro3() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        Optional<Livro> ofResult = Optional.of(livro);
        when(livroRepository.save(Mockito.any())).thenThrow(new ObjetoNaoExisteException());
        when(livroRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doNothing().when(modelMapper).map(Mockito.any(), Mockito.<Object>any());
        assertThrows(ObjetoNaoExisteException.class,
                () -> livroServiceImpl.atualizarLivro(1L,
                        LivroDTO.builder()
                                .ano("Ano")
                                .capa("Capa")
                                .descricao("Descricao")
                                .edicao(1)
                                .editora("Editora")
                                .paginas("Paginas")
                                .titulo("Titulo")
                                .build()));
        verify(modelMapper).map(Mockito.any(), Mockito.<Object>any());
        verify(livroRepository).findById(Mockito.<Long>any());
        verify(livroRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link LivroServiceImpl#removerLivro(Long)}
     */
    @Test
    void testRemoverLivro() {
        doNothing().when(livroRepository).deleteById(Mockito.<Long>any());
        livroServiceImpl.removerLivro(1L);
        verify(livroRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link LivroServiceImpl#removerLivro(Long)}
     */
    @Test
    void testRemoverLivro2() {
        doThrow(new ObjetoNaoExisteException()).when(livroRepository).deleteById(Mockito.<Long>any());
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.removerLivro(1L));
        verify(livroRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link LivroServiceImpl#adicionarExemplares(Long, Integer)}
     */
    @Test
    void testAdicionarExemplares() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        Optional<Livro> ofResult = Optional.of(livro);

        Livro livro2 = new Livro();
        livro2.setAno("Ano");
        livro2.setAutores(new HashSet<>());
        livro2.setCapa("Capa");
        livro2.setDescricao("Descricao");
        livro2.setEdicao(1);
        livro2.setEditora("Editora");
        livro2.setExemplares(new HashSet<>());
        livro2.setGeneros(new HashSet<>());
        livro2.setId(1L);
        livro2.setIsbn("Isbn");
        livro2.setLeituras(1);
        livro2.setLivroDoMes(true);
        livro2.setPaginas(1);
        livro2.setTitulo("Titulo");
        when(livroRepository.save(Mockito.any())).thenReturn(livro2);
        when(livroRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Livro actualAdicionarExemplaresResult = livroServiceImpl.adicionarExemplares(1L, 10);
        verify(livroRepository).findById(Mockito.<Long>any());
        verify(livroRepository).save(Mockito.any());
        assertSame(livro2, actualAdicionarExemplaresResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#adicionarExemplares(Long, Integer)}
     */
    @Test
    void testAdicionarExemplares2() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        Optional<Livro> ofResult = Optional.of(livro);
        when(livroRepository.save(Mockito.any())).thenThrow(new ObjetoNaoExisteException());
        when(livroRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.adicionarExemplares(1L, 10));
        verify(livroRepository).findById(Mockito.<Long>any());
        verify(livroRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link LivroServiceImpl#adicionarExemplares(Long, Integer)}
     */
    @Test
    void testAdicionarExemplares3() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");

        Exemplar exemplar = new Exemplar();
        exemplar.setEmprestimos(new HashSet<>());
        exemplar.setId(1L);
        exemplar.setLivro(livro);
        exemplar.setNumero(10);
        exemplar.setStatus(StatusExemplarEnum.DISPONIVEL);

        HashSet<Exemplar> exemplares = new HashSet<>();
        exemplares.add(exemplar);

        Livro livro2 = new Livro();
        livro2.setAno("Ano");
        livro2.setAutores(new HashSet<>());
        livro2.setCapa("Capa");
        livro2.setDescricao("Descricao");
        livro2.setEdicao(1);
        livro2.setEditora("Editora");
        livro2.setExemplares(exemplares);
        livro2.setGeneros(new HashSet<>());
        livro2.setId(1L);
        livro2.setIsbn("Isbn");
        livro2.setLeituras(1);
        livro2.setLivroDoMes(true);
        livro2.setPaginas(1);
        livro2.setTitulo("Titulo");
        Optional<Livro> ofResult = Optional.of(livro2);

        Livro livro3 = new Livro();
        livro3.setAno("Ano");
        livro3.setAutores(new HashSet<>());
        livro3.setCapa("Capa");
        livro3.setDescricao("Descricao");
        livro3.setEdicao(1);
        livro3.setEditora("Editora");
        livro3.setExemplares(new HashSet<>());
        livro3.setGeneros(new HashSet<>());
        livro3.setId(1L);
        livro3.setIsbn("Isbn");
        livro3.setLeituras(1);
        livro3.setLivroDoMes(true);
        livro3.setPaginas(1);
        livro3.setTitulo("Titulo");
        when(livroRepository.save(Mockito.any())).thenReturn(livro3);
        when(livroRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Livro actualAdicionarExemplaresResult = livroServiceImpl.adicionarExemplares(1L, 10);
        verify(livroRepository).findById(Mockito.<Long>any());
        verify(livroRepository).save(Mockito.any());
        assertSame(livro3, actualAdicionarExemplaresResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#atualizarLivroDoMes(String)}
     */
    @Test
    void testAtualizarLivroDoMes() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");

        Livro livro2 = new Livro();
        livro2.setAno("Ano");
        livro2.setAutores(new HashSet<>());
        livro2.setCapa("Capa");
        livro2.setDescricao("Descricao");
        livro2.setEdicao(1);
        livro2.setEditora("Editora");
        livro2.setExemplares(new HashSet<>());
        livro2.setGeneros(new HashSet<>());
        livro2.setId(1L);
        livro2.setIsbn("Isbn");
        livro2.setLeituras(1);
        livro2.setLivroDoMes(true);
        livro2.setPaginas(1);
        livro2.setTitulo("Titulo");

        Livro livro3 = new Livro();
        livro3.setAno("Ano");
        livro3.setAutores(new HashSet<>());
        livro3.setCapa("Capa");
        livro3.setDescricao("Descricao");
        livro3.setEdicao(1);
        livro3.setEditora("Editora");
        livro3.setExemplares(new HashSet<>());
        livro3.setGeneros(new HashSet<>());
        livro3.setId(1L);
        livro3.setIsbn("Isbn");
        livro3.setLeituras(1);
        livro3.setLivroDoMes(true);
        livro3.setPaginas(1);
        livro3.setTitulo("Titulo");
        when(livroRepository.findFirstByLivroDoMesTrue()).thenReturn(livro2);
        when(livroRepository.save(Mockito.any())).thenReturn(livro3);
        when(livroRepository.findByIsbn(Mockito.any())).thenReturn(livro);
        Livro[] actualAtualizarLivroDoMesResult = livroServiceImpl.atualizarLivroDoMes("Isbn");
        verify(livroRepository).findByIsbn(Mockito.any());
        verify(livroRepository).findFirstByLivroDoMesTrue();
        verify(livroRepository).save(Mockito.any());
        assertEquals(2, actualAtualizarLivroDoMesResult.length);
        assertSame(livro3, actualAtualizarLivroDoMesResult[1]);
    }

    /**
     * Method under test: {@link LivroServiceImpl#atualizarLivroDoMes(String)}
     */
    @Test
    void testAtualizarLivroDoMes2() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        when(livroRepository.findFirstByLivroDoMesTrue()).thenThrow(new NoSuchElementException("foo"));
        when(livroRepository.findByIsbn(Mockito.any())).thenReturn(livro);
        assertThrows(NoSuchElementException.class, () -> livroServiceImpl.atualizarLivroDoMes("Isbn"));
        verify(livroRepository).findByIsbn(Mockito.any());
        verify(livroRepository).findFirstByLivroDoMesTrue();
    }

    /**
     * Method under test: {@link LivroServiceImpl#verLivroDoMes()}
     */
    @Test
    void testVerLivroDoMes() {
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
        livro.setLeituras(1);
        livro.setLivroDoMes(true);
        livro.setPaginas(1);
        livro.setTitulo("Titulo");
        when(livroRepository.findFirstByLivroDoMesTrue()).thenReturn(livro);
        Livro actualVerLivroDoMesResult = livroServiceImpl.verLivroDoMes();
        verify(livroRepository).findFirstByLivroDoMesTrue();
        assertSame(livro, actualVerLivroDoMesResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#verLivroDoMes()}
     */
    @Test
    void testVerLivroDoMes2() {
        when(livroRepository.findFirstByLivroDoMesTrue()).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.verLivroDoMes());
        verify(livroRepository).findFirstByLivroDoMesTrue();
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarMaisLidos()}
     */
    @Test
    void testBuscarMaisLidos() {
        ArrayList<Livro> livroList = new ArrayList<>();
        when(livroRepository.findTop10ByOrderByLeiturasDesc()).thenReturn(livroList);
        List<Livro> actualBuscarMaisLidosResult = livroServiceImpl.buscarMaisLidos();
        verify(livroRepository).findTop10ByOrderByLeiturasDesc();
        assertTrue(actualBuscarMaisLidosResult.isEmpty());
        assertSame(livroList, actualBuscarMaisLidosResult);
    }

    /**
     * Method under test: {@link LivroServiceImpl#buscarMaisLidos()}
     */
    @Test
    void testBuscarMaisLidos2() {
        when(livroRepository.findTop10ByOrderByLeiturasDesc()).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> livroServiceImpl.buscarMaisLidos());
        verify(livroRepository).findTop10ByOrderByLeiturasDesc();
    }
}


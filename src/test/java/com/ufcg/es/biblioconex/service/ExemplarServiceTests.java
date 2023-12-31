package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.enums.StatusExemplarEnum;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.BiblioConexException;
import com.ufcg.es.biblioconex.model.Emprestimo;
import com.ufcg.es.biblioconex.model.Exemplar;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.EmprestimoRepository;
import com.ufcg.es.biblioconex.repository.ExemplarRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ExemplarServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ExemplarServiceTests {
    @MockBean
    private EmprestimoRepository emprestimoRepository;

    @MockBean
    private ExemplarRepository exemplarRepository;

    @Autowired
    private ExemplarServiceImpl exemplarServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UsuarioRepository usuarioRepository;

    /**
     * Method under test: {@link ExemplarServiceImpl#realizarEmprestimo(EmprestimoDTO)}
     */
    @Test
    void testRealizarEmprestimo() {
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
        Optional<Exemplar> ofResult = Optional.of(exemplar);
        when(exemplarRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenThrow(new BiblioConexException("An error occurred"));
        assertThrows(BiblioConexException.class, () -> exemplarServiceImpl.realizarEmprestimo(new EmprestimoDTO()));
        verify(exemplarRepository).findById(Mockito.<Long>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ExemplarServiceImpl#realizarDevolucao(Long)}
     */
    @Test
    void testRealizarDevolucao() {
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

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataDevolucao(LocalDate.of(1970, 1, 1));
        emprestimo.setDataDevolucaoPrevista(LocalDate.of(1970, 1, 1));
        emprestimo.setDataEmprestimo(LocalDate.of(1970, 1, 1));
        emprestimo.setExemplar(exemplar);
        emprestimo.setId(1L);
        emprestimo.setUsuario(usuario);
        Optional<Emprestimo> ofResult = Optional.of(emprestimo);

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

        Exemplar exemplar2 = new Exemplar();
        exemplar2.setEmprestimos(new HashSet<>());
        exemplar2.setId(1L);
        exemplar2.setLivro(livro2);
        exemplar2.setNumero(10);
        exemplar2.setStatus(StatusExemplarEnum.DISPONIVEL);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(1L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Emprestimo emprestimo2 = new Emprestimo();
        emprestimo2.setDataDevolucao(LocalDate.of(1970, 1, 1));
        emprestimo2.setDataDevolucaoPrevista(LocalDate.of(1970, 1, 1));
        emprestimo2.setDataEmprestimo(LocalDate.of(1970, 1, 1));
        emprestimo2.setExemplar(exemplar2);
        emprestimo2.setId(1L);
        emprestimo2.setUsuario(usuario2);
        when(emprestimoRepository.save(Mockito.any())).thenReturn(emprestimo2);
        when(emprestimoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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

        Exemplar exemplar3 = new Exemplar();
        exemplar3.setEmprestimos(new HashSet<>());
        exemplar3.setId(1L);
        exemplar3.setLivro(livro3);
        exemplar3.setNumero(10);
        exemplar3.setStatus(StatusExemplarEnum.DISPONIVEL);
        when(exemplarRepository.save(Mockito.any())).thenReturn(exemplar3);
        Emprestimo actualRealizarDevolucaoResult = exemplarServiceImpl.realizarDevolucao(1L);
        verify(emprestimoRepository).findById(Mockito.<Long>any());
        verify(emprestimoRepository).save(Mockito.any());
        verify(exemplarRepository).save(Mockito.any());
        Exemplar exemplar4 = actualRealizarDevolucaoResult.getExemplar();
        assertEquals(2, exemplar4.getLivro().getLeituras().intValue());
        assertEquals(StatusExemplarEnum.DISPONIVEL, exemplar4.getStatus());
        assertSame(emprestimo, actualRealizarDevolucaoResult);
    }

    /**
     * Method under test: {@link ExemplarServiceImpl#realizarDevolucao(Long)}
     */
    @Test
    void testRealizarDevolucao2() {
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

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataDevolucao(LocalDate.of(1970, 1, 1));
        emprestimo.setDataDevolucaoPrevista(LocalDate.of(1970, 1, 1));
        emprestimo.setDataEmprestimo(LocalDate.of(1970, 1, 1));
        emprestimo.setExemplar(exemplar);
        emprestimo.setId(1L);
        emprestimo.setUsuario(usuario);
        Optional<Emprestimo> ofResult = Optional.of(emprestimo);

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

        Exemplar exemplar2 = new Exemplar();
        exemplar2.setEmprestimos(new HashSet<>());
        exemplar2.setId(1L);
        exemplar2.setLivro(livro2);
        exemplar2.setNumero(10);
        exemplar2.setStatus(StatusExemplarEnum.DISPONIVEL);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(1L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Emprestimo emprestimo2 = new Emprestimo();
        emprestimo2.setDataDevolucao(LocalDate.of(1970, 1, 1));
        emprestimo2.setDataDevolucaoPrevista(LocalDate.of(1970, 1, 1));
        emprestimo2.setDataEmprestimo(LocalDate.of(1970, 1, 1));
        emprestimo2.setExemplar(exemplar2);
        emprestimo2.setId(1L);
        emprestimo2.setUsuario(usuario2);
        when(emprestimoRepository.save(Mockito.any())).thenReturn(emprestimo2);
        when(emprestimoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(exemplarRepository.save(Mockito.any())).thenThrow(new BiblioConexException("An error occurred"));
        assertThrows(BiblioConexException.class, () -> exemplarServiceImpl.realizarDevolucao(1L));
        verify(emprestimoRepository).findById(Mockito.<Long>any());
        verify(emprestimoRepository).save(Mockito.any());
        verify(exemplarRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link ExemplarServiceImpl#realizarDevolucao(Long)}
     */
    @Test
    void testRealizarDevolucao3() {
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

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);

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

        Exemplar exemplar2 = new Exemplar();
        exemplar2.setEmprestimos(new HashSet<>());
        exemplar2.setId(1L);
        exemplar2.setLivro(livro2);
        exemplar2.setNumero(10);
        exemplar2.setStatus(StatusExemplarEnum.DISPONIVEL);
        Emprestimo emprestimo = mock(Emprestimo.class);
        when(emprestimo.getExemplar()).thenReturn(exemplar2);
        doNothing().when(emprestimo).setDataDevolucao(Mockito.any());
        doNothing().when(emprestimo).setDataDevolucaoPrevista(Mockito.any());
        doNothing().when(emprestimo).setDataEmprestimo(Mockito.any());
        doNothing().when(emprestimo).setExemplar(Mockito.any());
        doNothing().when(emprestimo).setId(Mockito.<Long>any());
        doNothing().when(emprestimo).setUsuario(Mockito.any());
        emprestimo.setDataDevolucao(LocalDate.of(1970, 1, 1));
        emprestimo.setDataDevolucaoPrevista(LocalDate.of(1970, 1, 1));
        emprestimo.setDataEmprestimo(LocalDate.of(1970, 1, 1));
        emprestimo.setExemplar(exemplar);
        emprestimo.setId(1L);
        emprestimo.setUsuario(usuario);
        Optional<Emprestimo> ofResult = Optional.of(emprestimo);

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

        Exemplar exemplar3 = new Exemplar();
        exemplar3.setEmprestimos(new HashSet<>());
        exemplar3.setId(1L);
        exemplar3.setLivro(livro3);
        exemplar3.setNumero(10);
        exemplar3.setStatus(StatusExemplarEnum.DISPONIVEL);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(1L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Emprestimo emprestimo2 = new Emprestimo();
        emprestimo2.setDataDevolucao(LocalDate.of(1970, 1, 1));
        emprestimo2.setDataDevolucaoPrevista(LocalDate.of(1970, 1, 1));
        emprestimo2.setDataEmprestimo(LocalDate.of(1970, 1, 1));
        emprestimo2.setExemplar(exemplar3);
        emprestimo2.setId(1L);
        emprestimo2.setUsuario(usuario2);
        when(emprestimoRepository.save(Mockito.any())).thenReturn(emprestimo2);
        when(emprestimoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Livro livro4 = new Livro();
        livro4.setAno("Ano");
        livro4.setAutores(new HashSet<>());
        livro4.setCapa("Capa");
        livro4.setDescricao("Descricao");
        livro4.setEdicao(1);
        livro4.setEditora("Editora");
        livro4.setExemplares(new HashSet<>());
        livro4.setGeneros(new HashSet<>());
        livro4.setId(1L);
        livro4.setIsbn("Isbn");
        livro4.setLeituras(1);
        livro4.setLivroDoMes(true);
        livro4.setPaginas(1);
        livro4.setTitulo("Titulo");

        Exemplar exemplar4 = new Exemplar();
        exemplar4.setEmprestimos(new HashSet<>());
        exemplar4.setId(1L);
        exemplar4.setLivro(livro4);
        exemplar4.setNumero(10);
        exemplar4.setStatus(StatusExemplarEnum.DISPONIVEL);
        when(exemplarRepository.save(Mockito.any())).thenReturn(exemplar4);
        exemplarServiceImpl.realizarDevolucao(1L);
        verify(emprestimo).getExemplar();
        verify(emprestimo, atLeast(1)).setDataDevolucao(Mockito.any());
        verify(emprestimo).setDataDevolucaoPrevista(Mockito.any());
        verify(emprestimo).setDataEmprestimo(Mockito.any());
        verify(emprestimo).setExemplar(Mockito.any());
        verify(emprestimo).setId(Mockito.<Long>any());
        verify(emprestimo).setUsuario(Mockito.any());
        verify(emprestimoRepository).findById(Mockito.<Long>any());
        verify(emprestimoRepository).save(Mockito.any());
        verify(exemplarRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link ExemplarServiceImpl#consultarHistorico(Long)}
     */
    @Test
    void testConsultarHistorico() {
        ArrayList<Livro> livroList = new ArrayList<>();
        when(emprestimoRepository.consultarHistorico(Mockito.any())).thenReturn(livroList);

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Livro> actualConsultarHistoricoResult = exemplarServiceImpl.consultarHistorico(1L);
        verify(emprestimoRepository).consultarHistorico(Mockito.any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
        assertTrue(actualConsultarHistoricoResult.isEmpty());
        assertSame(livroList, actualConsultarHistoricoResult);
    }

    /**
     * Method under test: {@link ExemplarServiceImpl#consultarHistorico(Long)}
     */
    @Test
    void testConsultarHistorico2() {
        when(emprestimoRepository.consultarHistorico(Mockito.any()))
                .thenThrow(new BiblioConexException("An error occurred"));

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(BiblioConexException.class, () -> exemplarServiceImpl.consultarHistorico(1L));
        verify(emprestimoRepository).consultarHistorico(Mockito.any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
    }
}


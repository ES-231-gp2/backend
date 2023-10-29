package com.ufcg.es.biblioconex.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ufcg.es.biblioconex.dto.AlunoResenhasDTO;
import com.ufcg.es.biblioconex.dto.ResenhaDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Exemplar;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.model.Resenha;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import com.ufcg.es.biblioconex.repository.ResenhaRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ResenhaServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ResenhaServiceTest {
    @MockBean
    private LivroRepository livroRepository;

    @MockBean
    private ResenhaRepository resenhaRepository;

    @Autowired
    private ResenhaServiceImpl resenhaServiceImpl;

    @MockBean
    private UsuarioRepository usuarioRepository;

    /**
     * Method under test: {@link ResenhaServiceImpl#cadastrarResenha(ResenhaDTO)}
     */
    @Test
    void testCadastrarResenha() {
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
        when(usuarioRepository.findById(Mockito.<Long>any())).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> resenhaServiceImpl.cadastrarResenha(new ResenhaDTO()));
        verify(livroRepository).findById(Mockito.<Long>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ResenhaServiceImpl#cadastrarResenha(ResenhaDTO)}
     */
    @Test
    void testCadastrarResenha2() {
        Livro livro = mock(Livro.class);
        doNothing().when(livro).setAno(Mockito.<String>any());
        doNothing().when(livro).setAutores(Mockito.<Set<String>>any());
        doNothing().when(livro).setCapa(Mockito.<String>any());
        doNothing().when(livro).setDescricao(Mockito.<String>any());
        doNothing().when(livro).setEdicao(Mockito.<Integer>any());
        doNothing().when(livro).setEditora(Mockito.<String>any());
        doNothing().when(livro).setExemplares(Mockito.<Set<Exemplar>>any());
        doNothing().when(livro).setGeneros(Mockito.<Set<String>>any());
        doNothing().when(livro).setId(Mockito.<Long>any());
        doNothing().when(livro).setIsbn(Mockito.<String>any());
        doNothing().when(livro).setLeituras(Mockito.<Integer>any());
        doNothing().when(livro).setLivroDoMes(Mockito.<Boolean>any());
        doNothing().when(livro).setPaginas(Mockito.<Integer>any());
        doNothing().when(livro).setTitulo(Mockito.<String>any());
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

        Aluno aluno = new Aluno();
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);

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

        Resenha resenha = new Resenha();
        resenha.setAluno(aluno);
        resenha.setAprovada(true);
        resenha.setConteudo("Conteudo");
        resenha.setId(1L);
        resenha.setLivro(livro2);
        when(resenhaRepository.save(Mockito.<Resenha>any())).thenReturn(resenha);

        Aluno aluno2 = new Aluno();
        aluno2.setEmail("jane.doe@example.org");
        aluno2.setId(1L);
        aluno2.setNome("Nome");
        aluno2.setSenha("Senha");
        aluno2.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        aluno2.setEmail("jane.doe@example.org");
        aluno2.setId(1L);
        aluno2.setNome("Nome");
        aluno2.setSenha("Senha");
        aluno2.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult2 = Optional.of(aluno2);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        Resenha actualCadastrarResenhaResult = resenhaServiceImpl.cadastrarResenha(new ResenhaDTO());
        verify(livro).setAno(Mockito.<String>any());
        verify(livro).setAutores(Mockito.<Set<String>>any());
        verify(livro).setCapa(Mockito.<String>any());
        verify(livro).setDescricao(Mockito.<String>any());
        verify(livro).setEdicao(Mockito.<Integer>any());
        verify(livro).setEditora(Mockito.<String>any());
        verify(livro).setExemplares(Mockito.<Set<Exemplar>>any());
        verify(livro).setGeneros(Mockito.<Set<String>>any());
        verify(livro).setId(Mockito.<Long>any());
        verify(livro).setIsbn(Mockito.<String>any());
        verify(livro).setLeituras(Mockito.<Integer>any());
        verify(livro).setLivroDoMes(Mockito.<Boolean>any());
        verify(livro).setPaginas(Mockito.<Integer>any());
        verify(livro).setTitulo(Mockito.<String>any());
        verify(livroRepository).findById(Mockito.<Long>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
        verify(resenhaRepository).save(Mockito.<Resenha>any());
        assertSame(resenha, actualCadastrarResenhaResult);
    }

    /**
     * Method under test: {@link ResenhaServiceImpl#buscarResenhasPorLivro(Long)}
     */
    @Test
    void testBuscarResenhasPorLivro() {
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
        ArrayList<Resenha> resenhaList = new ArrayList<>();
        when(resenhaRepository.findByLivroId(Mockito.<Long>any())).thenReturn(resenhaList);
        List<Resenha> actualBuscarResenhasPorLivroResult = resenhaServiceImpl.buscarResenhasPorLivro(1L);
        verify(resenhaRepository).findByLivroId(Mockito.<Long>any());
        verify(livroRepository).findById(Mockito.<Long>any());
        assertTrue(actualBuscarResenhasPorLivroResult.isEmpty());
        assertSame(resenhaList, actualBuscarResenhasPorLivroResult);
    }

    /**
     * Method under test: {@link ResenhaServiceImpl#buscarResenhasPorLivro(Long)}
     */
    @Test
    void testBuscarResenhasPorLivro2() {
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
        when(resenhaRepository.findByLivroId(Mockito.<Long>any())).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> resenhaServiceImpl.buscarResenhasPorLivro(1L));
        verify(resenhaRepository).findByLivroId(Mockito.<Long>any());
        verify(livroRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ResenhaServiceImpl#buscarResenhasPorAluno(Long)}
     */
    @Test
    void testBuscarResenhasPorAluno() {
        ArrayList<Resenha> resenhaList = new ArrayList<>();
        when(resenhaRepository.findByAlunoId(Mockito.<Long>any())).thenReturn(resenhaList);

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Resenha> actualBuscarResenhasPorAlunoResult = resenhaServiceImpl.buscarResenhasPorAluno(1L);
        verify(resenhaRepository).findByAlunoId(Mockito.<Long>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
        assertTrue(actualBuscarResenhasPorAlunoResult.isEmpty());
        assertSame(resenhaList, actualBuscarResenhasPorAlunoResult);
    }

    /**
     * Method under test: {@link ResenhaServiceImpl#buscarResenhasPorAluno(Long)}
     */
    @Test
    void testBuscarResenhasPorAluno2() {
        when(resenhaRepository.findByAlunoId(Mockito.<Long>any())).thenThrow(new ObjetoNaoExisteException());

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ObjetoNaoExisteException.class, () -> resenhaServiceImpl.buscarResenhasPorAluno(1L));
        verify(resenhaRepository).findByAlunoId(Mockito.<Long>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ResenhaServiceImpl#buscarAlunosMaisResenhas()}
     */
    @Test
    void testBuscarAlunosMaisResenhas() {
        when(resenhaRepository.findTop10AlunosMaisResenhas()).thenReturn(new ArrayList<>());
        List<AlunoResenhasDTO> actualBuscarAlunosMaisResenhasResult = resenhaServiceImpl.buscarAlunosMaisResenhas();
        verify(resenhaRepository).findTop10AlunosMaisResenhas();
        assertTrue(actualBuscarAlunosMaisResenhasResult.isEmpty());
    }

    /**
     * Method under test: {@link ResenhaServiceImpl#buscarAlunosMaisResenhas()}
     */
    @Test
    void testBuscarAlunosMaisResenhas2() {
        when(resenhaRepository.findTop10AlunosMaisResenhas()).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> resenhaServiceImpl.buscarAlunosMaisResenhas());
        verify(resenhaRepository).findTop10AlunosMaisResenhas();
    }
}


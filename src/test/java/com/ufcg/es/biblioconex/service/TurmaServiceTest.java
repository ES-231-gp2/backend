package com.ufcg.es.biblioconex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ufcg.es.biblioconex.dto.TurmaDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Professor;
import com.ufcg.es.biblioconex.model.Texto;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaAluno;
import com.ufcg.es.biblioconex.model.TurmaProfessor;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.TurmaAlunoRepository;
import com.ufcg.es.biblioconex.repository.TurmaProfessorRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TurmaServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TurmaServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TurmaAlunoRepository turmaAlunoRepository;

    @MockBean
    private TurmaProfessorRepository turmaProfessorRepository;

    @MockBean
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaServiceImpl turmaServiceImpl;

    @MockBean
    private UsuarioRepository usuarioRepository;

    /**
     * Method under test: {@link TurmaServiceImpl#cadastrarTurma(TurmaDTO)}
     */
    @Test
    void testCadastrarTurma() {
        Professor professor = new Professor();
        professor.setEmail("jane.doe@example.org");
        professor.setId(1L);
        professor.setNome("Nome");
        professor.setSenha("Senha");
        professor.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);

        TurmaProfessor turmaProfessor = new TurmaProfessor();
        turmaProfessor.setId(1L);
        turmaProfessor.setProfessor(professor);
        turmaProfessor.setTurma(turma);
        when(turmaProfessorRepository.save(Mockito.<TurmaProfessor>any())).thenReturn(turmaProfessor);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");

        Turma turma2 = new Turma();
        turma2.setId(1L);
        turma2.setSerie("Serie");
        turma2.setTexto(texto2);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);

        Professor professor2 = new Professor();
        professor2.setEmail("jane.doe@example.org");
        professor2.setId(1L);
        professor2.setNome("Nome");
        professor2.setSenha("Senha");
        professor2.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        professor2.setEmail("jane.doe@example.org");
        professor2.setId(1L);
        professor2.setNome("Nome");
        professor2.setSenha("Senha");
        professor2.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(professor2);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Turma actualCadastrarTurmaResult = turmaServiceImpl.cadastrarTurma(new TurmaDTO());
        verify(usuarioRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
        verify(turmaProfessorRepository).save(Mockito.<TurmaProfessor>any());
        assertSame(turma2, actualCadastrarTurmaResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#cadastrarTurma(TurmaDTO)}
     */
    @Test
    void testCadastrarTurma2() {
        Professor professor = new Professor();
        professor.setEmail("jane.doe@example.org");
        professor.setId(1L);
        professor.setNome("Nome");
        professor.setSenha("Senha");
        professor.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);

        TurmaProfessor turmaProfessor = new TurmaProfessor();
        turmaProfessor.setId(1L);
        turmaProfessor.setProfessor(professor);
        turmaProfessor.setTurma(turma);
        when(turmaProfessorRepository.save(Mockito.<TurmaProfessor>any())).thenReturn(turmaProfessor);
        when(turmaRepository.save(Mockito.<Turma>any())).thenThrow(new ObjetoNaoExisteException());

        Professor professor2 = new Professor();
        professor2.setEmail("jane.doe@example.org");
        professor2.setId(1L);
        professor2.setNome("Nome");
        professor2.setSenha("Senha");
        professor2.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        professor2.setEmail("jane.doe@example.org");
        professor2.setId(1L);
        professor2.setNome("Nome");
        professor2.setSenha("Senha");
        professor2.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(professor2);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ObjetoNaoExisteException.class, () -> turmaServiceImpl.cadastrarTurma(new TurmaDTO()));
        verify(usuarioRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#cadastrarTurma(TurmaDTO)}
     */
    @Test
    void testCadastrarTurma3() {
        when(turmaProfessorRepository.save(Mockito.<TurmaProfessor>any())).thenThrow(new ObjetoNaoExisteException());

        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma);

        Professor professor = new Professor();
        professor.setEmail("jane.doe@example.org");
        professor.setId(1L);
        professor.setNome("Nome");
        professor.setSenha("Senha");
        professor.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        professor.setEmail("jane.doe@example.org");
        professor.setId(1L);
        professor.setNome("Nome");
        professor.setSenha("Senha");
        professor.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(professor);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ObjetoNaoExisteException.class,
                () -> turmaServiceImpl.cadastrarTurma(TurmaDTO.builder().professorId(1L).serie("Serie").build()));
        verify(usuarioRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
        verify(turmaProfessorRepository).save(Mockito.<TurmaProfessor>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarTurmas(Long)}
     */
    @Test
    void testBuscarTurmas() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Turma> actualBuscarTurmasResult = turmaServiceImpl.buscarTurmas(1L);
        verify(turmaRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualBuscarTurmasResult.size());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarTurmas(Long)}
     */
    @Test
    void testBuscarTurmas2() {
        ArrayList<Turma> turmaList = new ArrayList<>();
        when(turmaRepository.findAll()).thenReturn(turmaList);
        List<Turma> actualBuscarTurmasResult = turmaServiceImpl.buscarTurmas(null);
        verify(turmaRepository).findAll();
        assertTrue(actualBuscarTurmasResult.isEmpty());
        assertSame(turmaList, actualBuscarTurmasResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarTurmas(Long)}
     */
    @Test
    void testBuscarTurmas3() {
        when(turmaRepository.findAll()).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> turmaServiceImpl.buscarTurmas(null));
        verify(turmaRepository).findAll();
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTurma(Long, TurmaDTO)}
     */
    @Test
    void testAlterarTurma() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");

        Turma turma2 = new Turma();
        turma2.setId(1L);
        turma2.setSerie("Serie");
        turma2.setTexto(texto2);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Turma actualAlterarTurmaResult = turmaServiceImpl.alterarTurma(1L, new TurmaDTO());
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
        assertSame(turma2, actualAlterarTurmaResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTurma(Long, TurmaDTO)}
     */
    @Test
    void testAlterarTurma2() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.save(Mockito.<Turma>any())).thenThrow(new ObjetoNaoExisteException());
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ObjetoNaoExisteException.class, () -> turmaServiceImpl.alterarTurma(1L, new TurmaDTO()));
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTurma(Long, TurmaDTO)}
     */
    @Test
    void testAlterarTurma3() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");
        Turma turma = mock(Turma.class);
        doNothing().when(turma).setId(Mockito.<Long>any());
        doNothing().when(turma).setSerie(Mockito.<String>any());
        doNothing().when(turma).setTexto(Mockito.<Texto>any());
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");

        Turma turma2 = new Turma();
        turma2.setId(1L);
        turma2.setSerie("Serie");
        turma2.setTexto(texto2);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Turma actualAlterarTurmaResult = turmaServiceImpl.alterarTurma(1L, new TurmaDTO());
        verify(turma).setId(Mockito.<Long>any());
        verify(turma, atLeast(1)).setSerie(Mockito.<String>any());
        verify(turma).setTexto(Mockito.<Texto>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
        assertSame(turma2, actualAlterarTurmaResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarAlunosTurma(Long)}
     */
    @Test
    void testBuscarAlunosTurma() {
        when(turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        List<Aluno> actualBuscarAlunosTurmaResult = turmaServiceImpl.buscarAlunosTurma(1L);
        verify(turmaAlunoRepository).findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any());
        assertTrue(actualBuscarAlunosTurmaResult.isEmpty());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarAlunosTurma(Long)}
     */
    @Test
    void testBuscarAlunosTurma2() {
        Aluno aluno = new Aluno();
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);

        TurmaAluno turmaAluno = new TurmaAluno();
        turmaAluno.setAluno(aluno);
        turmaAluno.setId(1L);
        turmaAluno.setTurma(turma);

        ArrayList<TurmaAluno> turmaAlunoList = new ArrayList<>();
        turmaAlunoList.add(turmaAluno);
        when(turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any())).thenReturn(turmaAlunoList);
        List<Aluno> actualBuscarAlunosTurmaResult = turmaServiceImpl.buscarAlunosTurma(1L);
        verify(turmaAlunoRepository).findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any());
        assertEquals(1, actualBuscarAlunosTurmaResult.size());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarAlunosTurma(Long)}
     */
    @Test
    void testBuscarAlunosTurma3() {
        Aluno aluno = new Aluno();
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);

        TurmaAluno turmaAluno = new TurmaAluno();
        turmaAluno.setAluno(aluno);
        turmaAluno.setId(1L);
        turmaAluno.setTurma(turma);

        Aluno aluno2 = new Aluno();
        aluno2.setEmail("john.smith@example.org");
        aluno2.setId(2L);
        aluno2.setNome("42");
        aluno2.setSenha("42");
        aluno2.setTipoUsuario(TipoUsuarioEnum.PROFESSOR);

        Texto texto2 = new Texto();
        texto2.setConteudo("com.ufcg.es.biblioconex.model.Texto");
        texto2.setId(2L);
        texto2.setNome("com.ufcg.es.biblioconex.model.Texto");
        texto2.setResumo("com.ufcg.es.biblioconex.model.Texto");

        Turma turma2 = new Turma();
        turma2.setId(2L);
        turma2.setSerie("com.ufcg.es.biblioconex.model.Turma");
        turma2.setTexto(texto2);

        TurmaAluno turmaAluno2 = new TurmaAluno();
        turmaAluno2.setAluno(aluno2);
        turmaAluno2.setId(2L);
        turmaAluno2.setTurma(turma2);

        ArrayList<TurmaAluno> turmaAlunoList = new ArrayList<>();
        turmaAlunoList.add(turmaAluno2);
        turmaAlunoList.add(turmaAluno);
        when(turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any())).thenReturn(turmaAlunoList);
        List<Aluno> actualBuscarAlunosTurmaResult = turmaServiceImpl.buscarAlunosTurma(1L);
        verify(turmaAlunoRepository).findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any());
        assertEquals(2, actualBuscarAlunosTurmaResult.size());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarAlunosTurma(Long)}
     */
    @Test
    void testBuscarAlunosTurma4() {
        when(turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any()))
                .thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> turmaServiceImpl.buscarAlunosTurma(1L));
        verify(turmaAlunoRepository).findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#visualizarTexto(Long)}
     */
    @Test
    void testVisualizarTexto() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Texto actualVisualizarTextoResult = turmaServiceImpl.visualizarTexto(1L);
        verify(turmaRepository).findById(Mockito.<Long>any());
        assertSame(texto, actualVisualizarTextoResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#visualizarTexto(Long)}
     */
    @Test
    void testVisualizarTexto2() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");
        Turma turma = mock(Turma.class);
        when(turma.getTexto()).thenReturn(texto2);
        doNothing().when(turma).setId(Mockito.<Long>any());
        doNothing().when(turma).setSerie(Mockito.<String>any());
        doNothing().when(turma).setTexto(Mockito.<Texto>any());
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Texto actualVisualizarTextoResult = turmaServiceImpl.visualizarTexto(1L);
        verify(turma).getTexto();
        verify(turma).setId(Mockito.<Long>any());
        verify(turma).setSerie(Mockito.<String>any());
        verify(turma).setTexto(Mockito.<Texto>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
        assertSame(texto2, actualVisualizarTextoResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTexto(Long, Texto)}
     */
    @Test
    void testAlterarTexto() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");

        Turma turma2 = new Turma();
        turma2.setId(1L);
        turma2.setSerie("Serie");
        turma2.setTexto(texto2);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Texto texto3 = new Texto();
        texto3.setConteudo("Conteudo");
        texto3.setId(1L);
        texto3.setNome("Nome");
        texto3.setResumo("Resumo");
        Texto actualAlterarTextoResult = turmaServiceImpl.alterarTexto(1L, texto3);
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
        assertSame(texto2, actualAlterarTextoResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTexto(Long, Texto)}
     */
    @Test
    void testAlterarTexto2() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.save(Mockito.<Turma>any())).thenThrow(new ObjetoNaoExisteException());
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");
        assertThrows(ObjetoNaoExisteException.class, () -> turmaServiceImpl.alterarTexto(1L, texto2));
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTexto(Long, Texto)}
     */
    @Test
    void testAlterarTexto3() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");

        Texto texto3 = new Texto();
        texto3.setConteudo("Conteudo");
        texto3.setId(1L);
        texto3.setNome("Nome");
        texto3.setResumo("Resumo");
        Turma turma2 = mock(Turma.class);
        when(turma2.getTexto()).thenReturn(texto3);
        doNothing().when(turma2).setId(Mockito.<Long>any());
        doNothing().when(turma2).setSerie(Mockito.<String>any());
        doNothing().when(turma2).setTexto(Mockito.<Texto>any());
        turma2.setId(1L);
        turma2.setSerie("Serie");
        turma2.setTexto(texto2);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Texto texto4 = new Texto();
        texto4.setConteudo("Conteudo");
        texto4.setId(1L);
        texto4.setNome("Nome");
        texto4.setResumo("Resumo");
        Texto actualAlterarTextoResult = turmaServiceImpl.alterarTexto(1L, texto4);
        verify(turma2).getTexto();
        verify(turma2).setId(Mockito.<Long>any());
        verify(turma2).setSerie(Mockito.<String>any());
        verify(turma2).setTexto(Mockito.<Texto>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
        assertSame(texto3, actualAlterarTextoResult);
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTexto(Long, Texto)}
     */
    @Test
    void testAlterarTexto4() {
        Texto texto = new Texto();
        texto.setConteudo("Conteudo");
        texto.setId(1L);
        texto.setNome("Nome");
        texto.setResumo("Resumo");
        Turma turma = mock(Turma.class);
        doNothing().when(turma).setId(Mockito.<Long>any());
        doNothing().when(turma).setSerie(Mockito.<String>any());
        doNothing().when(turma).setTexto(Mockito.<Texto>any());
        turma.setId(1L);
        turma.setSerie("Serie");
        turma.setTexto(texto);
        Optional<Turma> ofResult = Optional.of(turma);

        Texto texto2 = new Texto();
        texto2.setConteudo("Conteudo");
        texto2.setId(1L);
        texto2.setNome("Nome");
        texto2.setResumo("Resumo");

        Texto texto3 = new Texto();
        texto3.setConteudo("Conteudo");
        texto3.setId(1L);
        texto3.setNome("Nome");
        texto3.setResumo("Resumo");
        Turma turma2 = mock(Turma.class);
        when(turma2.getTexto()).thenReturn(texto3);
        doNothing().when(turma2).setId(Mockito.<Long>any());
        doNothing().when(turma2).setSerie(Mockito.<String>any());
        doNothing().when(turma2).setTexto(Mockito.<Texto>any());
        turma2.setId(1L);
        turma2.setSerie("Serie");
        turma2.setTexto(texto2);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Texto texto4 = new Texto();
        texto4.setConteudo("Conteudo");
        texto4.setId(1L);
        texto4.setNome("Nome");
        texto4.setResumo("Resumo");
        Texto actualAlterarTextoResult = turmaServiceImpl.alterarTexto(1L, texto4);
        verify(turma2).getTexto();
        verify(turma2).setId(Mockito.<Long>any());
        verify(turma).setId(Mockito.<Long>any());
        verify(turma2).setSerie(Mockito.<String>any());
        verify(turma).setSerie(Mockito.<String>any());
        verify(turma2).setTexto(Mockito.<Texto>any());
        verify(turma, atLeast(1)).setTexto(Mockito.<Texto>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turmaRepository).save(Mockito.<Turma>any());
        assertSame(texto3, actualAlterarTextoResult);
    }
}


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
import com.ufcg.es.biblioconex.enums.SerieEnum;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaAluno;
import com.ufcg.es.biblioconex.repository.TurmaAlunoRepository;
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
class TurmaServiceImplDiffblueTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TurmaAlunoRepository turmaAlunoRepository;

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
        Turma turma = new Turma();
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma);
        assertSame(turma, turmaServiceImpl.cadastrarTurma(new TurmaDTO()));
        verify(turmaRepository).save(Mockito.<Turma>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#cadastrarTurma(TurmaDTO)}
     */
    @Test
    void testCadastrarTurma2() {
        when(turmaRepository.save(Mockito.<Turma>any())).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> turmaServiceImpl.cadastrarTurma(new TurmaDTO()));
        verify(turmaRepository).save(Mockito.<Turma>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarTurmas(Long)}
     */
    @Test
    void testBuscarTurmas() {
        Turma turma = new Turma();
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertEquals(1, turmaServiceImpl.buscarTurmas(1L).size());
        verify(turmaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarTurmas(Long)}
     */
    @Test
    void testBuscarTurmas2() {
        ArrayList<Turma> turmaList = new ArrayList<>();
        when(turmaRepository.findAll()).thenReturn(turmaList);
        List<Turma> actualBuscarTurmasResult = turmaServiceImpl.buscarTurmas(null);
        assertSame(turmaList, actualBuscarTurmasResult);
        assertTrue(actualBuscarTurmasResult.isEmpty());
        verify(turmaRepository).findAll();
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
        Turma turma = new Turma();
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);
        Optional<Turma> ofResult = Optional.of(turma);

        Turma turma2 = new Turma();
        turma2.setId(1L);
        turma2.setProfessor(1L);
        turma2.setSerie(SerieEnum.EF_1ANO);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(turma2, turmaServiceImpl.alterarTurma(1L, new TurmaDTO()));
        verify(turmaRepository).save(Mockito.<Turma>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTurma(Long, TurmaDTO)}
     */
    @Test
    void testAlterarTurma2() {
        Turma turma = new Turma();
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.save(Mockito.<Turma>any())).thenThrow(new ObjetoNaoExisteException());
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ObjetoNaoExisteException.class, () -> turmaServiceImpl.alterarTurma(1L, new TurmaDTO()));
        verify(turmaRepository).save(Mockito.<Turma>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#alterarTurma(Long, TurmaDTO)}
     */
    @Test
    void testAlterarTurma3() {
        Turma turma = mock(Turma.class);
        doNothing().when(turma).setId(Mockito.<Long>any());
        doNothing().when(turma).setProfessor(Mockito.<Long>any());
        doNothing().when(turma).setSerie(Mockito.<SerieEnum>any());
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);
        Optional<Turma> ofResult = Optional.of(turma);

        Turma turma2 = new Turma();
        turma2.setId(1L);
        turma2.setProfessor(1L);
        turma2.setSerie(SerieEnum.EF_1ANO);
        when(turmaRepository.save(Mockito.<Turma>any())).thenReturn(turma2);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(turma2, turmaServiceImpl.alterarTurma(1L, new TurmaDTO()));
        verify(turmaRepository).save(Mockito.<Turma>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(turma).setId(Mockito.<Long>any());
        verify(turma, atLeast(1)).setProfessor(Mockito.<Long>any());
        verify(turma, atLeast(1)).setSerie(Mockito.<SerieEnum>any());
    }

    /**
     * Method under test: {@link TurmaServiceImpl#buscarAlunosTurma(Long)}
     */
    @Test
    void testBuscarAlunosTurma() {
        when(turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        assertTrue(turmaServiceImpl.buscarAlunosTurma(1L).isEmpty());
        verify(turmaAlunoRepository).findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any());
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

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);

        TurmaAluno turmaAluno = new TurmaAluno();
        turmaAluno.setAluno(aluno);
        turmaAluno.setId(1L);
        turmaAluno.setTurma(turma);

        ArrayList<TurmaAluno> turmaAlunoList = new ArrayList<>();
        turmaAlunoList.add(turmaAluno);
        when(turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any())).thenReturn(turmaAlunoList);
        assertEquals(1, turmaServiceImpl.buscarAlunosTurma(1L).size());
        verify(turmaAlunoRepository).findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any());
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

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);

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

        Turma turma2 = new Turma();
        turma2.setId(2L);
        turma2.setProfessor(0L);
        turma2.setSerie(SerieEnum.EF_2ANO);

        TurmaAluno turmaAluno2 = new TurmaAluno();
        turmaAluno2.setAluno(aluno2);
        turmaAluno2.setId(2L);
        turmaAluno2.setTurma(turma2);

        ArrayList<TurmaAluno> turmaAlunoList = new ArrayList<>();
        turmaAlunoList.add(turmaAluno2);
        turmaAlunoList.add(turmaAluno);
        when(turmaAlunoRepository.findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any())).thenReturn(turmaAlunoList);
        assertEquals(2, turmaServiceImpl.buscarAlunosTurma(1L).size());
        verify(turmaAlunoRepository).findAllByTurmaIdOrderByAlunoNome(Mockito.<Long>any());
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
}


package com.ufcg.es.biblioconex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ufcg.es.biblioconex.dto.AlunoDTO;
import com.ufcg.es.biblioconex.enums.SerieEnum;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.EmailJaExisteException;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaAluno;
import com.ufcg.es.biblioconex.model.Usuario;
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

@ContextConfiguration(classes = {AlunoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AlunoServiceImplDiffblueTest {
    @Autowired
    private AlunoServiceImpl alunoServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TurmaAlunoRepository turmaAlunoRepository;

    @MockBean
    private TurmaRepository turmaRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    /**
     * Method under test: {@link AlunoServiceImpl#cadastrarAluno(AlunoDTO)}
     */
    @Test
    void testCadastrarAluno() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(usuario);
        assertThrows(EmailJaExisteException.class, () -> alunoServiceImpl.cadastrarAluno(new AlunoDTO()));
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AlunoServiceImpl#cadastrarAluno(AlunoDTO)}
     */
    @Test
    void testCadastrarAluno2() {
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> alunoServiceImpl.cadastrarAluno(new AlunoDTO()));
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AlunoServiceImpl#buscarAlunos(Long)}
     */
    @Test
    void testBuscarAlunos() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertEquals(1, alunoServiceImpl.buscarAlunos(1L).size());
        verify(usuarioRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AlunoServiceImpl#buscarAlunos(Long)}
     */
    @Test
    void testBuscarAlunos2() {
        ArrayList<Usuario> usuarioList = new ArrayList<>();
        when(usuarioRepository.findAll()).thenReturn(usuarioList);
        List<Usuario> actualBuscarAlunosResult = alunoServiceImpl.buscarAlunos(null);
        assertSame(usuarioList, actualBuscarAlunosResult);
        assertTrue(actualBuscarAlunosResult.isEmpty());
        verify(usuarioRepository).findAll();
    }

    /**
     * Method under test: {@link AlunoServiceImpl#buscarAlunos(Long)}
     */
    @Test
    void testBuscarAlunos3() {
        when(usuarioRepository.findAll()).thenThrow(new EmailJaExisteException());
        assertThrows(EmailJaExisteException.class, () -> alunoServiceImpl.buscarAlunos(null));
        verify(usuarioRepository).findAll();
    }

    /**
     * Method under test: {@link AlunoServiceImpl#alterarAluno(Long, AlunoDTO)}
     */
    @Test
    void testAlterarAluno() {
        Aluno aluno = new Aluno();
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(aluno);

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(EmailJaExisteException.class, () -> alunoServiceImpl.alterarAluno(1L, new AlunoDTO()));
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AlunoServiceImpl#alterarAluno(Long, AlunoDTO)}
     */
    @Test
    void testAlterarAluno2() {
        Turma turma = new Turma();
        turma.setId(1L);
        turma.setProfessor(1L);
        turma.setSerie(SerieEnum.EF_1ANO);
        Optional<Turma> ofResult = Optional.of(turma);
        when(turmaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Aluno aluno = new Aluno();
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult2 = Optional.of(aluno);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenThrow(new ObjetoNaoExisteException());
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(ObjetoNaoExisteException.class, () -> alunoServiceImpl.alterarAluno(1L,
                AlunoDTO.builder().email("jane.doe@example.org").nome("Nome").senha("Senha").turmaId(1L).build()));
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AlunoServiceImpl#buscarTurmaAluno(Long)}
     */
    @Test
    void testBuscarTurmaAluno() {
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
        when(turmaAlunoRepository.findTurmaByAlunoId(Mockito.<Long>any())).thenReturn(turmaAluno);
        assertSame(turma, alunoServiceImpl.buscarTurmaAluno(1L));
        verify(turmaAlunoRepository).findTurmaByAlunoId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AlunoServiceImpl#buscarTurmaAluno(Long)}
     */
    @Test
    void testBuscarTurmaAluno2() {
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

        Turma turma2 = new Turma();
        turma2.setId(1L);
        turma2.setProfessor(1L);
        turma2.setSerie(SerieEnum.EF_1ANO);
        TurmaAluno turmaAluno = mock(TurmaAluno.class);
        when(turmaAluno.getTurma()).thenReturn(turma2);
        doNothing().when(turmaAluno).setAluno(Mockito.<Aluno>any());
        doNothing().when(turmaAluno).setId(Mockito.<Long>any());
        doNothing().when(turmaAluno).setTurma(Mockito.<Turma>any());
        turmaAluno.setAluno(aluno);
        turmaAluno.setId(1L);
        turmaAluno.setTurma(turma);
        when(turmaAlunoRepository.findTurmaByAlunoId(Mockito.<Long>any())).thenReturn(turmaAluno);
        assertSame(turma2, alunoServiceImpl.buscarTurmaAluno(1L));
        verify(turmaAlunoRepository).findTurmaByAlunoId(Mockito.<Long>any());
        verify(turmaAluno).getTurma();
        verify(turmaAluno).setAluno(Mockito.<Aluno>any());
        verify(turmaAluno).setId(Mockito.<Long>any());
        verify(turmaAluno).setTurma(Mockito.<Turma>any());
    }
}


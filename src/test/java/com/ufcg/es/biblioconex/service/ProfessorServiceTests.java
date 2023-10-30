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

import com.ufcg.es.biblioconex.dto.ProfessorDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.EmailJaExisteException;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Professor;
import com.ufcg.es.biblioconex.model.Texto;
import com.ufcg.es.biblioconex.model.Turma;
import com.ufcg.es.biblioconex.model.TurmaProfessor;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.TurmaProfessorRepository;
import com.ufcg.es.biblioconex.repository.TurmaRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProfessorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProfessorServiceTests {
    @Autowired
    private ProfessorServiceImpl professorServiceImpl;

    @MockBean
    private TurmaProfessorRepository turmaProfessorRepository;

    @MockBean
    private TurmaRepository turmaRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    /**
     * Method under test: {@link ProfessorServiceImpl#cadastrarProfessor(ProfessorDTO)}
     */
    @Test
    void testCadastrarProfessor() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(usuario);
        assertThrows(EmailJaExisteException.class, () -> professorServiceImpl.cadastrarProfessor(new ProfessorDTO()));
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#cadastrarProfessor(ProfessorDTO)}
     */
    @Test
    void testCadastrarProfessor2() {
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenThrow(new ObjetoNaoExisteException());
        assertThrows(ObjetoNaoExisteException.class, () -> professorServiceImpl.cadastrarProfessor(new ProfessorDTO()));
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#buscarProfessores(Long)}
     */
    @Test
    void testBuscarProfessores() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Usuario> actualBuscarProfessoresResult = professorServiceImpl.buscarProfessores(1L);
        verify(usuarioRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualBuscarProfessoresResult.size());
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#buscarProfessores(Long)}
     */
    @Test
    void testBuscarProfessores2() {
        ArrayList<Usuario> usuarioList = new ArrayList<>();
        when(usuarioRepository.findAll()).thenReturn(usuarioList);
        List<Usuario> actualBuscarProfessoresResult = professorServiceImpl.buscarProfessores(null);
        verify(usuarioRepository).findAll();
        assertTrue(actualBuscarProfessoresResult.isEmpty());
        assertSame(usuarioList, actualBuscarProfessoresResult);
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#buscarProfessores(Long)}
     */
    @Test
    void testBuscarProfessores3() {
        when(usuarioRepository.findAll()).thenThrow(new EmailJaExisteException());
        assertThrows(EmailJaExisteException.class, () -> professorServiceImpl.buscarProfessores(null));
        verify(usuarioRepository).findAll();
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#alterarProfessor(Long, ProfessorDTO)}
     */
    @Test
    void testAlterarProfessor() {
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
        when(usuarioRepository.save(Mockito.<Usuario>any())).thenReturn(professor2);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Professor actualAlterarProfessorResult = professorServiceImpl.alterarProfessor(1L, new ProfessorDTO());
        verify(usuarioRepository).findById(Mockito.<Long>any());
        verify(usuarioRepository).save(Mockito.<Usuario>any());
        assertSame(professor2, actualAlterarProfessorResult);
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#alterarProfessor(Long, ProfessorDTO)}
     */
    @Test
    void testAlterarProfessor2() {
        when(turmaProfessorRepository.save(Mockito.<TurmaProfessor>any())).thenThrow(new EmailJaExisteException());

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
        Professor professor = mock(Professor.class);
        doNothing().when(professor).setEmail(Mockito.<String>any());
        doNothing().when(professor).setId(Mockito.<Long>any());
        doNothing().when(professor).setNome(Mockito.<String>any());
        doNothing().when(professor).setSenha(Mockito.<String>any());
        doNothing().when(professor).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
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
        Optional<Usuario> ofResult2 = Optional.of(professor);
        Usuario usuario = mock(Usuario.class);
        doNothing().when(usuario).setEmail(Mockito.<String>any());
        doNothing().when(usuario).setId(Mockito.<Long>any());
        doNothing().when(usuario).setNome(Mockito.<String>any());
        doNothing().when(usuario).setSenha(Mockito.<String>any());
        doNothing().when(usuario).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.save(Mockito.<Usuario>any())).thenReturn(usuario);
        when(usuarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        HashSet<Long> resultLongSet = new HashSet<>();
        resultLongSet.add(1L);
        ProfessorDTO professorDTO = mock(ProfessorDTO.class);
        when(professorDTO.getEmail()).thenReturn("jane.doe@example.org");
        when(professorDTO.getNome()).thenReturn("Nome");
        when(professorDTO.getSenha()).thenReturn("Senha");
        when(professorDTO.getTurmasIds()).thenReturn(resultLongSet);
        assertThrows(EmailJaExisteException.class, () -> professorServiceImpl.alterarProfessor(1L, professorDTO));
        verify(professorDTO).getEmail();
        verify(professorDTO).getNome();
        verify(professorDTO).getSenha();
        verify(professorDTO).getTurmasIds();
        verify(usuario).setEmail(Mockito.<String>any());
        verify(professor, atLeast(1)).setEmail(Mockito.<String>any());
        verify(usuario).setId(Mockito.<Long>any());
        verify(professor, atLeast(1)).setId(Mockito.<Long>any());
        verify(usuario).setNome(Mockito.<String>any());
        verify(professor, atLeast(1)).setNome(Mockito.<String>any());
        verify(usuario).setSenha(Mockito.<String>any());
        verify(professor, atLeast(1)).setSenha(Mockito.<String>any());
        verify(usuario).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        verify(professor, atLeast(1)).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        verify(turmaRepository).findById(Mockito.<Long>any());
        verify(usuarioRepository).findById(Mockito.<Long>any());
        verify(turmaProfessorRepository).save(Mockito.<TurmaProfessor>any());
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#buscarTurmasProfessor(Long)}
     */
    @Test
    void testBuscarTurmasProfessor() {
        ArrayList<Turma> turmaList = new ArrayList<>();
        when(turmaProfessorRepository.findTurmasByProfessorId(Mockito.<Long>any())).thenReturn(turmaList);
        List<Turma> actualBuscarTurmasProfessorResult = professorServiceImpl.buscarTurmasProfessor(1L);
        verify(turmaProfessorRepository).findTurmasByProfessorId(Mockito.<Long>any());
        assertTrue(actualBuscarTurmasProfessorResult.isEmpty());
        assertSame(turmaList, actualBuscarTurmasProfessorResult);
    }

    /**
     * Method under test: {@link ProfessorServiceImpl#buscarTurmasProfessor(Long)}
     */
    @Test
    void testBuscarTurmasProfessor2() {
        when(turmaProfessorRepository.findTurmasByProfessorId(Mockito.<Long>any()))
                .thenThrow(new EmailJaExisteException());
        assertThrows(EmailJaExisteException.class, () -> professorServiceImpl.buscarTurmasProfessor(1L));
        verify(turmaProfessorRepository).findTurmasByProfessorId(Mockito.<Long>any());
    }
}


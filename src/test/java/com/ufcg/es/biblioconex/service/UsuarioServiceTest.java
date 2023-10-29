package com.ufcg.es.biblioconex.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ufcg.es.biblioconex.dto.UsuarioDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.exception.SenhaIncorretaException;
import com.ufcg.es.biblioconex.exception.UsuarioNaoAutorizadoException;
import com.ufcg.es.biblioconex.exception.UsuarioNaoEncontradoException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UsuarioServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UsuarioServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    /**
     * Method under test: {@link UsuarioServiceImpl#login(String, String)}
     */
    @Test
    void testLogin() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(usuario);
        Usuario actualLoginResult = usuarioServiceImpl.login("jane.doe@example.org", "Senha");
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
        assertSame(usuario, actualLoginResult);
    }

    /**
     * Method under test: {@link UsuarioServiceImpl#login(String, String)}
     */
    @Test
    void testLogin2() {
        Aluno aluno = mock(Aluno.class);
        when(aluno.getSenha()).thenReturn("foo");
        doNothing().when(aluno).setEmail(Mockito.<String>any());
        doNothing().when(aluno).setId(Mockito.<Long>any());
        doNothing().when(aluno).setNome(Mockito.<String>any());
        doNothing().when(aluno).setSenha(Mockito.<String>any());
        doNothing().when(aluno).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(aluno);
        assertThrows(SenhaIncorretaException.class, () -> usuarioServiceImpl.login("jane.doe@example.org", "Senha"));
        verify(aluno).getSenha();
        verify(aluno).setEmail(Mockito.<String>any());
        verify(aluno).setId(Mockito.<Long>any());
        verify(aluno).setNome(Mockito.<String>any());
        verify(aluno).setSenha(Mockito.<String>any());
        verify(aluno).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UsuarioServiceImpl#cadastrarUsuario(String, String, UsuarioDTO)}
     */
    @Test
    void testCadastrarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(usuario);
        assertThrows(UsuarioNaoAutorizadoException.class, () -> usuarioServiceImpl.cadastrarUsuario("Login", "Senha",
                new UsuarioDTO("jane.doe@example.org", "Senha", TipoUsuarioEnum.ALUNO)));
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UsuarioServiceImpl#cadastrarUsuario(String, String, UsuarioDTO)}
     */
    @Test
    void testCadastrarUsuario2() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Usuario>>any())).thenReturn(usuario);
        Aluno aluno = mock(Aluno.class);
        when(aluno.getTipoUsuario()).thenReturn(TipoUsuarioEnum.BIBLIOTECARIO);
        when(aluno.getSenha()).thenReturn("Senha");
        doNothing().when(aluno).setEmail(Mockito.<String>any());
        doNothing().when(aluno).setId(Mockito.<Long>any());
        doNothing().when(aluno).setNome(Mockito.<String>any());
        doNothing().when(aluno).setSenha(Mockito.<String>any());
        doNothing().when(aluno).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(1L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.save(Mockito.<Usuario>any())).thenReturn(usuario2);
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(aluno);
        Usuario actualCadastrarUsuarioResult = usuarioServiceImpl.cadastrarUsuario("Login", "Senha",
                new UsuarioDTO("jane.doe@example.org", "Senha", TipoUsuarioEnum.ALUNO));
        verify(aluno).getSenha();
        verify(aluno).getTipoUsuario();
        verify(aluno).setEmail(Mockito.<String>any());
        verify(aluno).setId(Mockito.<Long>any());
        verify(aluno).setNome(Mockito.<String>any());
        verify(aluno).setSenha(Mockito.<String>any());
        verify(aluno).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Usuario>>any());
        verify(usuarioRepository).save(Mockito.<Usuario>any());
        assertSame(usuario2, actualCadastrarUsuarioResult);
    }

    /**
     * Method under test: {@link UsuarioServiceImpl#cadastrarUsuario(String, String, UsuarioDTO)}
     */
    @Test
    void testCadastrarUsuario3() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Usuario>>any())).thenReturn(usuario);
        Aluno aluno = mock(Aluno.class);
        when(aluno.getTipoUsuario()).thenReturn(TipoUsuarioEnum.BIBLIOTECARIO);
        when(aluno.getSenha()).thenReturn("Senha");
        doNothing().when(aluno).setEmail(Mockito.<String>any());
        doNothing().when(aluno).setId(Mockito.<Long>any());
        doNothing().when(aluno).setNome(Mockito.<String>any());
        doNothing().when(aluno).setSenha(Mockito.<String>any());
        doNothing().when(aluno).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        aluno.setEmail("jane.doe@example.org");
        aluno.setId(1L);
        aluno.setNome("Nome");
        aluno.setSenha("Senha");
        aluno.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioRepository.save(Mockito.<Usuario>any())).thenThrow(new UsuarioNaoEncontradoException());
        when(usuarioRepository.findByEmail(Mockito.<String>any())).thenReturn(aluno);
        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioServiceImpl.cadastrarUsuario("Login", "Senha",
                new UsuarioDTO("jane.doe@example.org", "Senha", TipoUsuarioEnum.ALUNO)));
        verify(aluno).getSenha();
        verify(aluno).getTipoUsuario();
        verify(aluno).setEmail(Mockito.<String>any());
        verify(aluno).setId(Mockito.<Long>any());
        verify(aluno).setNome(Mockito.<String>any());
        verify(aluno).setSenha(Mockito.<String>any());
        verify(aluno).setTipoUsuario(Mockito.<TipoUsuarioEnum>any());
        verify(usuarioRepository).findByEmail(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Usuario>>any());
        verify(usuarioRepository).save(Mockito.<Usuario>any());
    }
}


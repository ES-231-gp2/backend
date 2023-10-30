package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.UsuarioDTO;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.service.UsuarioService;
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

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LoginController.class})
@ExtendWith(SpringExtension.class)
class LoginControllerTests {
    @Autowired
    private LoginController loginController;

    @MockBean
    private UsuarioService usuarioService;

    /**
     * Method under test: {@link LoginController#cadastrarUsuario(UsuarioDTO, String, String)}
     */
    @Test
    void testCadastrarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioService.cadastrarUsuario(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(usuario);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("jane.doe@example.org");
        usuarioDTO.setSenha("Senha");
        usuarioDTO.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        String content = (new ObjectMapper()).writeValueAsString(usuarioDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/login/cadastrar")
                .param("loginBibliotecario", "foo")
                .param("senhaBibliotecario", "foo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nome\":\"Nome\",\"email\":\"jane.doe@example.org\",\"senha\":\"Senha\"," +
                                        "\"tipo_usuario\":\"ALUNO\"}"));
    }

    /**
     * Method under test: {@link LoginController#login(String, String)}
     */
    @Test
    void testLogin() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(1L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setTipoUsuario(TipoUsuarioEnum.ALUNO);
        when(usuarioService.login(Mockito.any(), Mockito.any())).thenReturn(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/login")
                .param("login", "foo")
                .param("senha", "foo");
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nome\":\"Nome\",\"email\":\"jane.doe@example.org\",\"senha\":\"Senha\"," +
                                        "\"tipo_usuario\":\"ALUNO\"}"));
    }
}


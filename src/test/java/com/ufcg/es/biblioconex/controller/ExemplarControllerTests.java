package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.EmprestimoDTO;
import com.ufcg.es.biblioconex.enums.StatusExemplarEnum;
import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import com.ufcg.es.biblioconex.model.Emprestimo;
import com.ufcg.es.biblioconex.model.Exemplar;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.model.Usuario;
import com.ufcg.es.biblioconex.service.ExemplarService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ExemplarController.class})
@ExtendWith(SpringExtension.class)
class ExemplarControllerTests {
    @Autowired
    private ExemplarController exemplarController;

    @MockBean
    private ExemplarService exemplarService;

    /**
     * Method under test: {@link ExemplarController#realizarEmprestimo(EmprestimoDTO)}
     */
    @Test
    void testRealizarEmprestimo() throws Exception {
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
        when(exemplarService.realizarEmprestimo(Mockito.any())).thenReturn(emprestimo);

        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
        emprestimoDTO.setDataDevolucaoPrevista(null);
        emprestimoDTO.setExemplarId(1L);
        emprestimoDTO.setUsuario(1L);
        String content = (new ObjectMapper()).writeValueAsString(emprestimoDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/exemplares/emprestimo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(exemplarController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"exemplar\":{\"id\":1,\"numero\":10,\"status\":\"DISPONIVEL\"}," +
                                        "\"usuario\":{\"id\":1,\"nome\":\"Nome\",\"email"
                                        + "\":\"jane.doe@example.org\",\"senha\":\"Senha\"," +
                                        "\"tipo_usuario\":\"ALUNO\"},\"data_emprestimo\":[1970,1,1],\"data"
                                        + "_devolucao_prevista\":[1970,1,1],\"data_devolucao\":[1970,1,1]}"));
    }

    /**
     * Method under test: {@link ExemplarController#realizarDevolucao(Long)}
     */
    @Test
    void testRealizarDevolucao() throws Exception {
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
        when(exemplarService.realizarDevolucao(Mockito.<Long>any())).thenReturn(emprestimo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/exemplares/devolucao/{id}",
                1L);
        MockMvcBuilders.standaloneSetup(exemplarController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"exemplar\":{\"id\":1,\"numero\":10,\"status\":\"DISPONIVEL\"}," +
                                        "\"usuario\":{\"id\":1,\"nome\":\"Nome\",\"email"
                                        + "\":\"jane.doe@example.org\",\"senha\":\"Senha\"," +
                                        "\"tipo_usuario\":\"ALUNO\"},\"data_emprestimo\":[1970,1,1],\"data"
                                        + "_devolucao_prevista\":[1970,1,1],\"data_devolucao\":[1970,1,1]}"));
    }

    /**
     * Method under test: {@link ExemplarController#consultarHistorico(Long)}
     */
    @Test
    void testConsultarHistorico() throws Exception {
        when(exemplarService.consultarHistorico(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/exemplares/historico" +
                        "/{usuarioId}",
                1L);
        MockMvcBuilders.standaloneSetup(exemplarController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


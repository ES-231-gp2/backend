package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Livros")
class LivroControllerTests {

    final String URI_LIVROS = "/api/livros";

    @Autowired
    MockMvc driver;
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    ObjectMapper objectMapper;
    LivroDTO livroDTO;
    Livro livro;

    Livro livro2;

    @BeforeEach
    void setup() {
        livroDTO = LivroDTO.builder()
                .isbn("978-85-8057-301-5")
                .titulo("Extraordinário")
                .autores(List.of("R. J. Palacio"))
                .editora("Intrínseca")
                .ano("2013")
                .paginas("320")
                .edicao(1)
                .descricao("August Pullman, o Auggie, nasceu com uma síndrome genética cuja sequela é uma severa deformidade facial, que lhe impôs diversas cirurgias e complicações médicas. Por isso, ele nunca havia frequentado uma escola de verdade - até agora. Todo mundo sabe que é difícil ser um aluno novo, mais ainda quando se tem um rosto tão diferente. Prestes a começar o quinto ano em um colégio particular de Nova York, Auggie tem uma missão nada fácil pela frente - convencer os colegas de que, apesar da aparência incomum, ele é um menino igual a todos os outros.")
                .build();
    }

    @AfterEach
    void tearDown() {
        livroRepository.deleteAll();
    }

    @Test
    @DisplayName("Cadastrar livro com dados válidos")
    void cadastrarLivro01() throws Exception {
        String responseJsonString = driver.perform(post(URI_LIVROS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livroDTO)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Livro resultado = objectMapper.readValue(responseJsonString, Livro.LivroBuilder.class).build();

        assertAll(
                () -> assertNotNull(resultado.getId()),
                () -> assertEquals(livroDTO.getIsbn(), resultado.getIsbn())
        );
    }


    @Nested
    @DisplayName("Testes de livro do Mês")
    class LivroDoMesTest {
        @BeforeEach
        void setup() {
            livro = livroRepository.save(Livro.builder()
                    .isbn("978-85-8057-301-5")
                    .titulo("Extraordinário")
                    .autores(List.of("R. J. Palacio"))
                    .editora("Intrínseca")
                    .ano("2013")
                    .paginas(320)
                    .edicao(1)
                    .descricao("August Pullman, o Auggie, nasceu com uma síndrome genética cuja sequela é uma severa deformidade facial, que lhe impôs diversas cirurgias e complicações médicas. Por isso, ele nunca havia frequentado uma escola de verdade - até agora. Todo mundo sabe que é difícil ser um aluno novo, mais ainda quando se tem um rosto tão diferente. Prestes a começar o quinto ano em um colégio particular de Nova York, Auggie tem uma missão nada fácil pela frente - convencer os colegas de que, apesar da aparência incomum, ele é um menino igual a todos os outros.")
                    .build());

            livro2 = livroRepository.save(Livro.builder()
                    .isbn("978-85-8057-302-2")
                    .titulo("Outro Livro")
                    .autores(List.of("Autor do Outro Livro"))
                    .editora("Editora do Outro Livro")
                    .ano("2022")
                    .paginas(250)
                    .edicao(2)
                    .descricao("Descrição do Outro Livro.")
                    .build());


            livroDTO = LivroDTO.builder()
                    .isbn(livro.getIsbn())
                    .titulo(livro.getTitulo())
                    .autores(livro.getAutores())
                    .editora(livro.getEditora())
                    .ano(livro.getAno())
                    .paginas("" + livro.getPaginas())
                    .edicao(livro.getEdicao())
                    .descricao(livro.getDescricao())
                    .build();
        }

        @AfterEach
        void tearDown() {
            livroRepository.deleteAll();
        }

        @Test
        @DisplayName("Atualizar primeiro livro do mês")
        void atualizarPrimeiroLivroDoMes() throws Exception {
            String responseJsonString = driver.perform(put(URI_LIVROS + "/livro-do-mes/" + livro.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Livro[] resultado = objectMapper.readValue(responseJsonString, Livro[].class);

            assertAll(
                    () -> assertNull(resultado[0]),
                    ()-> assertTrue(resultado[1].isLivroDoMes()),
                    () -> assertEquals(livro.getId(), resultado[1].getId())
            );
        }

        @Test
        @DisplayName("Atualizar segundo livro do mês")
        void atualizarSegundoLivroDoMes() throws Exception {
            driver.perform(put(URI_LIVROS + "/livro-do-mes/" + livro.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            String responseJsonString2 = driver.perform(put(URI_LIVROS + "/livro-do-mes/" + livro2.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Livro[] resultado2 = objectMapper.readValue(responseJsonString2, Livro[].class);

            assertAll(
                    () -> assertEquals(livro.getId(), resultado2[0].getId()),
                    () -> assertTrue(resultado2[1].isLivroDoMes()),
                    () -> assertEquals(livro2.getId(), resultado2[1].getId())
            );
        }

        @Test
        @DisplayName("Ver livro do mês")
        void verLivroDoMes() throws Exception {
            driver.perform(put(URI_LIVROS + "/livro-do-mes/" + livro.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            String responseJsonString = driver.perform(get(URI_LIVROS + "/livro-do-mes")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Livro livro = objectMapper.readValue(responseJsonString, Livro.class);

            assertAll(
                    () -> assertTrue(livro.isLivroDoMes()),
                    () -> assertEquals(livro.getId(), livro.getId())
            );
        }

        @Test
        @DisplayName("Ver livro do mês sem livro do mês")
        void verLivroDoMesNulo() throws Exception {
            String responseJsonString = driver.perform(get(URI_LIVROS + "/livro-do-mes")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            assertTrue(responseJsonString == null || responseJsonString.isEmpty());
        }

    }
}

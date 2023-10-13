package com.ufcg.es.biblioconex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    void setup() {
        livroDTO = LivroDTO.builder()
                .isbn("978-85-8057-301-5")
                .titulo("Extraordinário")
                .autores(Set.of("R. J. Palacio"))
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
}

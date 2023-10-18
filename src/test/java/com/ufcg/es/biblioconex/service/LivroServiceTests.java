package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Testes do service de Livros")
class LivroServiceTests {

    @Autowired
    LivroService livroService;
    @Autowired
    LivroRepository livroRepository;
    LivroDTO livroDTO;

    @BeforeEach
    void setup() {
        livroDTO = LivroDTO.builder()
                .isbn("85-325-2066-9")
                .titulo("O conto da aia")
                .autores(Set.of("Margaret Atwood"))
                .editora("Editora Rocco")
                .ano("2017")
                .paginas("368")
                .descricao("O romance distópico O conto da aia, de Margaret Atwood, se passa num futuro muito próximo e tem como cenário uma república onde não existem mais jornais, revistas, livros nem filmes. As universidades foram extintas. Também já não há advogados, porque ninguém tem direito a defesa. Os cidadãos considerados criminosos são fuzilados e pendurados mortos no Muro, em praça pública, para servir de exemplo enquanto seus corpos apodrecem à vista de todos. Para merecer esse destino, não é preciso fazer muita coisa – basta, por exemplo, cantar qualquer canção que contenha palavras proibidas pelo regime, como \"liberdade\". Nesse Estado teocrático e totalitário, as mulheres são as vítimas preferenciais, anuladas por uma opressão sem precedentes. O nome dessa república é Gilead, mas já foi Estados Unidos da América. Uma das obras mais importantes da premiada escritora canadense, conhecida por seu ativismo político, ambiental e em prol das causas femininas, O conto da aia foi escrito em 1985 e inspirou a série homônima (The Handmaid's Tale, no original), produzida pelo canal de streaming Hulu em 2017. As mulheres de Gilead não têm direitos. Elas são divididas em categorias, cada qual com uma função muito específica no Estado. A Offred coube a categoria de aia, o que significa pertencer ao governo e existir unicamente para procriar, depois que uma catástrofe nuclear tornou estéril um grande número de pessoas. E sem dúvida, ainda que vigiada dia e noite e ceifada em seus direitos mais básicos, o destino de uma aia ainda é melhor que o das não-mulheres, como são chamadas aquelas que não podem ter filhos, as homossexuais, viúvas e feministas, condenadas a trabalhos forçados nas colônias, lugares onde o nível de radiação é mortífero.")
                .capa("http://books.google.com/books/content?id=TJk4DwAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api")
                .build();
    }

    @AfterEach
    void tearDown() {
        livroRepository.deleteAll();
    }

    @Test
    @DisplayName("Cadastrar um primeiro livro")
    void cadastrarLivro01() {
        Livro livro = livroService.cadastrarLivro(livroDTO, 1);

        assertAll(
                () -> assertEquals(1, livroRepository.count()),
                () -> assertNotNull(livro.getId()),
                () -> assertEquals(livroDTO.getIsbn(), livro.getIsbn())
        );
    }

    @Test
    @DisplayName("Cadastrar um 2º livro ou posterior")
    void cadastrarLivro02() {
        LivroDTO segundoLivroDTO = LivroDTO.builder()
                .isbn("978-85-8057-301-5")
                .titulo("Extraordinário")
                .autores(Set.of("R. J. Palacio"))
                .editora("Intrínseca")
                .ano("2013")
                .paginas("320")
                .build();

        Livro primeiroLivro = livroService.cadastrarLivro(livroDTO, 1);
        Livro segundoLivro = livroService.cadastrarLivro(segundoLivroDTO, 2);

        assertAll(
                () -> assertEquals(2, livroRepository.count()),
                () -> assertNotNull(primeiroLivro.getId()),
                () -> assertNotNull(segundoLivro.getId()),
                () -> assertEquals(livroDTO.getIsbn(), primeiroLivro.getIsbn()),
                () -> assertEquals(segundoLivroDTO.getIsbn(), segundoLivro.getIsbn())
        );
    }

    @Test
    @DisplayName("Buscar livro por ISBN via Google Books")
    void buscarLivroPorIsbn01() {
        LivroDTO livroApi = livroService.buscarLivroPorIsbn(livroDTO.getIsbn());

        assertAll(
                () -> assertEquals(livroDTO, livroApi)
        );
    }

    @Test
    @DisplayName("Adicionar livro como primeiro livro do mês")
    void adicionaLivroDoMes() {
        Livro livro = livroService.cadastrarLivro(livroDTO, 2);

        Livro[] livros = livroService.atualizarLivroDoMes(livro.getId());

        assertAll(
                () -> assertEquals(livro.getId(), livros[1].getId()),
                () -> assertNull(livros[0])
        );
    }

    @Test
    @DisplayName("Adicionar livro como segundo livro do mês")
    void adicionaSegundoLivroDoMes() {
        LivroDTO segundoLivroDTO = LivroDTO.builder()
                .isbn("978-85-8057-301-5")
                .titulo("Extraordinário")
                .autores(Set.of("R. J. Palacio"))
                .editora("Intrínseca")
                .ano("2013")
                .paginas("320")
                .build();

        Livro primeiroLivro = livroService.cadastrarLivro(livroDTO, 2);
        Livro segundoLivro = livroService.cadastrarLivro(segundoLivroDTO, 1);

        livroService.atualizarLivroDoMes(primeiroLivro.getId());
        Livro[] livros = livroService.atualizarLivroDoMes(segundoLivro.getId());

        assertAll(
                () -> assertEquals(primeiroLivro.getId(), livros[0].getId()),
                () -> assertEquals(segundoLivro.getId(), livros[1].getId())
        );
    }

    @Test
    @DisplayName("Vê o livro do mês")
    void verLivroDoMes() {
        Livro livro = livroService.cadastrarLivro(livroDTO, 2);

        livroService.atualizarLivroDoMes(livro.getId());

        Livro livroDoMes = livroService.verLivroDoMes();

        assertAll(
                () -> assertEquals(livroDoMes.getId(), livro.getId()),
                () -> assertTrue(livroDoMes.isLivroDoMes())
        );
    }

    @Test
    @DisplayName("Ver livro do mês sem ter um livro do mês")
    void verLivroDoMesNulo() {
        Livro livroDoMes = livroService.verLivroDoMes();

        assertAll(
                () -> assertNull(livroDoMes)
        );
    }
}

package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    ModelMapper modelMapper;
    @Value("${google.api.url}")
    private String URL;
    @Value("${google.api.key}")
    private String KEY;

    @Override
    public Livro cadastrarLivro(LivroDTO livroDTO) {
        Livro livro = modelMapper.map(livroDTO, Livro.class);
        livro.setIsbn(formataIsbn(livroDTO.getIsbn()));
        return livroRepository.save(livro);
    }

    @Override
    public LivroDTO buscarLivroPorIsbn(String isbn) {
        isbn = formataIsbn(isbn);
        String params = "?q=ISBN:" + isbn + "&orderBy=relevance&maxResults=1";
        String url = URL + params + "&key=" + KEY;

        return LivroDTO.fromJson(isbn, getHttpResponse(url));
    }

    @Override
    public List<Livro> buscarLivros(Long id) {
        if (id != null && id > 0) {
            Livro livro = livroRepository.findById(id).orElseThrow(NoSuchElementException::new);
            return List.of(livro);
        }

        return livroRepository.findAll();
    }

    @Override
    public Livro atualizarLivro(Long id, LivroDTO livroDTO) {
        Livro livro = livroRepository.findById(id).orElseThrow(NoSuchElementException::new);
        modelMapper.map(livroDTO, livro);
        return livroRepository.save(livro);
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.deleteById(id);
    }

    private String formataIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return "";
        }

        String cleanIsbn = isbn.replaceAll("[^0-9]", "");

        if (cleanIsbn.length() == 10) {
            isbn = cleanIsbn.substring(0, 2) + "-" +
                    cleanIsbn.substring(2, 5) + "-" +
                    cleanIsbn.substring(5, 9) + "-" +
                    cleanIsbn.charAt(9);
        } else if (cleanIsbn.length() == 13) {
            isbn = cleanIsbn.substring(0, 3) + "-" +
                    cleanIsbn.substring(3, 5) + "-" +
                    cleanIsbn.substring(5, 9) + "-" +
                    cleanIsbn.substring(9, 12) + "-" +
                    cleanIsbn.charAt(12);
        }

        return isbn;
    }

    private String getHttpResponse(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}

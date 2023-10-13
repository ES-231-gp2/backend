package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.LivroDTO;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Exemplar;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.ufcg.es.biblioconex.utils.Formatador.formatarIsbn;
import static com.ufcg.es.biblioconex.utils.HttpClient.getHttpResponse;

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
    public Livro cadastrarLivro(LivroDTO livroDTO, Integer numeroExemplares) {
        livroDTO.setIsbn(formatarIsbn(livroDTO.getIsbn()));
        Livro livro = modelMapper.map(livroDTO, Livro.class);
        livroRepository.save(livro);

        Set<Exemplar> exemplares = livro.getExemplares();
        for (int i = 1; i < numeroExemplares + 1; i++) {
            exemplares.add(Exemplar.builder()
                    .livro(livro)
                    .numero(i)
                    .build());
        }
        livro.setExemplares(exemplares);

        return livroRepository.save(livro);
    }

    @Override
    public LivroDTO buscarLivroPorIsbn(String isbn) {
        isbn = formatarIsbn(isbn);
        String params = "?q=ISBN:" + isbn + "&orderBy=relevance&maxResults=1";
        String url = URL + params + "&key=" + KEY;

        return LivroDTO.fromJson(isbn, getHttpResponse(url));
    }

    @Override
    public List<Livro> buscarLivros(Long id) {
        if (id != null && id > 0) {
            Livro livro = livroRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);
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

    @Override
    public Livro adicionarExemplares(Long id, Integer numeroExemplares) {
        Livro livro = livroRepository.findById(id).orElseThrow(ObjetoNaoExisteException::new);
        Set<Exemplar> exemplares = livro.getExemplares();

        int ultimoExemplar = exemplares.size();
        for (int i = ultimoExemplar + 1; i < ultimoExemplar + numeroExemplares + 1; i++) {
            exemplares.add(Exemplar.builder()
                    .livro(livro)
                    .numero(i)
                    .build());
        }

        livro.setExemplares(exemplares);
        return livroRepository.save(livro);
    }
}

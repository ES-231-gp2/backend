package com.ufcg.es.biblioconex.service;

import com.ufcg.es.biblioconex.dto.AlunoResenhasDTO;
import com.ufcg.es.biblioconex.dto.ResenhaDTO;
import com.ufcg.es.biblioconex.exception.ObjetoNaoExisteException;
import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Livro;
import com.ufcg.es.biblioconex.model.Resenha;
import com.ufcg.es.biblioconex.repository.LivroRepository;
import com.ufcg.es.biblioconex.repository.ResenhaRepository;
import com.ufcg.es.biblioconex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResenhaServiceImpl implements ResenhaService {

    @Autowired
    ResenhaRepository resenhaRepository;
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Resenha cadastrarResenha(ResenhaDTO resenhaDTO) {
        Livro livro = livroRepository.findById(resenhaDTO.getLivroId()).orElseThrow(ObjetoNaoExisteException::new);
        Aluno aluno = (Aluno) usuarioRepository.findById(resenhaDTO.getAlunoId())
                .orElseThrow(ObjetoNaoExisteException::new);

        Resenha resenha = Resenha.builder()
                .livro(livro)
                .aluno(aluno)
                .conteudo(resenhaDTO.getConteudo())
                .build();

        return resenhaRepository.save(resenha);
    }

    @Override
    public List<Resenha> buscarResenhasPorLivro(Long livroId) {
        livroRepository.findById(livroId).orElseThrow(ObjetoNaoExisteException::new);
        return resenhaRepository.findByLivroId(livroId);
    }

    @Override
    public List<Resenha> buscarResenhasPorAluno(Long alunoId) {
        usuarioRepository.findById(alunoId).orElseThrow(ObjetoNaoExisteException::new);
        return resenhaRepository.findByAlunoId(alunoId);
    }

    @Override
    public List<AlunoResenhasDTO> buscarAlunosMaisResenhas() {
        List<Object[]> results = resenhaRepository.findTop10AlunosMaisResenhas();
        List<AlunoResenhasDTO> top10Alunos = new ArrayList<>();

        for (Object[] result : results) {
            Aluno aluno = (Aluno) result[0];
            Long totalResenhas = (Long) result[1];
            top10Alunos.add(new AlunoResenhasDTO(aluno, totalResenhas));
        }

        return top10Alunos;
    }
}

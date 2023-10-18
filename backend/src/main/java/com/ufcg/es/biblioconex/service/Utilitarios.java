package com.ufcg.es.biblioconex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.es.biblioconex.model.Aluno;
import com.ufcg.es.biblioconex.model.Resenha;
import com.ufcg.es.biblioconex.repository.AlunoRepository;

@Service
public class Utilitarios {

    @Autowired
    AlunoRepository alunoRepository;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final String CARACTERES_IDENTIFICADOR = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static boolean validaEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public String identificadorResenha(){

        String identificador = "";

        for (int i = 0;i < 8;i++){

            int randomInt = (int)(CARACTERES_IDENTIFICADOR.length() * Math.random());
            identificador += CARACTERES_IDENTIFICADOR.charAt(randomInt);

        }

        return identificador;
    }

    public Set<Aluno> bibliofilos(){

        List<Aluno> alunos = alunoRepository.findAll();
        HashMap<Aluno,Integer> alunosBibliofilos = new HashMap<>();
        int maxLivros = 0;

        for (Aluno a:alunos){

            HashMap<String,Resenha> resenhas = a.getResenhas();
            int resenhasAprovadas = 0;

            if (!a.getResenhas().isEmpty()){
                for (Resenha r:resenhas.values()){
                    if (Boolean.TRUE.equals(r.getResenhaAprovada())){
                        resenhasAprovadas++;
                    }
                }
            }

            if (resenhasAprovadas > maxLivros){
                alunosBibliofilos.clear();
                maxLivros = resenhasAprovadas;
                alunosBibliofilos.put(a,resenhasAprovadas);
            } else if (resenhasAprovadas == maxLivros){
                alunosBibliofilos.put(a,resenhasAprovadas);
            }

        }

        return alunosBibliofilos.keySet();

    }
    
}

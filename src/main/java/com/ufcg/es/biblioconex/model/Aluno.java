package com.ufcg.es.biblioconex.model;

import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Aluno extends Usuario {

    public Aluno(String nome, String email, String senha) {
        super(nome, email, senha, TipoUsuarioEnum.ALUNO);
    }
}

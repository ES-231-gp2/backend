package com.ufcg.es.biblioconex.model;

import com.ufcg.es.biblioconex.enums.TipoUsuarioEnum;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Professor extends Usuario {

    public Professor(String nome, String email, String senha) {
        super(nome, email, senha, TipoUsuarioEnum.PROFESSOR);
    }
}

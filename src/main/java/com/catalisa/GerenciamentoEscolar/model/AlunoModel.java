package com.catalisa.GerenciamentoEscolar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "alunos")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeAluno;
    @Column(nullable = false)
    private int idade;
    @Column(nullable = false)
    private String email;


    public AlunoModel(String nomeAluno, int idade, String email) {
        this.nomeAluno = nomeAluno;
        this.idade = idade;
        this.email = email;
    }

}

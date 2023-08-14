package com.catalisa.GerenciamentoEscolar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "alunos")
@Entity
@Data
@NoArgsConstructor
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
}

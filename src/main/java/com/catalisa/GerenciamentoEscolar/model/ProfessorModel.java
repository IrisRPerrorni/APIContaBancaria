package com.catalisa.GerenciamentoEscolar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    @ManyToOne
    @JoinColumn(name = "curso_id" )
    private CursoModel cursos;
    private double salario;

    public ProfessorModel(String nome, int idade, CursoModel cursos, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.cursos = cursos;
        this.salario = salario;
    }
}

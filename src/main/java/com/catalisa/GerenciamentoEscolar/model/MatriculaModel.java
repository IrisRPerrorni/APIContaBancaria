package com.catalisa.GerenciamentoEscolar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataMatricula;
    @OneToOne
    @JoinColumn(name = "aluno_id")
    private AlunoModel aluno;
    @OneToOne
    @JoinColumn(name = "curso_id")
    private CursoModel curso;


}

package com.catalisa.GerenciamentoEscolar.dto;

import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private String nome;
    private int idade;
    private double salario;
    private Long  cursos;
}

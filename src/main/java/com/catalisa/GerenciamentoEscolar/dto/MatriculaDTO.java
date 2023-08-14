package com.catalisa.GerenciamentoEscolar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTO {

    private Long aluno;
    private Long curso;
    private String dataMatricula;


}

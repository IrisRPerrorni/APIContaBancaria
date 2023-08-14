package com.catalisa.GerenciamentoEscolar.dto;

import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {

    private String nomeAluno;
    private int idade;
    private String email;


}

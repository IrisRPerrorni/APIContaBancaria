package com.catalisa.GerenciamentoEscolar.dto;

import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    private String nomeCurso;
    private int cargaHoraria;



}

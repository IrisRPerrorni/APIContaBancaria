package com.catalisa.GerenciamentoEscolar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
public class CursoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeCurso;
    @Column(nullable = false)
    private int cargaHoraria;

}





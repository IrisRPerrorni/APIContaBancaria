package com.catalisa.GerenciamentoEscolar.repository;

import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<MatriculaModel,Long> {

    void deleteByAluno(AlunoModel aluno);

}

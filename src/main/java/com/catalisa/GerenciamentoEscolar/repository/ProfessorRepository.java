package com.catalisa.GerenciamentoEscolar.repository;

import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorModel,Long> {

}

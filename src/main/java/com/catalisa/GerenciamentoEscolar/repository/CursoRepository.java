package com.catalisa.GerenciamentoEscolar.repository;

import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel,Long> {
}

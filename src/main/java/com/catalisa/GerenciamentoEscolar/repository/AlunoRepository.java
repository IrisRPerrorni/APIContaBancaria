package com.catalisa.GerenciamentoEscolar.repository;

import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel,Long> {

}

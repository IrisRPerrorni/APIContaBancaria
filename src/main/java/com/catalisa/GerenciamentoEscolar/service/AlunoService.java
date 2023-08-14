package com.catalisa.GerenciamentoEscolar.service;

import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.repository.AlunoRepository;
import com.catalisa.GerenciamentoEscolar.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;





    //1. Cadastro de alunos.

    public AlunoModel cadastrarAluno(AlunoModel alunoModel){

        return alunoRepository.save(alunoModel);

    }

    //4. Listar todos os alunos.

    public List<AlunoModel> listarAlunos(){
        return  alunoRepository.findAll();
    }

    //9. Deletar aluno.

    public void deletar(AlunoModel alunoModel){
        alunoRepository.delete(alunoModel);
    }

    public Optional<AlunoModel> exibirEspecifico(Long id) {
        return alunoRepository.findById(id);

    }










}

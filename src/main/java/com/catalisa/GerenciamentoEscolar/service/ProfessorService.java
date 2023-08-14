package com.catalisa.GerenciamentoEscolar.service;

import com.catalisa.GerenciamentoEscolar.dto.ProfessorDTO;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import com.catalisa.GerenciamentoEscolar.repository.CursoRepository;
import com.catalisa.GerenciamentoEscolar.repository.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CursoRepository cursoRepository;

    public ProfessorModel cadastrarProfessor(ProfessorDTO professorDTO){
        ProfessorModel professor = new ProfessorModel();
        professor.setNome(professorDTO.getNome());
        professor.setIdade(professorDTO.getIdade());
        professor.setSalario(professorDTO.getSalario());


        if (professorDTO.getCursos()!=null) {
            Optional<CursoModel> curso = cursoRepository.findById(professorDTO.getCursos());
            professor.setCursos(curso.get());
        }
        return professorRepository.save(professor);

    }

    public List<ProfessorModel>listarProfessor(){
        return professorRepository.findAll();
    }

    public void deletarProfessor(ProfessorModel professorModel){
        professorRepository.delete(professorModel);
    }

    public Optional<ProfessorModel> exibirEspecifico(Long id) {
        return professorRepository.findById(id);

    }



}

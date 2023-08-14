package com.catalisa.GerenciamentoEscolar.service;

import com.catalisa.GerenciamentoEscolar.dto.CursoDTO;
import com.catalisa.GerenciamentoEscolar.dto.MatriculaDTO;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.model.MatriculaModel;
import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import com.catalisa.GerenciamentoEscolar.repository.AlunoRepository;
import com.catalisa.GerenciamentoEscolar.repository.CursoRepository;
import com.catalisa.GerenciamentoEscolar.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoRepository cursoRepository;


    public ResponseEntity<Object> cadastrarMatricula(MatriculaDTO matriculaDTO) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findById(matriculaDTO.getAluno());
        Optional<CursoModel> cursoOptional = cursoRepository.findById(matriculaDTO.getCurso());

        if (alunoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aluno não encontrado para o ID informado.");
        }
        if (cursoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado para o ID informado.");
        }

        MatriculaModel matricula = new MatriculaModel();
        matricula.setDataMatricula(matriculaDTO.getDataMatricula());
        matricula.setCurso(cursoOptional.get());
        matricula.setAluno(alunoOptional.get());

        MatriculaModel matriculaSalva = matriculaRepository.save(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body("Matrícula criada com sucesso.");
    }



    public List<MatriculaModel> listarMatricual() {
        return matriculaRepository.findAll();
    }

    public void deletarMatricula(MatriculaModel matriculaModel) {
        matriculaRepository.delete(matriculaModel);
    }

    public Optional<MatriculaModel> exibirEspecifico(Long id) {
        return matriculaRepository.findById(id);

    }

    @Transactional
    public void atualizarCurso(Long id, MatriculaDTO matriculaDTO) {
        MatriculaModel matricula = matriculaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Matricula não encontrada"));

        AlunoModel aluno = alunoRepository.findById(matriculaDTO.getAluno()).orElseThrow(() ->
                new EntityNotFoundException("Aluno não encontrado"));

        CursoModel curso = cursoRepository.findById(matriculaDTO.getCurso()).orElseThrow(() ->
                new EntityNotFoundException("Curso não encontrado"));

        if (!matricula.getAluno().getId().equals(aluno.getId())) {
            throw new IllegalArgumentException("O aluno fornecido não corresponde ao aluno associado à matrícula.");

        }
        matricula.setCurso(curso);
        matricula.setAluno(aluno);
        matricula.setDataMatricula(matriculaDTO.getDataMatricula());
        matriculaRepository.save(matricula);

    }
    @Transactional
    public void deletarMatriculaPorAluno(AlunoModel alunoModel) {

        matriculaRepository.deleteByAluno(alunoModel);

    }




}



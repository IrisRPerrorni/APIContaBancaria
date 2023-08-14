package com.catalisa.GerenciamentoEscolar.service;

import com.catalisa.GerenciamentoEscolar.dto.MatriculaDTO;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.model.MatriculaModel;
import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import com.catalisa.GerenciamentoEscolar.repository.AlunoRepository;
import com.catalisa.GerenciamentoEscolar.repository.CursoRepository;
import com.catalisa.GerenciamentoEscolar.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public MatriculaModel cadastrarMatricula(MatriculaDTO matriculaDTO) {
        MatriculaModel matricula = new MatriculaModel();
        matricula.setDataMatricula(matriculaDTO.getDataMatricula());

        if (matriculaDTO.getAluno() != null) {
            Optional<AlunoModel> alunoid = alunoRepository.findById(matriculaDTO.getAluno());
            alunoid.ifPresent(matricula::setAluno);
        }

        if (matriculaDTO.getCurso() != null) {
            Optional<CursoModel> cursoid = cursoRepository.findById(matriculaDTO.getCurso());
            cursoid.ifPresent(matricula::setCurso);
        }

        return matriculaRepository.save(matricula);
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



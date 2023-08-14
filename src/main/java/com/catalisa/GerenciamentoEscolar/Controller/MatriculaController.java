package com.catalisa.GerenciamentoEscolar.Controller;

import com.catalisa.GerenciamentoEscolar.dto.MatriculaDTO;
import com.catalisa.GerenciamentoEscolar.model.MatriculaModel;
import com.catalisa.GerenciamentoEscolar.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @PostMapping(path = "/escola/matricula")
    public ResponseEntity<MatriculaModel> cadastroNovaMatricula(@RequestBody MatriculaDTO matriculaDTO) {
        MatriculaModel matriculaModel = matriculaService.cadastrarMatricula(matriculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaModel);

    }

    @GetMapping(path = "/escola/matricula")
    public ResponseEntity<List<MatriculaModel>> listarTodaMatricula() {
        List<MatriculaModel> matriculaModels = matriculaService.listarMatricual();
        return ResponseEntity.ok(matriculaModels);
    }

    @PutMapping(path = "/escola/matricula/{id}")
    public ResponseEntity<String>atualizarCursoAluno(@PathVariable Long id ,
                                                             @RequestBody MatriculaDTO matriculaDTO){
        matriculaService.atualizarCurso(id,matriculaDTO);
        return ResponseEntity.ok("Curso do aluno na matrícula atualizado com sucesso.");

    }
}






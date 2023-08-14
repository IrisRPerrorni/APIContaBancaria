package com.catalisa.GerenciamentoEscolar.Controller;

import com.catalisa.GerenciamentoEscolar.dto.ProfessorDTO;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import com.catalisa.GerenciamentoEscolar.service.ProfessorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProfessorController {
    @Autowired
    ProfessorService professorService;

    //post
    @PostMapping(path ="/escola/professor")
    public ResponseEntity<ProfessorModel> cadastrarProfessorNovo(@RequestBody ProfessorDTO professorDTO) {
        ProfessorModel professor = professorService.cadastrarProfessor(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @GetMapping(path ="/escola/professor")
    public ResponseEntity<List<ProfessorModel>> listarTodosProfessores() {
        List<ProfessorModel> professores = professorService.listarProfessor();
        return ResponseEntity.ok(professores);

    }

    @DeleteMapping(path = "/escola/professor/{id}")
    public ResponseEntity<Object>deletarConta(@PathVariable (value = "id") Long id){
        Optional<ProfessorModel> professorOptiona = professorService.exibirEspecifico(id);
        if (professorOptiona.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
        }
        professorService.deletarProfessor(professorOptiona.get());
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso");
    }
}

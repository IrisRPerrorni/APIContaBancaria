package com.catalisa.GerenciamentoEscolar.Controller;

import com.catalisa.GerenciamentoEscolar.dto.CursoDTO;

import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.service.CursoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;


    @PostMapping(path = "/escola/curso")
    public ResponseEntity<Object> cadastrarCursoNovo(@RequestBody CursoDTO cursoDTO) {
        CursoModel cursoModel = new CursoModel();
        BeanUtils.copyProperties(cursoDTO, cursoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.cadastrarCurso(cursoModel));

    }



    @GetMapping(path = "/escola/curso")
    public ResponseEntity<List<CursoModel>> exibirListaDeCursos() {
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.listarCursos());
    }

    @DeleteMapping(path = "/escola/curso/{id}")
    public ResponseEntity<Object>deletarCurso(@PathVariable (value = "id") Long id){
        Optional<CursoModel> cursoModelOptional = cursoService.exibirEspecifico(id);
        if (cursoModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }
        cursoService.deletarCurso(cursoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso");
    }
}

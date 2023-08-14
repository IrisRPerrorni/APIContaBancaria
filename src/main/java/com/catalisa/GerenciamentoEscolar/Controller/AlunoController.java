package com.catalisa.GerenciamentoEscolar.Controller;

import com.catalisa.GerenciamentoEscolar.dto.AlunoDTO;
import com.catalisa.GerenciamentoEscolar.dto.CursoDTO;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.service.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class AlunoController {

    @Autowired
    AlunoService alunoService;
    @PostMapping(path = "/escola/aluno")
    public ResponseEntity<AlunoModel>cadastrarAlunoNovo(@RequestBody AlunoDTO alunoDTO){
        AlunoModel alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDTO,alunoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.cadastrarAluno(alunoModel));

    }


    @GetMapping(path = "/escola/aluno")
    public ResponseEntity<List<AlunoModel>>exibirListaDeAluno(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.listarAlunos());
    }

    @DeleteMapping(path = "/escola/aluno/{id}")
    public ResponseEntity<Object>deletarConta(@PathVariable (value = "id") Long id){
        Optional<AlunoModel>alunoModelOptional = alunoService.exibirEspecifico(id);
        if (alunoModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
        alunoService.deletar(alunoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso");
    }



}

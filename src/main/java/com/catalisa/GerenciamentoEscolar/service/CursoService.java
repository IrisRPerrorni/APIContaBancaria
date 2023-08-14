package com.catalisa.GerenciamentoEscolar.service;


import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    //2.Cadastro de curso.
    public CursoModel cadastrarCurso(CursoModel cursoModel){
        return cursoRepository.save(cursoModel);

    }
    public Optional<CursoModel> exibirEspecifico(Long id) {
        return cursoRepository.findById(id);

    }


    //  6. Listar todos os cursos.
    public List<CursoModel> listarCursos(){
        return cursoRepository.findAll();
    }

    //Deletar curso

    public void deletarCurso(CursoModel cursoModel){
        cursoRepository.delete(cursoModel);
    }
}

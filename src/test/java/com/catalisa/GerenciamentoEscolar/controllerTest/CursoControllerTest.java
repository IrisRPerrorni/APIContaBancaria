package com.catalisa.GerenciamentoEscolar.controllerTest;

import com.catalisa.GerenciamentoEscolar.Controller.CursoController;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CursoService cursoService;



    @Test
    @DisplayName("Buscar todos alunos usando mockMVC")
    public void exibirListaDeCurso() throws Exception {


        CursoModel curso1 = new CursoModel("Java para iniciante", 360);
        CursoModel curso2 = new CursoModel("SQL", 360);
        Mockito.when(cursoService.listarCursos()).thenReturn(List.of(curso1, curso2));

        //Configurando o MockMVC

        mockMvc.perform(get("/escola/curso")).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].nomeCurso").value("Java para iniciante"))
                .andExpect(jsonPath("$[0].cargaHoraria").value(360))
                .andExpect(jsonPath("$[1].nomeCurso").value("SQL"))
                .andExpect(jsonPath("$[1].cargaHoraria").value(360));



        Mockito.verify(cursoService, times(1)).listarCursos();


    }

    @Test
    public void cadastrarCursosTest() throws  Exception{

        CursoModel cursoCadastro = new CursoModel("Lógica de programação",360);
        Mockito.when(cursoService.cadastrarCurso(cursoCadastro)).thenReturn(cursoCadastro);


        mockMvc.perform(post("/escola/curso").contentType("application/json")
                .content(objectMapper.writeValueAsString(cursoCadastro))).andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(cursoCadastro)));
    }

    @Test
    public void deletarTeste() throws Exception{
        CursoModel cursoExistente = new CursoModel(1L,"Java",360);
        given(cursoService.exibirEspecifico(1L)).willReturn(Optional.of(cursoExistente));

        mockMvc.perform(delete("/escola/curso/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Curso deletado com sucesso"));

    }



}


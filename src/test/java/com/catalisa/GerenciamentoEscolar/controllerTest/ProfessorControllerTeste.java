package com.catalisa.GerenciamentoEscolar.controllerTest;

import com.catalisa.GerenciamentoEscolar.Controller.AlunoController;
import com.catalisa.GerenciamentoEscolar.Controller.ProfessorController;
import com.catalisa.GerenciamentoEscolar.dto.ProfessorDTO;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import com.catalisa.GerenciamentoEscolar.service.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProfessorController.class)
public class ProfessorControllerTeste {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfessorService professorService;

    @Test
    @DisplayName("Buscar todos professores usando mockMVC")
    public void exibirListaDeProfessor() throws Exception {


        CursoModel curso1 = new CursoModel(1L, "Java", 360);
        CursoModel curso2 = new CursoModel(2L, "Postegrels", 360);
        ProfessorModel prof1 = new ProfessorModel( "Antonio", 30, curso1, 3000);
        ProfessorModel prof2 = new ProfessorModel( "Joana", 35, curso2, 3000);
        Mockito.when(professorService.listarProfessor()).thenReturn(List.of(prof1, prof2));

        //Configurando o MockMVC

        mockMvc.perform(get("/escola/professor")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Antonio"))
                .andExpect(jsonPath("$[0].idade").value(30))
                .andExpect(jsonPath("$[0].cursos").value(curso1))
                .andExpect(jsonPath("$[1].nome").value("Joana"))
                .andExpect(jsonPath("$[1].idade").value(35))
                .andExpect(jsonPath("$[1].cursos").value(curso2));

        //Verifique o metodo listarAlunos do service foi chamado uma vez
        Mockito.verify(professorService, times(1)).listarProfessor();

    }

    @Test
    public void cadastrarProfessorTest() throws Exception {

        ProfessorDTO professorCadastro = new ProfessorDTO("Vitor", 35, 3000, 1L);
        CursoModel curso1 = new CursoModel(1L, "Java", 360);
        ProfessorModel professor = new ProfessorModel();
        professor.setNome("Vitor");
        professor.setIdade(35);
        professor.setSalario(3000);
        professor.setCursos(curso1);

        Mockito.when(professorService.cadastrarProfessor(professorCadastro)).thenReturn(professor);
        mockMvc.perform(MockMvcRequestBuilders.post("/escola/professor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(professorCadastro)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Vitor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(35))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salario").value(3000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cursos.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cursos.nomeCurso").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cursos.cargaHoraria").value(360));


    }

    @Test
    public void testDeletarProfessor() throws Exception {

        CursoModel curso1 = new CursoModel(1L, "Java", 360);
        ProfessorModel professorExistente = new ProfessorModel(1L, "Antonio", 30, curso1, 3000);

        given(professorService.exibirEspecifico(1L)).willReturn(Optional.of(professorExistente));

        mockMvc.perform(delete("/escola/professor/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Professor deletado com sucesso"));
    }


}

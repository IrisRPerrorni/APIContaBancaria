package com.catalisa.GerenciamentoEscolar.controllerTest;

import com.catalisa.GerenciamentoEscolar.Controller.AlunoController;
import com.catalisa.GerenciamentoEscolar.Controller.MatriculaController;
import com.catalisa.GerenciamentoEscolar.dto.MatriculaDTO;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.model.CursoModel;
import com.catalisa.GerenciamentoEscolar.model.MatriculaModel;
import com.catalisa.GerenciamentoEscolar.model.ProfessorModel;
import com.catalisa.GerenciamentoEscolar.service.MatriculaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = MatriculaController.class)
public class MatriculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MatriculaService matriculaService;

    @Test
    public void cadastrarMatriuculaTestALunoEcursoExistente() throws Exception {
        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, 1L, "25/04/2023");
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).body("Matrícula criada com sucesso.");
        when(matriculaService.cadastrarMatricula(any())).thenReturn(response);

        mockMvc.perform(post("/escola/matricula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matriculaDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Matrícula criada com sucesso."));

    }

    @Test
    public void cadastrarMatriculaTestAlunoNãoEncontrado() throws Exception {
        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, 1L, "25/04/2023");
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Aluno não encontrado para o ID informado.");
        when(matriculaService.cadastrarMatricula(any())).thenReturn(response);
        mockMvc.perform(post("/escola/matricula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matriculaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Aluno não encontrado para o ID informado."));
    }

    @Test
    public void cadastrarMatriculaTestCursoNãoEncontrado() throws Exception {
        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, 1L, "25/04/2023");
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Curso não encontrado para o ID informado.");
        when(matriculaService.cadastrarMatricula(any())).thenReturn(response);
        mockMvc.perform(post("/escola/matricula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matriculaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Curso não encontrado para o ID informado."));
    }

    @Test
    public void exibirListadeMatricula() throws Exception {

        CursoModel curso1 = new CursoModel(1L, "Java", 360);
        CursoModel curso2 = new CursoModel(2L, "Postegrels", 360);

        AlunoModel aluno1 = new AlunoModel(1L, "Iris", 30, "iris@gmail.com");
        AlunoModel aluno2 = new AlunoModel(2L, "Vitor", 25, "vitor@gmail.com");

        MatriculaModel matricula1 = new MatriculaModel(1L,"25/06/2023", aluno1, curso1);
        MatriculaModel matricula2 = new MatriculaModel(2L,"27/06/2023", aluno2, curso2);
        Mockito.when(matriculaService.listarMatricual()).thenReturn(List.of(matricula1, matricula2));


        mockMvc.perform(get("/escola/matricula")).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].id").value(1L)).
                andExpect(jsonPath("$[0].dataMatricula").value("25/06/2023"))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].aluno.nomeAluno").value("Iris"))
                .andExpect(jsonPath("$[0].aluno.idade").value(30))
                .andExpect(jsonPath("$[0].aluno.email").value("iris@gmail.com"))
                .andExpect(jsonPath("$[0].curso.nomeCurso").value("Java"))
                .andExpect(jsonPath("$[0].curso.cargaHoraria").value(360))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].dataMatricula").value("27/06/2023"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].aluno.nomeAluno").value("Vitor"))
                .andExpect(jsonPath("$[1].aluno.idade").value(25))
                .andExpect(jsonPath("$[1].aluno.email").value("vitor@gmail.com"))
                .andExpect(jsonPath("$[1].curso.nomeCurso").value("Postegrels"))
                .andExpect(jsonPath("$[1].curso.cargaHoraria").value(360));


        Mockito.verify(matriculaService, times(1)).listarMatricual();
    }

    @Test
    public void testDeletarMatricula() throws Exception {

        CursoModel curso1 = new CursoModel(1L,"Java", 360);
        AlunoModel aluno1 = new AlunoModel(1L, "Iris", 30, "iris@gmail.com");
        MatriculaModel matriculaExistente = new MatriculaModel(1l,"25/06/2023",aluno1,curso1);

        given(matriculaService.exibirEspecifico(1L)).willReturn(Optional.of(matriculaExistente));

        mockMvc.perform(delete("/escola/matricula/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Matricula deletado com sucesso"));
    }

    @Test
    public void atualizarCursoAlunoTeste() throws Exception {
        Long id = 1L;
        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, 2L, "28/08/2023");
        ResponseEntity<String> response = ResponseEntity.ok("Curso do aluno na matrícula atualizado com sucesso.");
        doNothing().when(matriculaService).atualizarCurso(id, matriculaDTO);

        mockMvc.perform(put("/escola/matricula/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matriculaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Curso do aluno na matrícula atualizado com sucesso."));
        verify(matriculaService, times(1)).atualizarCurso(id, matriculaDTO);




    }

}

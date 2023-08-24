package com.catalisa.GerenciamentoEscolar.controllerTest;
import com.catalisa.GerenciamentoEscolar.Controller.AlunoController;
import com.catalisa.GerenciamentoEscolar.model.AlunoModel;
import com.catalisa.GerenciamentoEscolar.service.AlunoService;
import com.catalisa.GerenciamentoEscolar.service.MatriculaService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlunoService alunoService;

    @MockBean
    private MatriculaService matriculaService;

    @Test
    @DisplayName("Buscar todos alunos usando mockMVC")
    public void exibirListaDeAluno()throws Exception{

        //criando uma lista de alunos para o aluno service retornar
        AlunoModel aluno1 = new AlunoModel("Iris",30,"iris@gmail.com");
        AlunoModel aluno2 = new AlunoModel("Vitor",25,"vitor@gmail.com");
        Mockito.when(alunoService.listarAlunos()).thenReturn(List.of(aluno1,aluno2));

        //Configurando o MockMVC

        mockMvc.perform(get("/escola/aluno")).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].nomeAluno").value("Iris"))
                .andExpect(jsonPath("$[0].idade").value(30))
                .andExpect(jsonPath("$[0].email").value("iris@gmail.com"))
                .andExpect(jsonPath("$[1].nomeAluno").value("Vitor"))
                .andExpect(jsonPath("$[1].idade").value(25))
                .andExpect(jsonPath("$[1].email").value("vitor@gmail.com"));

        //Verifique o metodo listarAlunos do service foi chamado uma vez
        Mockito.verify(alunoService,times(1)).listarAlunos();


    }

    @Test
    public void  cadastrarAlunosTest() throws Exception {
        AlunoModel alunoCadastro = new AlunoModel("João",26,"joao@gmail.com");

        Mockito.when(alunoService.cadastrarAluno(alunoCadastro)).thenReturn(alunoCadastro);

        mockMvc.perform(post("/escola/aluno").contentType("application/json")
                .content(objectMapper.writeValueAsString(alunoCadastro)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(alunoCadastro)));
    }

    @Test
    public void testDeletarConta() throws Exception {
        // Simule o retorno de um aluno existente
        AlunoModel alunoExistente = new AlunoModel(1L, "João", 26, "joao@gmail.com");

        given(alunoService.exibirEspecifico(1L)).willReturn(Optional.of(alunoExistente));

        mockMvc.perform(delete("/escola/aluno/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Conta deletada com sucesso"));
    }



}

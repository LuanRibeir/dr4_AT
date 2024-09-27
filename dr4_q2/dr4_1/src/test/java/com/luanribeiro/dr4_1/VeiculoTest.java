package com.luanribeiro.dr4_1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.luanribeiro.dr4_1.model.Veiculo;
import com.luanribeiro.dr4_1.service.VeiculoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private VeiculoService vService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {

    }

    @Test
    public void testAdd() throws Exception {
        Veiculo veiculo = new Veiculo(1L, "toyota", "ABC-1234", 45000);

        when(vService.save(any(Veiculo.class))).thenReturn(veiculo);

        mockMvc.perform(post("/veiculos/").contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"toyota\",\"placa\":\"ABC-1234\",\"preco\":45000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("toyota"))
                .andExpect(jsonPath("$.placa").value("ABC-1234"))
                .andExpect(jsonPath("$.preco").value("45000"));

    }

    @Test
    public void testFindAll() throws Exception {
        Veiculo veiculo1 = new Veiculo(1L, "toyota", "ABC-1234", 45000);
        Veiculo veiculo2 = new Veiculo(2L, "honda", "XYZ-5678", 60000);

        when(vService.getAll()).thenReturn(List.of(veiculo1, veiculo2));

        mockMvc.perform(get("/veiculos/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("toyota"))
                .andExpect(jsonPath("$[0].placa").value("ABC-1234"))
                .andExpect(jsonPath("$[0].preco").value(45000))
                .andExpect(jsonPath("$[1].marca").value("honda"))
                .andExpect(jsonPath("$[1].placa").value("XYZ-5678"))
                .andExpect(jsonPath("$[1].preco").value(60000));
    }

    @Test
    public void testGetById() throws Exception {
        Long id = 1L;
        Veiculo veiculo = new Veiculo(id, "toyota", "ABC-1234", 45000);

        when(vService.getById(id)).thenReturn(veiculo);

        mockMvc.perform(get("/veiculos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("toyota"))
                .andExpect(jsonPath("$.placa").value("ABC-1234"))
                .andExpect(jsonPath("$.preco").value(45000));
    }

    @Test
    public void testDeleteById() throws Exception {
        Long id = 1L;
        Veiculo veiculoDeletado = new Veiculo(id, "toyota", "ABC-1234", 45000);

        when(vService.deleteById(id)).thenReturn(veiculoDeletado);

        mockMvc.perform(delete("/veiculos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("toyota"))
                .andExpect(jsonPath("$.placa").value("ABC-1234"))
                .andExpect(jsonPath("$.preco").value(45000));
    }

    @Test
    public void testUpdateById() throws Exception {
        Long id = 1L;
        Veiculo veiculoAtualizado = new Veiculo(id, "toyota", "DEF-5678", 50000);

        when(vService.updateById(any(Long.class), any(Veiculo.class))).thenReturn(veiculoAtualizado);

        mockMvc.perform(put("/veiculos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"toyota\",\"placa\":\"DEF-5678\",\"preco\":50000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("toyota"))
                .andExpect(jsonPath("$.placa").value("DEF-5678"))
                .andExpect(jsonPath("$.preco").value(50000));
    }

}

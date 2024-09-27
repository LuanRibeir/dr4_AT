package com.luanribeiro.dr4_1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marca;
    private String placa;
    private Integer preco;

    public void update(String marca, String placa, Integer preco) {
        this.marca = marca;
        this.placa = placa;
        this.preco = preco;
    }
}

package com.luanribeiro.dr4_7.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("livros")
@Data
@AllArgsConstructor
public class Livro {
    @Id
    private Long id;
    private String titulo;
    private String autor;
    private Integer preco;

    public void update(String titulo, String autor, Integer preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }
}
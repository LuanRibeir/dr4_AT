package com.luanribeiro.dr4_6.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.luanribeiro.dr4_6.domain.Livro;

public interface LivroRepository extends R2dbcRepository<Livro, Long> {

}

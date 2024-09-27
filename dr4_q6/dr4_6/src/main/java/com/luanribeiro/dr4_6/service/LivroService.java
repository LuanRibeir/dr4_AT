package com.luanribeiro.dr4_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanribeiro.dr4_6.domain.Livro;
import com.luanribeiro.dr4_6.repository.LivroRepository;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class LivroService {
    @Autowired
    private LivroRepository lRepository;

    public Flux<Livro> getAll() {
        log.info("Service fetching all books.");

        return lRepository.findAll();
    }

    public Mono<Livro> getById(Long id) {
        log.info("Service fetching book with ID: {}", id);

        return lRepository.findById(id);
    }

    public Mono<Livro> save(Livro livro) {
        log.info("Service saving new book: {}", livro);

        return lRepository.save(livro);
    }

    public Mono<Void> deleteById(Long id) {
        log.info("Service deleting book with ID: {}", id);

        return lRepository.deleteById(id);
    }

}

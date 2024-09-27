package com.luanribeiro.dr4_6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanribeiro.dr4_6.domain.Livro;
import com.luanribeiro.dr4_6.service.LivroService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService lService;

    @PostMapping("/")
    public Mono<Livro> add(@RequestBody Livro livro) {
        return lService.save(livro);
    }

    @GetMapping("/{id}")
    public Mono<Livro> getById(@PathVariable Long id) {
        return lService.getById(id);
    }

    @GetMapping("/")
    public Flux<Livro> findAll() {
        return lService.getAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return lService.deleteById(id);
    }

}

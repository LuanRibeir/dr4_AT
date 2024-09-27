package com.luanribeiro.dr4_7.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanribeiro.dr4_7.domain.Livro;
import com.luanribeiro.dr4_7.service.LivroWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroWebClient lWebClient;

    public LivroController(LivroWebClient lWebClient) {
        this.lWebClient = lWebClient;
    }

    @PostMapping("/")
    public Mono<Livro> add(@RequestBody Livro livro) {
        return lWebClient.save(livro);
    }

    @GetMapping("/{id}")
    public Mono<Livro> getById(@PathVariable Long id) {
        return lWebClient.getById(id);
    }

    @GetMapping("/")
    public Flux<Livro> findAll() {
        return lWebClient.getAllLivros();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return lWebClient.deleteById(id);
    }

}

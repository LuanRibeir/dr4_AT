package com.luanribeiro.dr4_7.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.luanribeiro.dr4_7.domain.Livro;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class LivroWebClient {
    private final WebClient webClient;

    public LivroWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/livros/").build();
    }

    public Flux<Livro> getAllLivros() {
        log.info("Service fetching all books.");

        return webClient.get().uri("/").retrieve().bodyToFlux(Livro.class);
    }

    public Mono<Livro> getById(Long id) {
        log.info("Service fetching book with ID: {}", id);

        return webClient.get().uri("/{id}", id).retrieve().bodyToMono(Livro.class);
    }

    public Mono<Livro> save(Livro livro) {
        log.info("Service saving new book: {}", livro);

        return webClient.post().uri("/").bodyValue(livro).retrieve().bodyToMono(Livro.class);
    }

    public Mono<Void> deleteById(Long id) {
        log.info("Service deleting book with ID: {}", id);

        return webClient.delete().uri("/{id}", id).retrieve().bodyToMono(void.class);
    }

}

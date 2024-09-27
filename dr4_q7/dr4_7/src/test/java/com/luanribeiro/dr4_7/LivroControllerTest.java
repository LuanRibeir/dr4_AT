package com.luanribeiro.dr4_7;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.luanribeiro.dr4_7.controller.LivroController;
import com.luanribeiro.dr4_7.domain.Livro;
import com.luanribeiro.dr4_7.service.LivroWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(LivroController.class)
public class LivroControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LivroWebClient lRepository;

    private Livro livro;

    @BeforeEach
    void setUp() {
        livro = new Livro(1L, "Titulo teste", "Autor Teste", 19);

    }

    @Test
    void testAdd() {

        Livro novoLivro = new Livro(null, "Titulo teste", "Autor Teste", 19);
        Livro livroAdicionado = new Livro(2L, "Titulo teste", "Autor Teste", 19);

        doReturn(Mono.just(livroAdicionado)).when(lRepository).save(novoLivro);

        webTestClient.post().uri("/livros/")
                .bodyValue(novoLivro)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Livro.class)
                .isEqualTo(livroAdicionado);

        verify(lRepository, times(1)).save(novoLivro);
    }

    @Test
    void testGetById() {
        doReturn(Mono.just(livro)).when(lRepository).getById(1L);

        webTestClient.get().uri("/livros/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Livro.class)
                .isEqualTo(livro);

        verify(lRepository, times(1)).getById(1L);
    }

    @Test
    void testFindAllLivros() {
        doReturn(Flux.just(livro)).when(lRepository).getAllLivros();

        webTestClient.get().uri("/livros/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Livro.class)
                .hasSize(1)
                .contains(livro);

        verify(lRepository, times(1)).getAllLivros();
    }

    @Test
    void testDeleteById() {
        doReturn(Mono.empty()).when(lRepository).deleteById(1L);

        webTestClient.delete().uri("/livros/1")
                .exchange()
                .expectStatus().isOk();

        verify(lRepository, times(1)).deleteById(1L);
    }
}

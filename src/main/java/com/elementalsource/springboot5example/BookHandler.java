package com.elementalsource.springboot5example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {

    private final BookRepository bookRepository;

    public BookHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<ServerResponse> findByIsbn(ServerRequest serverRequest) {
        String isbn = serverRequest.pathVariable("isbn");
        Mono<Book> bookMono = bookRepository.findByIsbn(isbn);
        return bookMono
                    .flatMap(b -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(b)))
                    .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        Flux<Book> books = bookRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(books, Book.class);
    }

    public Mono<ServerResponse> createBook(ServerRequest serverRequest) {
        Mono<Book> bookMono = serverRequest.bodyToMono(Book.class);
        return ServerResponse.ok().build(bookRepository.save(bookMono));
    }
}

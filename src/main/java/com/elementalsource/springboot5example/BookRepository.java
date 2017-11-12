package com.elementalsource.springboot5example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {
    Mono<Book> findByIsbn(String isbn);
    Flux<Book> findAll();
    Mono<Void> save(Mono<Book> book);
}

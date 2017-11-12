package com.elementalsource.springboot5example;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);

    private final Map<String, Book> books;

    public BookRepositoryImpl() {
        this.books = new HashMap<>();
    }

    @Override
    public Mono<Book> findByIsbn(String isbn) {
        return Mono.justOrEmpty(books.get(isbn));
    }

    @Override
    public Flux<Book> findAll() {
        return Flux.fromIterable(books.values());
    }

    @Override
    public Mono<Void> save(Mono<Book> bookMono) {
        return bookMono.doOnNext(book -> {
            books.put(book.getIsbn(), book);
            logger.info("Saving book {}", book);
        }).thenEmpty(Mono.empty());
    }
}

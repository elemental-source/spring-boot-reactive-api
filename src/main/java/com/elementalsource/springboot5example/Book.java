package com.elementalsource.springboot5example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    private final String isbn;
    private final String name;

    public Book(@JsonProperty("isbn") String isbn,
                @JsonProperty("name") String name) {
        this.isbn = isbn;
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                   "isbn='" + isbn + '\'' +
                   ", name='" + name + '\'' +
                   '}';
    }
}

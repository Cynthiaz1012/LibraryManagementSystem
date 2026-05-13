package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Book extends Item {
    private String ISBN;
    private String author;
    private String genre;

    public Book(String id, String title, Status status, String ISBN, String author, String genre) {
        super(id, title, status);
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
    }
}

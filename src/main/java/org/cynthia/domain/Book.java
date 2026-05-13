package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Book extends Item {
    private String isbn;
    private String author;
    private String genre;

    public Book(String id, String title, String ISBN, String author, String genre) {
        super(id, title);
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String getDetails() {
        return "Book: " + title + " by " + author;
    }
}

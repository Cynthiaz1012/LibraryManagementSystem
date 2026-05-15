package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper=true)
public class Book extends Item {
    private String isbn;
    private String author;
    private String genre;

    public Book(int id, String title, String isbn, String author, String genre) {
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

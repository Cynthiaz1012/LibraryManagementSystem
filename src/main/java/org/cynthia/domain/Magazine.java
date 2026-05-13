package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Magazine extends Item {
    private int issueNumber;
    private String publisher;

    public Magazine(int id, String title, int issueNumber, String publisher) {
        super(id, title);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    @Override
    public String getDetails() {
        return "Magazine: " + title + " published by " + publisher;
    }
}

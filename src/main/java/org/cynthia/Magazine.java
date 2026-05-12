package org.cynthia;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Magazine extends Item {
    private int issueNumber;
    private String publisher;

    public Magazine(String id, String title, Status status, int issueNumber, String publisher) {
        super(id, title, status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }
}

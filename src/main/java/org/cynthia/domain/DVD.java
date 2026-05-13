package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DVD extends Item {
    private String director;
    private int duration;

    public DVD(String id, String title, Status status, String director, int duration) {
        super(id, title, status);
        this.director = director;
        this.duration = duration;
    }
}

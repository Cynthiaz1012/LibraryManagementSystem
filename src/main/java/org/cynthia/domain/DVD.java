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

    public DVD(int id, String title, String director, int duration) {
        super(id, title);
        this.director = director;
        this.duration = duration;
    }

    @Override
    public String getDetails() {
        return "DVD: " + title + " directed by " + director;
    }
}

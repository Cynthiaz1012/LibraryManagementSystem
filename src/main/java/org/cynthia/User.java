package org.cynthia;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public abstract class User {
    protected String id;
    protected String name;
    protected List<Item> borrowedItems;

    public User(String id, String name, List<Item> borrowedItems) {
        this.id = id;
        this.name = name;
        this.borrowedItems = borrowedItems;
    }
}

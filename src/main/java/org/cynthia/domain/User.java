package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
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
        this.borrowedItems = new ArrayList<>();
    }

    /**
     * Returns the maximum number of items this user can borrow.
     * @return Maximum borrowing limit.
     */
    public abstract int getBorrowingLimit();


    public void borrowItem(Item item) {
        borrowedItems.add(item);
    }

    public void returnItem(Item item) {
        borrowedItems.remove(item);
    }
}

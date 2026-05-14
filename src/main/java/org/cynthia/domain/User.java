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
    protected int id;
    protected String name;
    protected List<Item> borrowedItems;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    /**
     * Checks if the user can borrow an item.
     * @param item the item to borrow
     * @return true if allowed, otherwise false
     */
    public abstract boolean canBorrow(Item item);

    /**
     * Borrows an item for the user.
     * @param item the item to borrow
     */
    public void borrowItem(Item item) {
        borrowedItems.add(item);
        item.setStatus(Item.ItemStatus.BORROWED);
    }

    /**
     * Returns an item from the user.
     * @param item the item to return
     */
    public void returnItem(Item item) {
        borrowedItems.remove(item);
        item.setStatus(Item.ItemStatus.IN_STORE);
    }
}

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

    /**
     * Returns the maximum number of items this user can borrow.
     * @return Maximum borrowing limit.
     */
    public abstract int getBorrowingLimit();

    /**
     * Checks if the user can borrow more items.
     * @return True if current borrowed count < limit, else false.
     */
    public boolean canBorrow() {
        return borrowedItems.size() < getBorrowingLimit();
    }
}

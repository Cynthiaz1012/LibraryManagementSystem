package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Student extends User {
    public Student(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    @Override
    public int getBorrowingLimit() {
        return 5;
    }

    /**
     * Students can only borrow books.
     * @param item The item to check.
     * @return True if item is a Book, false otherwise.
     */
    public boolean canBorrowItem(Item item) {
        return item instanceof Book;
    }


}

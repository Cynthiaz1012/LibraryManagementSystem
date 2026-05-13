package org.cynthia;


import java.util.List;

public class Student extends User{
    public Student(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
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

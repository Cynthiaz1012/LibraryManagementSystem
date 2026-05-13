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

    @Override
    public void borrowItem(Item item) {
        if (!(item instanceof Book)) {
            throw new IllegalArgumentException("Students can only borrow books.");
        }
        if (borrowedItems.size() >= getBorrowingLimit()) {
            throw new IllegalArgumentException("Student borrow limit reached.");
        }
        borrowedItems.add(item);
    }
}

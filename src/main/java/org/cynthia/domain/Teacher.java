package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Teacher extends User {
    public Teacher(String id, String name) {
        super(id, name);
    }

    @Override
    public int getBorrowingLimit() {
        return 10;
    }

    @Override
    public void borrowItem(Item item) {
        if (borrowedItems.size() >= getBorrowingLimit()) {
            throw new IllegalArgumentException("Teacher borrow limit reached.");
        }
        borrowedItems.add(item);
    }
}

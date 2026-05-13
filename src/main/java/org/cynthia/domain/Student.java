package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.cynthia.util.Constants.MAX_BOOKS_STUDENT;

@Getter
@Setter
@ToString
public class Student extends User {
    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean canBorrow(Item item) {
        if (!(item instanceof Book)) {
            return false;
        }
        return borrowedItems.size() < MAX_BOOKS_STUDENT;
    }
}

package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.cynthia.util.Constants.MAX_ITEMS_TEACHER;

@Getter
@Setter
@ToString
public class Teacher extends User {
    public Teacher(int id, String name) {
        super(id, name);
    }

    @Override
    public boolean canBorrow(Item item) {
        return borrowedItems.size() < MAX_ITEMS_TEACHER;
    }
}

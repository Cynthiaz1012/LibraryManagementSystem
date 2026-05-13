package org.cynthia;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Teacher extends User {
    public Teacher(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    @Override
    public int getBorrowingLimit() {
        return 10;
    }
}

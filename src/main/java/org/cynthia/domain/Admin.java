package org.cynthia.domain;

public class Admin extends User {

    public Admin(String id, String name) {
        super(id, name);
    }

    @Override
    public int getBorrowingLimit() {
        return 0;
    }
}

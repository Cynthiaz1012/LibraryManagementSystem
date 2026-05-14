package org.cynthia.domain;

import org.cynthia.Interfaces.Reportable;

import java.util.List;

public class Admin extends User implements Reportable {

    public Admin(int id, String name) {
        super(id, name);
    }
    @Override
    public boolean canBorrow(Item item) {
        return false;
    }

    @Override
    public void generateReport(List<Item> items) {
        for (Item item : items) {
            System.out.println(item);
        }
    }
}

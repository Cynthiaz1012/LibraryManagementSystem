package org.cynthia.domain;

import org.cynthia.Interfaces.Reportable;

public class Admin extends User implements Reportable {

    public Admin(String id, String name) {
        super(id, name);
    }
}

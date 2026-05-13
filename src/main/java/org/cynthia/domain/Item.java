package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Item {
    protected String id;
    protected String title;
    protected ItemStatus status;

    public Item(String id, String title) {
        this.id = id;
        this.title = title;
        this.status = ItemStatus.IN_STORE;
    }
    public abstract String getDetails();

    public enum ItemStatus {
        IN_STORE,
        BORROWED,
        LOST
    }
}

package org.cynthia.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Item {
    protected int id;
    protected String title;
    protected ItemStatus status;

    public Item(int id, String title) {
        this.id = id;
        this.title = title;
        this.status = ItemStatus.IN_STORE;
    }

    /**
     * Returns item details.
     * @return item details as a string
     */
    public abstract String getDetails();

    public enum ItemStatus {
        IN_STORE,
        BORROWED,
        LOST
    }
}

package org.cynthia;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Item {
    private String id;
    private String title;
    private Status status;

    public Item(String id, String title, Status status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    /**
     * Checks whether item is available.
     * @return true if available
     */
    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    public enum Status {
        AVAILABLE,
        BORROWED,
        LOST
    }
}

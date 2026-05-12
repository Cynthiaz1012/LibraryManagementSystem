package org.cynthia;

public abstract class Item {
    private String id;
    private String title;
    private Status status;

    public enum Status {
        AVAILABLE,
        BORROWED,
        LOST
    }
}

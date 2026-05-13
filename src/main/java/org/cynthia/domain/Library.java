package org.cynthia.domain;

import org.cynthia.Interfaces.Reportable;

import java.util.*;

public class Library implements Reportable {
    private List<Item> items;
    private List<User> users;

    private Queue<User> waitingQueue;
    private Stack<String> logs;
    private Set<String> borrowedItemIds;
    private Map<String, Item> itemMap;

    public Library() {
        items = new ArrayList<>();
        users = new ArrayList<>();
        waitingQueue = new LinkedList<>();
        logs = new Stack<>();
        borrowedItemIds = new HashSet<>();
        itemMap = new HashMap<>();
    }
}

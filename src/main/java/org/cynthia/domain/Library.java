package org.cynthia.domain;

import java.util.*;

public class Library {
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

    public void addItem(Item item) {
        items.add(item);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Item findItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void borrowItem(int userId, int itemId) {
        User user = findUserById(userId);
        Item item = findItemById(itemId);

        if (user == null || item == null) {
            throw new IllegalArgumentException("User or Item not found.");
        }

        if (item.getStatus() != Item.ItemStatus.IN_STORE) {
            throw new IllegalStateException("Item unavailable.");
        }

        if (!user.canBorrow(item)) {
            throw new IllegalStateException("Borrow limit exceeded.");
        }
        user.borrowItem(item);
    }

    public void returnItem(int userId, int itemId) {
        User user = findUserById(userId);
        Item item = findItemById(itemId);

        if (user == null || item == null) {
            throw new IllegalArgumentException("User or Item not found.");
        }
        user.returnItem(item);
    }

    public List<Item> recursiveSearchByTitle(String keyword) {
        return recursiveSearch(keyword.toLowerCase(), 0, new ArrayList<>());
    }

    private List<Item> recursiveSearch(String keyword, int index, List<Item> result) {
        if (index >= items.size()) {
            return result;
        }

        Item current = items.get(index);
        if (current.getTitle().toLowerCase().contains(keyword)) {
            boolean exists = false;
            for (Item item : result) {
                if (item.getTitle().equalsIgnoreCase(current.getTitle())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                result.add(current);
            }
        }
        return recursiveSearch(keyword, index + 1, result);
    }
}

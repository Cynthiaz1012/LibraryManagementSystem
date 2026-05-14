package org.cynthia.domain;

import lombok.Getter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Library {
    private List<Item> items;
    private List<User> users;

    public Library() {
        items = new ArrayList<>();
        users = new ArrayList<>();
    }

    /**
     * Adds a new item to the library.
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Adds a new user to the library.
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Finds a user by their ID.
     * @param id the user ID
     * @return the matching user, or null if not found
     */
    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * Finds an item by its ID.
     * @param id the item ID
     * @return the matching item, or null if not found
     */
    public Item findItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Allows a user to borrow an item.
     * @param userId the user ID
     * @param itemId the item ID
     * @throws IllegalArgumentException if the user or item does not exist
     * @throws IllegalStateException if the item is unavailable or the user reached the limit
     */
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

    /**
     * Allows a user to return an item.
     * @param userId the user ID
     * @param itemId the item ID
     * @throws IllegalArgumentException if the user or item does not exist
     */
    public void returnItem(int userId, int itemId) {
        User user = findUserById(userId);
        Item item = findItemById(itemId);

        if (user == null || item == null) {
            throw new IllegalArgumentException("User or Item not found.");
        }
        user.returnItem(item);
    }

    /**
     * Searches items recursively by title.
     * @param keyword the title keyword
     * @return a list of matching items
     */
    public List<Item> recursiveSearchByTitle(String keyword) {
        return recursiveSearch(keyword.toLowerCase(), 0, new ArrayList<>());
    }

    /**
     * Recursive helper method for searching items.
     * @param keyword the keyword to search
     * @param index current index in the list
     * @param result list of matching items
     * @return list of matching items
     */
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

    /**
     * Searches items using streams by title.
     * @param keyword the title keyword
     * @return a list of matching items
     */
    public List<Item> streamSearchByTitle(String keyword) {
        return items.stream()
                .filter(item -> item.getTitle()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toMap(
                        Item::getTitle, item -> item, (a, b) -> a))
                .values()
                .stream()
                .toList();
    }

    /**
     * Saves all items into a file.
     * @param fileName the output file name
     * @throws IOException if writing fails
     */
    public void backupItems(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (Item item : items) {
            writer.write(item.toString());
            writer.write("\n");
        }
        writer.close();
    }

    /**
     * Saves all users into a file.
     * @param fileName the output file name
     * @throws IOException if writing fails
     */
    public void backupUsers(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (User user : users) {
            writer.write(user.toString());
            writer.write("\n");
        }
        writer.close();
    }
}

package org.cynthia.util;

public class Validation {
    /**
     * Checks if an ISBN is valid.
     * @param isbn the ISBN number
     * @return true if valid, otherwise false
     */
    public static boolean isValidISBN(String isbn) {
        return isbn.matches("\\d{13}");
    }
}

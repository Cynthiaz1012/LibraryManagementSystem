import org.cynthia.domain.*;
import org.cynthia.util.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class MainTest {
    @Test
    @DisplayName("Valid ISBN -> true")
    void testIsValidISBN1() {
        String isbn = "1234567890123";
        boolean expected = true;
        boolean actual = Validation.isValidISBN(isbn);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ISBN with letters -> false")
    void testIsValidISBN2() {
        String isbn = "12345ABC90123";
        boolean expected = false;
        boolean actual = Validation.isValidISBN(isbn);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Short ISBN -> false")
    void testIsValidISBN3() {
        String isbn = "1234567";
        boolean expected = false;
        boolean actual = Validation.isValidISBN(isbn);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Student borrows book -> true")
    void testCanBorrow1() {
        Student student = new Student(1, "Alice");
        Book book = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        boolean expected = true;
        boolean actual = student.canBorrow(book);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Student borrows DVD -> false")
    void testCanBorrow2() {
        Student student = new Student(1, "Alice");
        DVD dvd = new DVD(2, "Movie", "John", 120);
        boolean expected = false;
        boolean actual = student.canBorrow(dvd);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Student exceeds limit -> false")
    void testCanBorrow3() {
        Student student = new Student(1, "Alice");
        for (int i = 0; i < 5; i++) {
            student.borrowItem(new Book(i, "Book", "1234567890123", "Author", "Genre"));
        }

        Book newBook = new Book(10, "Extra", "1234567890123", "Tom", "Programming");
        boolean expected = false;
        boolean actual = student.canBorrow(newBook);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find existing user")
    void testFindUserById1() {
        Library library = new Library();
        Student student = new Student(1, "Alice");
        library.addUser(student);
        User actual = library.findUserById(1);
        Assertions.assertEquals(student, actual);
    }

    @Test
    @DisplayName("Find missing user")
    void testFindUserById2() {
        Library library = new Library();
        User actual = library.findUserById(99);
        Assertions.assertNull(actual);
    }

    @Test
    @DisplayName("Find second user")
    void testFindUserById3() {
        Library library = new Library();
        Student student1 = new Student(1, "Alice");
        Student student2 = new Student(2, "Tom");
        library.addUser(student1);
        library.addUser(student2);
        User actual = library.findUserById(2);
        Assertions.assertEquals(student2, actual);
    }

    @Test
    @DisplayName("Find existing item")
    void testFindItemById1() {
        Library library = new Library();
        Book book = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        library.addItem(book);
        Item actual = library.findItemById(1);
        Assertions.assertEquals(book, actual);
    }

    @Test
    @DisplayName("Find missing item")
    void testFindItemById2() {
        Library library = new Library();
        Item actual = library.findItemById(50);
        Assertions.assertNull(actual);
    }

    @Test
    @DisplayName("Find second item")
    void testFindItemById3() {
        Library library = new Library();
        Book book1 = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        DVD dvd = new DVD(2, "Movie", "John", 120);
        library.addItem(book1);
        library.addItem(dvd);
        Item actual = library.findItemById(2);
        Assertions.assertEquals(dvd, actual);
    }

    @Test
    @DisplayName("Borrow item successfully")
    void testBorrowItem1() {
        Library library = new Library();
        Student student = new Student(1, "Alice");
        Book book = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        library.addUser(student);
        library.addItem(book);
        library.borrowItem(1, 1);
        Item.ItemStatus expected = Item.ItemStatus.BORROWED;
        Item.ItemStatus actual = book.getStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Borrow unavailable item")
    void testBorrowItem2() {
        Library library = new Library();
        Student student = new Student(1, "Alice");
        Book book = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        book.setStatus(Item.ItemStatus.BORROWED);
        library.addUser(student);
        library.addItem(book);
        Assertions.assertThrows(IllegalStateException.class, () -> library.borrowItem(1, 1)
        );
    }

    @Test
    @DisplayName("Borrow missing item")
    void testBorrowItem3() {
        Library library = new Library();
        Student student = new Student(1, "Alice");
        library.addUser(student);
        Assertions.assertThrows(IllegalArgumentException.class, () -> library.borrowItem(1, 99)
        );
    }

    @Test
    @DisplayName("Return item successfully")
    void testReturnItem1() {
        Library library = new Library();
        Student student = new Student(1, "Alice");
        Book book = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        library.addUser(student);
        library.addItem(book);
        library.borrowItem(1, 1);
        library.returnItem(1, 1);
        Item.ItemStatus expected = Item.ItemStatus.IN_STORE;
        Item.ItemStatus actual = book.getStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Recursive search finds item")
    void testRecursiveSearch1() {
        Library library = new Library();
        Book book = new Book(1, "Java Basics", "1234567890123", "Tom", "Programming");
        library.addItem(book);
        int expected = 1;
        int actual = library.recursiveSearchByTitle("Java").size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Recursive search no result")
    void testRecursiveSearch2() {
        Library library = new Library();
        int expected = 0;
        int actual = library.recursiveSearchByTitle("Python").size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Recursive search case insensitive")
    void testRecursiveSearch3() {
        Library library = new Library();
        Book book = new Book(1, "JAVA", "1234567890123", "Tom", "Programming");
        library.addItem(book);
        int expected = 1;
        int actual = library.recursiveSearchByTitle("java").size();
    }

    @Test
    @DisplayName("Backup items creates file")
    void testBackupItems1() throws IOException {
        Library library = new Library();
        Book book = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        library.addItem(book);
        String fileName = "testItems1.csv";
        library.backupItems(fileName);
        File file = new File(fileName);
        boolean expected = true;
        boolean actual = file.exists();
        Assertions.assertEquals(expected, actual);
        file.delete();
    }

    @Test
    @DisplayName("Backup items writes content")
    void testBackupItems2() throws IOException {
        Library library = new Library();
        Book book = new Book(1, "Java", "1234567890123", "Tom", "Programming");
        library.addItem(book);
        String fileName = "testItems2.csv";
        library.backupItems(fileName);
        File file = new File(fileName);
        boolean expected = true;
        boolean actual = file.length() > 0;
        Assertions.assertEquals(expected, actual);
        file.delete();
    }

    @Test
    @DisplayName("Backup empty items list")
    void testBackupItems3() throws IOException {
        Library library = new Library();
        String fileName = "testItems3.csv";
        library.backupItems(fileName);
        File file = new File(fileName);
        boolean expected = true;
        boolean actual = file.exists();
        Assertions.assertEquals(expected, actual);
        file.delete();
    }

    @Test
    @DisplayName("Backup users creates file")
    void testBackupUsers1() throws IOException {
        Library library = new Library();
        Student student = new Student(1, "Alice");
        library.addUser(student);
        String fileName = "testUsers1.csv";
        library.backupUsers(fileName);
        File file = new File(fileName);
        boolean expected = true;
        boolean actual = file.exists();
        Assertions.assertEquals(expected, actual);
        file.delete();
    }

    @Test
    @DisplayName("Backup users writes content")
    void testBackupUsers2() throws IOException {
        Library library = new Library();
        Student student = new Student(1, "Alice");
        library.addUser(student);
        String fileName = "testUsers2.csv";
        library.backupUsers(fileName);
        File file = new File(fileName);
        boolean expected = true;
        boolean actual = file.length() > 0;
        Assertions.assertEquals(expected, actual);
        file.delete();
    }

    @Test
    @DisplayName("Backup empty users list")
    void testBackupUsers3() throws IOException {
        Library library = new Library();
        String fileName = "testUsers3.csv";
        library.backupUsers(fileName);
        File file = new File(fileName);
        boolean expected = true;
        boolean actual = file.exists();
        Assertions.assertEquals(expected, actual);
        file.delete();
    }
}

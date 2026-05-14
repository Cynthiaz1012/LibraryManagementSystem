import org.cynthia.domain.*;
import org.cynthia.util.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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


}

import org.cynthia.domain.Book;
import org.cynthia.domain.DVD;
import org.cynthia.domain.Student;
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

}

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
}

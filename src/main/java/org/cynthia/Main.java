package org.cynthia;

import org.cynthia.domain.Book;
import org.cynthia.domain.Library;
import org.cynthia.domain.Student;

public class Main {
    static void main() {
        Library library = new Library();
        Book book1 = new Book(1, "Java Basics", "1234567890123", "Tom", "Programming");
        Student student = new Student(100, "Alice");
        library.addItem(book1);
        library.addUser(student);
        library.borrowItem(100, 1);
        System.out.println(student.getBorrowedItems());
    }
}

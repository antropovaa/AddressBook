import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class AddressBookTest {
    @Test
    void addNewRecord() {
        AddressBook book = new AddressBook();
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Ленина", 104, 2);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals("Горького, 150, 26", book.searchByName("Аня"));
        assertEquals("Ленина, 104, 2", book.searchByName("Петя"));
        assertEquals("Советская, 32, 13", book.searchByName("Света"));
    }

    @Test
    void deleteRecord() throws NullPointerException {
        AddressBook book = new AddressBook();
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Ленина", 104, 2);
        book.addNewRecord("Света", "Советская", 32, 13);
        book.deleteRecord("Света");
        try {
            book.searchByName("Света");
            fail("Expected NullPointerException");
        } catch (NullPointerException thrown) {
            assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    void changeAddress() {
        AddressBook book = new AddressBook();
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.changeAddress("Аня", "Пушкина", 4, 98);
        assertEquals("Пушкина, 4, 98", book.searchByName("Аня"));
    }

    @Test
    void searchByName() {
        AddressBook book = new AddressBook();
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Ленина", 104, 2);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals("Горького, 150, 26", book.searchByName("Аня"));
    }

    @Test
    void searchByStreet() {
        AddressBook book = new AddressBook();
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected,"Петя", "Аня");
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Горького", 150, 26);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals(expected, book.searchByStreet("Горького"));
    }

    @Test
    void searchByBuilding() {
        AddressBook book = new AddressBook();
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected,"Петя", "Аня");
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Горького", 150, 26);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals(expected, book.searchByBuilding("Горького", 150));
    }

}
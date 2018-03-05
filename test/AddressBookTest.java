import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class AddressBookTest {
    AddressBook book = new AddressBook();
    @Test
    void addNewRecord() {
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Ленина", 104, 2);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals("Горького, 150, 26", book.searchByName("Аня").toString());
        assertEquals("Ленина, 104, 2", book.searchByName("Петя").toString());
        assertEquals("Советская, 32, 13", book.searchByName("Света").toString());
        assertThrows(NullPointerException.class, () -> {
                book.addNewRecord("Аня", "Горького", 150, 26);
                book.addNewRecord(null, null, 32, 13);
        });
    }

    @Test
    void deleteRecord() throws NullPointerException {
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Ленина", 104, 2);
        book.addNewRecord("Света", "Советская", 32, 13);
        book.deleteRecord("Света");
        assertThrows(NullPointerException.class, () -> {
            book.deleteRecord(null);
            book.deleteRecord("Маша");
            book.searchByName("Света");
        });
    }

    @Test
    void changeAddress() {
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.changeAddress("Аня", "Пушкина", 4, 98);
        assertEquals("Пушкина, 4, 98", book.searchByName("Аня").toString());
        assertThrows(NullPointerException.class, () -> {
            book.changeAddress("Маша", "Ленина", 1, 23);
            book.changeAddress(null, "Советская", 0, 0);
        });
    }

    @Test
    void searchByName() {
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Ленина", 104, 2);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals("Горького, 150, 26", book.searchByName("Аня").toString());
        assertThrows(NullPointerException.class, () -> {
            book.searchByName("Маша");
            book.searchByName(null);
        });
    }

    @Test
    void searchByStreet() {
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected,"Петя", "Аня");
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Горького", 150, 26);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals(expected, book.searchByStreet("Горького"));
        assertThrows(NullPointerException.class, () -> book.searchByStreet(null));
    }

    @Test
    void searchByBuilding() {
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected,"Петя", "Аня");
        book.addNewRecord("Аня", "Горького", 150, 26);
        book.addNewRecord("Петя", "Горького", 150, 26);
        book.addNewRecord("Света", "Советская", 32, 13);
        assertEquals(expected, book.searchByBuilding("Горького", 150));
        assertThrows(NullPointerException.class, () -> {
            book.searchByBuilding(null, 12);
            book.searchByBuilding("Советская", 0);
        });
    }
}
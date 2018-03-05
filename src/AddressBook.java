import java.util.*;

/**
 * Create an address book, where person's name and address are stored.
 */
public class AddressBook {
    /**
     * Create an address of person by street, building and apartment.
     */
    public class Address {
        private String street;
        private int building;
        private int apartment;

        Address(String street, int building, int apartment) {
            this.street = street;
            this.building = building;
            this.apartment = apartment;
        }

        public String getStreet() {
            return street;
        }

        public int getBuilding() {
            return building;
        }

        public int getApartment() {
            return apartment;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Address address = (Address) o;

            if (building != address.building) return false;
            if (apartment != address.apartment) return false;
            return street != null ? street.equals(address.street) : address.street == null;
        }

        @Override
        public int hashCode() {
            int result = street != null ? street.hashCode() : 0;
            result = 31 * result + building;
            result = 31 * result + apartment;
            return result;
        }

        @Override
        public String toString() {
            return street + ", " + building + ", " + apartment;
        }
    }

    private Map<String, Address> addressBook;

    AddressBook() {
        this.addressBook = new HashMap<>();
    }

    /**
     * Add new record to address book by full information of his address.
     * @param name Name of person who is added to address book.
     * @param street Street on which person lives.
     * @param building Building on street in which person lives.
     * @param apartment Apartment of building.
     */
    public void addNewRecord(String name, String street, int building, int apartment) {
        if (name == null || street == null)
            throw new NullPointerException();
        if (!addressBook.containsKey(name)) {
            Address address = new Address(street, building, apartment);
            addressBook.put(name, address);
        }
    }

    /**
     * Delete person's information from address book by his name.
     * @param name Name of person who is deleted from address book.
     */
    public void deleteRecord(String name) {
        if (name == null || !addressBook.containsKey(name))
            throw new NullPointerException();
        addressBook.remove(name);
    }

    /**
     * Change person's address by replacing his old full information with new.
     * @param name Name of person whose address will be changed.
     * @param street New street of address.
     * @param building New building of address.
     * @param apartment New apartment of address.
     */
    public void changeAddress(String name, String street, int building, int apartment) {
        if (name == null || street == null || building == 0 || apartment == 0 || !addressBook.containsKey(name))
            throw new NullPointerException();
        Address newAddress = new Address(street, building, apartment);
        addressBook.put(name, newAddress);
    }

    /**
     * Search person's address in address book by his name.
     * @param name Name of person whose address need to be found.
     * @return Found address.
     */
    public Address searchByName(String name) {
        if (name == null || !addressBook.containsKey(name))
            throw new NullPointerException();
        return addressBook.get(name);
    }

    /**
     * Output list of people's name who live on given street.
     * @param street Street on which the search is carried out.
     * @return List of founded names.
     */
    public List<String> searchByStreet(String street) {
        if (street == null)
            throw new NullPointerException();
        List<String> searchResult = new ArrayList<>();
        Set<Map.Entry<String, Address>> allAddress = addressBook.entrySet();
        for (Map.Entry<String, Address> currentAddress : allAddress) {
            String currentStreet = currentAddress.getValue().getStreet();
            if (currentStreet.equals(street))
                searchResult.add(currentAddress.getKey());
        }
        return searchResult;
    }

    /**
     * Output list of people's name who live on given building of giving street.
     * @param street Street on which the search is carried out.
     * @param building Building on which the search is carried out.
     * @return List of names.
     */
    public List<String> searchByBuilding(String street, int building) {
        if (street == null || building == 0)
            throw new NullPointerException();
        List<String> allNames = searchByStreet(street);
        List<String> searchResult = new ArrayList<>();
        for (String currentName : allNames) {
            int currentBuilding = addressBook.get(currentName).getBuilding();
            if (currentBuilding == building) {
                searchResult.add(currentName);
            }
        }
        return searchResult;
    }
}
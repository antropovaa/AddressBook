import java.util.*;

public class AddressBook {
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
    }

    private String name;
    private Map<String, Address> addressBook;

    AddressBook() {
        this.addressBook = new HashMap<String, Address>();
    }

    // Добавь нового человека в адресную книгу
    public void addNewRecord(String name, String street, int building, int apartment) {
        Address address = new Address(street, building, apartment);
        addressBook.put(name, address);
    }

    // Удалить человека из адресной книги
    public void deleteRecord(String name) {
        addressBook.remove(name);
    }

    // Изменить адрес человека
    public void changeAddress(String name, String street, int building, int apartment){
        Address newAddress = new Address(street, building, apartment);
        addressBook.put(name, newAddress);
    }

    // Найти адрес человека по имени
    public String searchByName(String name) {
        Address address = addressBook.get(name);
        return address.getStreet() + ", " + address.getBuilding() + ", " + address.getApartment();
    }

    // Вывести список людей, живущих на заданной улице
    public List<String> searchByStreet(String street) {
        List<String> searchResult = new ArrayList<>();
        Collection<String> allNames = addressBook.keySet();
            for (String currentName : allNames) {
                String currentStreet = addressBook.get(currentName).getStreet();
                if (currentStreet.equals(street)) {
                    searchResult.add(currentName);
                }
            }
            return searchResult;
    }

    // Вывести список людей, живущих в заданном доме
    public List<String> searchByBuilding(String street, int building) {
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

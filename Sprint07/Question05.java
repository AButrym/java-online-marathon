import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;

/*Create class hierarchy that represent Address Book, where can be save records in format:
 "(first name, last name) => address":
    Records in the Address Book should be represented as objects of the NameAddressPair type.
    The pair "(first name, last name)" is key for access to "address" in the Address Book.
    The key "(first name, last name)" should be immutable and in Address Book cannot be two records with same key.
    The capacity of Address Book must grow twice when has no place for save the next record.
    The sortedBy(...) method should sorted records by ascending or descending using for this Arrays.sort(...) method.
    The Comparator should be implemented as an anonymous class.
    Sorting at first be by firstName field, and if the names match then by lastName field.
    The next() method from AddressBookIterator class should return record as String in next format: "First name: <first name>, Last name: <last name>, Address: <address>".

    A class hierarchy should be correspond the next class diagram:
    https://softserve.academy/pluginfile.php/63843/question/questiontext/77347/5/54917/task%20%235.png?time=1587458726481
 */
enum SortOrder {
    ASC, DESC
}

class AddressBook implements Iterable<String> {

    private static class NameAddressPair {
        private final Person person;
        private String address;

        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        private static class Person {
            private final String firstName;
            private final String lastName;
            private final int hash;

            private Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
                hash = Objects.hash(firstName, lastName);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return hash == person.hash &&
                        Objects.equals(firstName, person.firstName) &&
                        Objects.equals(lastName, person.lastName);
            }

            @Override
            public int hashCode() {
                return hash;
            }
        }
    }

    private static final String ITERATOR_FORMAT = "First name: %s, Last name: %s, Address: %s";

    private class AddressBookIterator implements Iterator<String> {
        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < AddressBook.this.counter;
        }

        @Override
        public String next() {
            if (hasNext()) {
                NameAddressPair res = addressBook[this.counter++];
                return String.format(ITERATOR_FORMAT, res.person.firstName, res.person.lastName, res.address);
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private NameAddressPair[] addressBook;
    private int counter = 0;

    public AddressBook(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be positive");
        }
        addressBook = new NameAddressPair[capacity];
    }

    private int find(NameAddressPair.Person person) {
        for (int i = 0; i < counter; i++) {
            if (Objects.equals(person, addressBook[i].person)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity() {
        if (counter + 1 > addressBook.length) {
            addressBook = Arrays.copyOf(addressBook, 2 * addressBook.length);
        }
    }

    public boolean create(String firstName, String lastName, String address) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        if (find(person) > -1) {
            return false;
        }
        ensureCapacity();
        addressBook[counter++] = new NameAddressPair(person, address);
        return true;
    }

    public String read(String firstName, String lastName) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        return ix > -1 ? addressBook[ix].address : null;
    }

    public boolean update(String firstName, String lastName, String address) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        if (ix > -1) {
            addressBook[ix].address = address;
            return true;
        }
        return false;
    }

    public boolean delete(String firstName, String lastName) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        if (ix == -1) {
            return false;
        } else if (ix < counter - 1) {
            System.arraycopy(addressBook, ix + 1, addressBook, ix, counter - ix - 1);
        }
        addressBook[--counter] = null;
        return true;
    }

    public int size() {
        return counter;
    }

    public void sortedBy(SortOrder order) {
        Comparator<String> comp;
        switch (order) {
            case ASC:
                comp = nullsFirst(naturalOrder());
                break;
            case DESC:
                comp = nullsLast(reverseOrder());
                break;
            default:
                throw new RuntimeException("Unknown SortOrder: " + order);
        }
        Arrays.sort(addressBook, 0, counter,
                new Comparator<NameAddressPair>() {
                    @Override
                    public int compare(NameAddressPair nap1, NameAddressPair nap2) {
                        int res = Objects.compare(nap1.person.firstName, nap2.person.firstName, comp);
                        if (res == 0) {
                            res = Objects.compare(nap1.person.lastName, nap2.person.lastName, comp);
                        }
                        return res;
                    }
                }
        );
    }

    @Override
    public Iterator<String> iterator() {
        return this.new AddressBookIterator();
    }
}

public class Main {
    public static void main(String[] args) {
        // smoke test
        AddressBook addressBook = new AddressBook(4);
        addressBook.create("John", "Brown", "Address #1");
        addressBook.create("Karen", "Davis", "Address #2");
        addressBook.create("Steven", "Taylor", "Address #1");
        addressBook.create("Susan", "Brown", "Address # 4");
        addressBook.update("Steven", "Taylor", "Address #3");
        addressBook.delete("John", "Brown");

        addressBook.forEach(System.out::println);

        System.out.println();
        addressBook.sortedBy(SortOrder.DESC);
        addressBook.forEach(System.out::println);
        System.out.println(addressBook.read("Steven", "Taylor"));
        System.out.println(addressBook.size());
    }
}

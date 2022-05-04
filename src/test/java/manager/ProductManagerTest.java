package manager;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import org.junit.jupiter.api.Test;
import products.Book;
import products.Product;
import products.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductManager manager = new ProductManager();

    @Test
    void addElementsWithOneId() {

        Product book1 = new Book(1, "Title1", 10, "Author1");
        Product book2 = new Book(1, "Title2", 20, "Author2");

        manager.add(book1);

        assertThrows(AlreadyExistsException.class, () -> {
            manager.add(book2);
        });
    }

    @Test
    void addSuccessTwoElements() {

        Product book1 = new Book(1, "Title1", 10, "Author1");
        Product book2 = new Book(2, "Title2", 20, "Author2");
        Product book3 = new Book(3, "Title3", 30, "Author3");

        manager.add(book1);
        manager.add(book2);

        Product[] expected = {book1, book2};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeNotFoundId() {

        Product book1 = new Book(1, "Title1", 10, "Author1");
        Product book2 = new Book(2, "Title2", 20, "Author2");
        Product book3 = new Book(3, "Title3", 30, "Author3");

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        assertThrows(NotFoundException.class, () -> {
            manager.removeId(4);
        });
    }

    @Test
    void removeFoundId() {

        Product book1 = new Book(1, "Title1", 10, "Author1");
        Product book2 = new Book(2, "Title2", 20, "Author2");
        Product book3 = new Book(3, "Title3", 30, "Author3");

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.removeId(2);

        Product[] expected = {book1, book3};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void addNullRepository() {

        Product book1 = new Book(2, "Title", 150, "Author");
        Product smartphone1 = new Smartphone(3, "Sony", 15000, "Japan");

        manager.add(book1);
        manager.add(smartphone1);

        Product[] expected = {book1, smartphone1};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeIdOneProductFromOneProduct() {

        Product book1 = new Book(2, "Title", 150, "Author");

        manager.add(book1);
        manager.removeId(2);

        Product[] expected = {};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeIdOneProductFromTwoProducts() {

        Product book1 = new Book(2, "Title", 150, "Author");
        Product smartphone1 = new Smartphone(3, "Sony", 15000, "Japan");

        manager.add(book1);
        manager.add(smartphone1);
        manager.removeId(2);

        Product[] expected = {smartphone1};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByOneResult () {

        Product book1 = new Book(2, "Title", 150, "Author");
        Product smartphone1 = new Smartphone(3, "Sony", 15000, "Japan");

        manager.add(book1);
        manager.add(smartphone1);

        Product[] expected = {book1};
        Product[] actual = manager.searchBy("Title");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByResult () {

        Product book1 = new Book(1, "Title1", 150, "Author1");
        Product smartphone1 = new Smartphone(2, "Sony", 15000, "Japan1");
        Product book2 = new Book(3, "Title2", 150, "Author2");
        Product smartphone2 = new Smartphone(4, "Sony", 15000, "Japan2");

        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);

        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = manager.searchBy("Sony");

        assertArrayEquals(expected, actual);
    }
}
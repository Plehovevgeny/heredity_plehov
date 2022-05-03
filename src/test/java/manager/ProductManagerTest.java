package manager;

import org.junit.jupiter.api.Test;
import products.Book;
import products.Product;
import products.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductManager manager = new ProductManager();

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
    void removeIdOneProductFromRepository() {

        Product book1 = new Book(2, "Title", 150, "Author");

        manager.add(book1);
        manager.removeId(2);

        Product[] expected = {};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeIdOneProductFromRepository1() {

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
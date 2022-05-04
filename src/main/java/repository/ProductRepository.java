package repository;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import products.Product;

public class ProductRepository {

    protected Product[] products = new Product[0];

    public ProductRepository() {
    }

    public ProductRepository(Product[] products) {
        this.products = products;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Продукт с id: \" + id + \" уже существует в репозитории");
        }
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return getProducts();
    }

    public void removeId (int id){
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: \" + id + \" not found");
        }
        Product [] tmp = new Product[products.length -1];
        int i = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[i] = product;
                i++;
            }
        }
        products = tmp;
    }

    public Product findById (int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}

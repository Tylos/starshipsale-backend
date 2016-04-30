package repositories;

import java.util.List;

import domain.Product;

public interface ProductsRepository {

    List<Product> getAll();

    Product getById(String id);
}

package repositories;

import domain.Product;

import java.util.List;

public interface ProductsRepository {

    List<Product> getAll();

    Product getById(Long id);
}

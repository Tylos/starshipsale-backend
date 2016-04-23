package repositories;

import domain.Product;
import repositories.ProductsRepository;

import javax.inject.Singleton;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class MemoryProductsRepository implements ProductsRepository {

    private Map<Long, Product> products;

    public MemoryProductsRepository() {
        this.products = new HashMap<>();
        fillProductList(products);
    }

    private void fillProductList(Map<Long, Product> products) {
        for (int i = 0; i < 20; i++) {
            products.put((long) (i+1), new Product());
        }
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getById(Long id) {
        return this.products.get(id);
    }
}

package repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import domain.Product;

@Singleton
public class MemoryProductsRepository implements ProductsRepository {

    private Map<String, Product> products;

    public MemoryProductsRepository() {
        this.products = new HashMap<>();
        fillProductList(products);
    }

    private void fillProductList(Map<String, Product> products) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Product> fixtures = objectMapper.readValue(
                    new File("./app/assets/product_fixture.json"),
                    new TypeReference<List<Product>>() {
            });

            fixtures.stream().forEach(fixture ->
                    products.put(fixture.getProductNameAsId(), fixture));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getById(String id) {
        return this.products.get(id);
    }
}

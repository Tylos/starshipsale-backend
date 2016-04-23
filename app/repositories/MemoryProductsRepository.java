package repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Product;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
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

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Product> fixtures = objectMapper.readValue(
                    new File("./app/assets/product_fixture.json"),
                    new TypeReference<List<Product>>() {
            });

            for (int i = 0; i < fixtures.size(); i++) {
                Product value = fixtures.get(i);
                long id = (long) (i + 1);
                value.id = id;
                products.put(id, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //new ObjectMapper().readValue(parse, new TypeReference<List<Product>>(){});
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

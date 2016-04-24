package controllers.mapper;

import domain.Product;
import domain.User;
import views.ProductView;

import java.util.ArrayList;
import java.util.List;

public class ProductToViewMapper {

    public static List<ProductView> map(List<Product> all, User userContext) {
        List<ProductView> results = new ArrayList<>();
        all.forEach(product -> {
            results.add(map(product, userContext));
        });

        return results;
    }

    public static ProductView map(Product product, User userContext) {
        return ProductView.newBuilder()
                .withId(product.id)
                .withName(product.name)
                .withModel(product.model)
                .withManufacturer(product.manufacturer)
                .withStarshipClass(product.starship_class)
                .withCostInCredits(product.cost_in_credits)
                .withImage(product.image)
                .withPassengers(product.passengers)
                .withCrew(product.crew)
                .withCargoCapacity(product.cargo_capacity)
                .withHyperdriveRating(product.hyperdrive_rating)
                .withIsFeatured(product.isFeatured)
                .withIsFavorite(userContext.isFavorite(product))
                .withIsInCart(userContext.isInCart(product))
                .build();
    }
}
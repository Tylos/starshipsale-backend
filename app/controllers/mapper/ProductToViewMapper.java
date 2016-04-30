package controllers.mapper;

import java.util.ArrayList;
import java.util.List;

import domain.Product;
import domain.User;
import views.ProductView;

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
                .withName(product.name)
                .withId(product.getId())
                .withModel(product.model)
                .withManufacturer(product.manufacturer)
                .withStarshipClass(product.starship_class)
                .withCostInCredits(product.cost_in_credits)
                .withImage(product.image)
                .withPassengers(product.passengers)
                .withCrew(product.crew)
                .withCargoCapacity(product.cargo_capacity)
                .withHyperdriveRating(product.hyperdrive_rating)
                .withIsFeatured(product.is_featured)
                .withIsFavorite(userContext.isFavorite(product))
                .withIsInCart(userContext.isInCart(product))
                .build();
    }
}
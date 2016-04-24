package controllers.api;

import javax.inject.Inject;

import controllers.authentication.OptionalAuthenticator;
import controllers.authentication.RequiredAuthenticator;
import domain.Product;
import domain.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import repositories.ProductsRepository;
import repositories.UserRepository;
import views.ProductView;

import java.util.ArrayList;
import java.util.List;

@Security.Authenticated(OptionalAuthenticator.class)
public class ProductsController extends Controller {

    @Inject
    ProductsRepository productsRepository;

    @Inject
    UserRepository userRepository;

    public Result list() {
        return ok(Json.toJson(
                mapProducts(
                        productsRepository.getAll(),
                        getUserContextWithAnonymousFallback())));
    }

    public Result show(Long id) {
        Product product = productsRepository.getById(id);
        if (product != null) {
            return ok(Json.toJson(
                    mapProduct(
                            product,
                            getUserContextWithAnonymousFallback())));
        } else {
            return notFound();
        }
    }

    private List<ProductView> mapProducts(List<Product> all, User userContext) {
        List<ProductView> results = new ArrayList<>();
        all.forEach(product -> {
            results.add(mapProduct(product, userContext));
        });

        return results;
    }

    private ProductView mapProduct(Product product, User userContext) {
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

    private User getUserContextWithAnonymousFallback() {
        if (!OptionalAuthenticator.ANONYMOUS_USER.equals(ctx().request().username())) {
            User user = userRepository.getByIdentifier(ctx().request().username());
            return user != null ? user : UserRepository.anonymous;
        }
        return UserRepository.anonymous;
    }
}

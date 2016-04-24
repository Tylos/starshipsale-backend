package controllers.api;

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

import javax.inject.Inject;

@Security.Authenticated(RequiredAuthenticator.class)
public class FavoritesController extends Controller {

    @Inject
    UserRepository userRepository;

    @Inject
    ProductsRepository productsRepository;

    public Result list() {
        User user = userRepository.getByIdentifier(ctx().request().username());
        if (user != null) {
            return ok(Json.toJson(user.getFavorites()));
        } else {
            return notFound();
        }
    }

    public Result favoriteProduct(Long id) {
        User user = userRepository.getByIdentifier(ctx().request().username());
        Product product = productsRepository.getById(id);

        if (user != null && product != null) {
            user.favorite(product);
            return ok(Json.toJson(product));
        } else {
            Logger.debug("Product = " + product + "User " + user);
            return notFound();
        }
    }

    public Result unfavoriteProduct(Long id) {
        User user = userRepository.getByIdentifier(ctx().request().username());
        Product product = productsRepository.getById(id);
        user.unfavorite(product);
        return ok(Json.toJson(product));
    }
}

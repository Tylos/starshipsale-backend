package controllers;

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

public class ProductsController extends Controller {

    @Inject
    ProductsRepository productsRepository;

    @Inject
    UserRepository userRepository;

    public Result list() {
        return ok(Json.toJson(productsRepository.getAll()));
    }

    public Result show(Long id) {
        Product product = productsRepository.getById(id);
        if (product != null) {
            return ok(Json.toJson(product));
        } else {
            return notFound();
        }
    }

    @Security.Authenticated(ActionAuthenticator.class)
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

    @Security.Authenticated(ActionAuthenticator.class)
    public Result unfavoriteProduct(Long id){
        User user = userRepository.getByIdentifier(ctx().request().username());
        Product product = productsRepository.getById(id);
        user.unfavorite(product);
        return ok(Json.toJson(product));
    }
}

package controllers.api;

import javax.inject.Inject;

import controllers.authentication.RequiredAuthenticator;
import controllers.mapper.ProductToViewMapper;
import domain.Product;
import domain.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import repositories.ProductsRepository;
import repositories.UserRepository;


@Security.Authenticated(RequiredAuthenticator.class)
public class CartController extends Controller {

    @Inject
    UserRepository userRepository;

    @Inject
    ProductsRepository productsRepository;

    public Result list() {
        User user = userRepository.getByIdentifier(ctx().request().username());
        if (user != null) {
            return ok(Json.toJson(
                    ProductToViewMapper.map(
                            user.getShoppingCart(),
                            user)));
        } else {
            return notFound();
        }
    }

    public Result addToCart(String id) {
        User user = userRepository.getByIdentifier(ctx().request().username());
        Product product = productsRepository.getById(id);

        if (user != null && product != null) {
            user.addToCart(product);
            return ok(Json.toJson(
                    ProductToViewMapper.map(
                            product,
                            user)));
        } else {

            return notFound();
        }
    }

    public Result removeFromCart(String id) {
        User user = userRepository.getByIdentifier(ctx().request().username());
        Product product = productsRepository.getById(id);

        if(user != null && product != null) {
            user.removeFromCart(product);
            return ok(Json.toJson(
                    ProductToViewMapper.map(
                            product,
                            user)));
        }

        return notFound();
    }
}

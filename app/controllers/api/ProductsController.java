package controllers.api;

import javax.inject.Inject;

import controllers.authentication.OptionalAuthenticator;
import controllers.mapper.ProductToViewMapper;
import domain.Product;
import domain.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import repositories.ProductsRepository;
import repositories.UserRepository;

@Security.Authenticated(OptionalAuthenticator.class)
public class ProductsController extends Controller {

    @Inject
    ProductsRepository productsRepository;

    @Inject
    UserRepository userRepository;

    public Result list() {
        return ok(Json.toJson(
                ProductToViewMapper.map(
                        productsRepository.getAll(),
                        getUserContextWithAnonymousFallback())));
    }

    public Result show(Long id) {
        Product product = productsRepository.getById(id);
        if (product != null) {
            return ok(Json.toJson(
                    ProductToViewMapper.map(
                            product,
                            getUserContextWithAnonymousFallback())));
        } else {
            return notFound();
        }
    }

    private User getUserContextWithAnonymousFallback() {
        if (!OptionalAuthenticator.ANONYMOUS_USER.equals(ctx().request().username())) {
            User user = userRepository.getByIdentifier(ctx().request().username());
            return user != null ? user : UserRepository.anonymous;
        }
        return UserRepository.anonymous;
    }
}

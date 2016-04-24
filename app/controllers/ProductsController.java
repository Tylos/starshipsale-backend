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
import views.ProductView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProductsController extends Controller {

    @Inject
    ProductsRepository productsRepository;

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

}

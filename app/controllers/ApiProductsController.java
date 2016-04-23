package controllers;

import javax.inject.Inject;

import domain.Product;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.ProductsRepository;

public class ApiProductsController extends Controller {

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

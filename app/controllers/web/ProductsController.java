package controllers.web;

import javax.inject.Inject;

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

public class ProductsController extends Controller {

    @Inject
    ProductsRepository productsRepository;

    public Result list() {
        return ok(views.html.index.render(productsRepository.getAll()));
    }

    public Result show(Long id) {
        Product product = productsRepository.getById(id);
        if (product != null) {
            return ok(views.html.item.render(product));
        } else {
            return notFound();
        }
    }

}

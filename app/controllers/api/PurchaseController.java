package controllers.api;

import controllers.authentication.RequiredAuthenticator;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(RequiredAuthenticator.class)
public class PurchaseController extends Controller {

    public Result purchase() {
        return ok();
    }
}

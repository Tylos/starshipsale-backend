package domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String email;
    private final String password;
    private final ArrayList<Product> favorites;
    private final ArrayList<Product> shoppingCart;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.favorites = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void favorite(Product product) {
        favorites.add(product);
    }

    public void unfavorite(Product product) {
        favorites.remove(product);
    }

    public boolean isFavorite(Product product) {
        return favorites.contains(product);
    }

    public Boolean isInCart(Product product) {
        return shoppingCart.contains(product);
    }
}

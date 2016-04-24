package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private final String email;
    private final String password;
    private final Set<Product> favorites;
    private final Set<Product> shoppingCart;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.favorites = new HashSet<>();
        this.shoppingCart = new HashSet<>();
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

    public void addToCart(Product product) {
        shoppingCart.add(product);
    }

    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
    }

    public Boolean isInCart(Product product) {
        return shoppingCart.contains(product);
    }

    public List<Product> getFavorites() {
        return new ArrayList<>(favorites);
    }

    public List<Product> getShoppingCart() {
        return new ArrayList<>(shoppingCart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}

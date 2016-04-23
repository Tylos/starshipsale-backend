package repositories;

import domain.User;

import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
public class MemoryUserRepository implements UserRepository {

    private final HashMap<String, User> users;

    public MemoryUserRepository() {
        users = new HashMap<>();
    }

    @Override
    public User getByIdentifier(String identifier) {
        return users.get(identifier);
    }

    @Override
    public void add(User user) {
        users.put(user.getEmail(), user);
    }
}

package repositories;

import domain.User;

public interface UserRepository {

    User getByIdentifier(String identifier);

    void add(User user);

    public static User anonymous = new User("", "");
}

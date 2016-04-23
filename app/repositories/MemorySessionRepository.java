package repositories;

import domain.Session;
import domain.User;

import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
public class MemorySessionRepository implements SessionRepository {

    private final HashMap<String, User> sessions;

    public MemorySessionRepository() {
        sessions = new HashMap<>();
    }

    @Override
    public void add(Session session) {
        sessions.put(session.getToken(), session.getUser());
    }

    @Override
    public Session getByIdentifier(String token) {
        User user = sessions.get(token);
        if (user != null) {
            return new Session(token, user);
        }

        return null;
    }
}

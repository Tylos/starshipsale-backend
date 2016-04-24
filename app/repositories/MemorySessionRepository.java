package repositories;

import domain.Session;
import domain.User;

import javax.annotation.Nullable;
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
        String previousSession = findPreviousSession(session);
        if (previousSession != null) {
            sessions.remove(previousSession);
        }
        sessions.put(session.getToken(), session.getUser());
    }

    private @Nullable String findPreviousSession(Session session) {
        final String[] storedKey = {null};
        sessions.entrySet().forEach(entry -> {
            if (entry.getValue().equals(session.getUser())) {
                storedKey[0] = entry.getKey();
            }
        });
        return storedKey[0];
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

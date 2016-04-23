package repositories;

import domain.Session;

public interface SessionRepository {

    void add(Session session);

    Session getByIdentifier(String token);
}

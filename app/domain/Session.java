package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Session {

    private final String authToken;
    private final User user;

    public Session(String authToken, User user) {
        this.authToken = authToken;
        this.user = user;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public String getToken() {
        return authToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        return user.equals(session.user);

    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}

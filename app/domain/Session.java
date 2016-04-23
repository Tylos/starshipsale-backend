package domain;

public class Session {

    private final String authToken;
    private final User user;

    public Session(String authToken, User user) {
        this.authToken = authToken;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return authToken;
    }
}
